//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.mangue.models.security;

import java.io.Serializable;
import java.security.acl.Permission;

public class PermissionImpl implements Permission, Serializable {
    public String permission;

    public PermissionImpl(){}

    public PermissionImpl(String permission) {
        this.permission = permission;
    }

    public boolean equals(Object var1) {
        if(var1 instanceof Permission) {
            Permission var2 = (Permission)var1;
            return this.permission.equals(var2.toString());
        } else {
            return false;
        }
    }

    public String toString() {
        return this.permission;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }
}
