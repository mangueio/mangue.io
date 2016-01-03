package io.mangue.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by misael on 19/10/2015.
 */
@Document
public class App extends AbstractModel {

    public String name;

    public String subdomain;

    public String uniqueSimpleHash;

    public List<String> userIds;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public Date createdAt;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public Date updateAt;

    public Set<String> collections;
}