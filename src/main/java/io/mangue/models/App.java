package io.mangue.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by misael on 19/10/2015.
 */
@Document
public class App {
    @Id
    public String id;

    public String name;

    public String subdomain;

    @CreatedDate
    public DateTime createdAt;

    @LastModifiedDate
    public DateTime updateAt;
}
