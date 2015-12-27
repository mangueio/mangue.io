package io.mangue.models.security;

import java.security.Principal;

public class PrincipalImpl implements Principal {
    private String id;

    public String getId() {
        return id;
    }

    public PrincipalImpl(String id) {
        this.id = id;
    }

    public boolean equals(Object var1) {
        if(var1 instanceof PrincipalImpl) {
            PrincipalImpl var2 = (PrincipalImpl)var1;
            return this.id.equals(var2.toString());
        } else {
            return false;
        }
    }

    public String toString() {
        return this.id;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public String getName() {
        return this.id;
    }
}
