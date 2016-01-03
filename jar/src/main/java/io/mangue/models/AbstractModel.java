package io.mangue.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.mangue.models.security.PrincipalImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.acl.Acl;

/**
 * Created by misael on 12/25/2015.
 */
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractModel {
    @Id
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public PrincipalImpl principal;

    public Acl acl;

    public PrincipalImpl getPrincipal() {
        return principal;
    }

    public void setPrincipal(PrincipalImpl principal) {
        this.principal = principal;
    }

    public Acl getAcl() {
        return acl;
    }

    public void setAcl(Acl acl) {
        this.acl = acl;
    }
}
