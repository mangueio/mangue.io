package io.mangue.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by misael on 19/10/2015.
 */
@Document
public class App {
    @Id
    public String id;

    public String name;

    public String subdomain;

    public String uniqueSimpleHash;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public Date createdAt;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public Date updateAt;
}
