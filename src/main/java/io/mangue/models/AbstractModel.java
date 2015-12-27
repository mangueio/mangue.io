package io.mangue.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.mangue.models.security.AclImpl;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sun.security.acl.PrincipalImpl;

import java.security.acl.Acl;

/**
 * Created by misael on 12/25/2015.
 */
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbstractModel {
    @Id
    public String id;

    @JsonIgnore
    private PrincipalImpl principal;

    private Acl acl;

    public AbstractModel(){
        this.id = new ObjectId().toHexString();
        principal = new PrincipalImpl(this.id);
        acl = new AclImpl(principal, this.id);
    }

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
