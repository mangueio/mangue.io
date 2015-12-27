package io.mangue.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by misael on 17/10/2015.
 */
@JsonIgnoreProperties(value = {"accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"}) // remove UserDetails properties from json
@Document
public class User extends AbstractModel implements UserDetails{

    public String username;
    public String name;

    public User(){
        super();
    }

    public void nullafy(){
        setAcl(null);
        setPrincipal(null);
    }

    @JsonIgnore
    public String password;
    public Boolean enabled = true;
    public Set<UserGrantedAuthority> authorities;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public Date createdAt;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    public Date updateAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}