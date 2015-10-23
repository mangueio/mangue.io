package io.mangue.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AmazonCloudService {

	@Value("${amazon.accessKey}")
	String accessKey;
	@Value("${amazon.accessSecretKey}")
	String accessSecretKey;
	@Value("${amazon.distributionKey}")
	String distributionKey;
	@Value("${amazon.privateCloudfrontUrl}")
	String publicCloudfrontUrl;
	@Value("${amazon.publicCloudfrontUrl}")
	String privateCloudfrontUrl;
	@Value("${amazon.publicBucket}")
	String publicBucket;
	@Value("${amazon.privateBucket}")
	String privateBucket;

	public String getPublicImageURL(String appDomain, String fileName) throws IOException {
		return "http://" + publicCloudfrontUrl + "/" + appDomain + "/images/" + fileName;
	}

//	public String uploadPublicImage(InputStream input, Long lenght, String networkDomain, String size, String mime) throws IOException, AmazonS3Exception {
//		byte[] bytes = WordrailsUtil.getBytes(input);
//		String hash = WordrailsUtil.getHash(bytes);
//
//		return uploadPublicImage(new ByteArrayInputStream(bytes), lenght, networkDomain, hash, size, mime);
//	}

//	public String uploadPublicImage(InputStream input, Long lenght, String networkDomain, String hash, String size, String mime) throws IOException, AmazonS3Exception {
//		if(hash == null) {
//			byte[] bytes = WordrailsUtil.getBytes(input);
//			hash = WordrailsUtil.getHash(bytes);
//		}
//
//		ObjectMetadata md = new ObjectMetadata();
//		md.setContentType(mime);
//		md.addUserMetadata("size", size);
//
//		String path = networkDomain + "/images/" + hash;
//		uploadFile(input, lenght, publicBucket, path, md);
//
//		return hash;
//	}

	public boolean exists(AmazonS3 s3Client, String bucket, String key) {
		try {
			s3Client.getObject(bucket, key);
		} catch(AmazonServiceException e) {
			return false;
		}
		return true;
	}

	public S3Object get(AmazonS3 s3Client, String bucket, String key) {
		try {
			return s3Client.getObject(bucket, key);
		} catch(AmazonServiceException e) {
			return null;
		}
	}

	private AmazonS3 s3() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, accessSecretKey);
		return new AmazonS3Client(awsCreds);
	}

	private void uploadFile(InputStream input, Long lenght, String bucketName, String keyName, ObjectMetadata metadata) throws IOException, AmazonS3Exception {
		if (input.available() == 0) {
			throw new AmazonS3Exception("InputStream is empty");
		}

		AmazonS3 s3Client = s3();

		if(exists(s3Client, bucketName, keyName)) {
			return;
		}

		// Create a list of UploadPartResponse objects. You get one of these for
		// each part upload.
		List<PartETag> partETags = new ArrayList<>();

		// Step 1: Initialize.
		InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, keyName);
		if(metadata != null) initRequest.setObjectMetadata(metadata);

		initRequest.setCannedACL(CannedAccessControlList.PublicRead);
		InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);
		long partSize = 7 * 1024 * 1024; // Set part size to 7 MB.

		try {
			// Step 2: Upload parts.
			long filePosition = 0;
			for (int i = 1; filePosition < lenght; i++) {
				// Last part can be less than 3 MB. Adjust part size.
				partSize = Math.min(partSize, (lenght - filePosition));

				// Create request to upload a part.
				UploadPartRequest uploadRequest = new UploadPartRequest().
						withBucketName(bucketName).
						withKey(keyName).
						withUploadId(initResponse.getUploadId()).
						withPartNumber(i).
						withFileOffset(filePosition).
						withInputStream(input).
						withPartSize(partSize);

				// Upload part and add response to our list.
				partETags.add(s3Client.uploadPart(uploadRequest).getPartETag());

				filePosition += partSize;
			}

			// Step 3: Complete.
			CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucketName, keyName, initResponse.getUploadId(), partETags);

			s3Client.completeMultipartUpload(compRequest);
		} catch (Exception e) {
			s3Client.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, keyName, initResponse.getUploadId()));

			throw new AmazonS3Exception("Error uploading file to Amazon S3", e);
		}
	}
}