package io.mangue.models.security;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.security.Principal;
import java.security.acl.AclEntry;

/**
 * Created by misael on 12/25/2015.
 */
public class PrincipalEntryPair implements Serializable {
    public Principal principal;
    public AclEntry entry;

    @Override
    public boolean equals(Object object) {
        if(object instanceof PrincipalEntryPair && this.principal != null) {
            PrincipalEntryPair object2 = (PrincipalEntryPair) object;
            if(object2.principal!=null)
                return this.principal.equals(object2.principal);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if(principal != null)
            return principal.hashCode() * 31;
        return super.hashCode();
    }
}
