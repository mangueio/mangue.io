package io.mangue.models;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by misael on 17/10/2015.
 */
@Document
public class User {
    @Id
    public String id;
    public String username;
    public String password;
    public Set<UserGrantedAuthority> authorities;
    public String appId;

    @CreatedDate
    public DateTime createdAt;

    @LastModifiedDate
    public DateTime updateAt;

    public User(){}
}
