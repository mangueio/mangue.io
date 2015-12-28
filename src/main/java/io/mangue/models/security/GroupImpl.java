//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.mangue.models.security;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Enumeration;
import java.util.Vector;

@Document
public class GroupImpl implements Group, Serializable {
    private Vector<Principal> groupMembers = new Vector(50, 100);

    @Id
    private String id;

    public GroupImpl() {
        this.id = new ObjectId().toHexString();
    }

//    public GroupImpl(String id) {
//        this.id = id;
//    }

    public boolean addMember(Principal var1) {
        if(var1 != null)
            if(this.groupMembers.contains(var1)) {
                return false;
            } else if(this.id.equals(var1.toString())) {
                throw new IllegalArgumentException();
            } else {
                this.groupMembers.addElement(var1);
                return true;
            }
        return false;
    }

    public boolean removeMember(Principal var1) {
        return this.groupMembers.removeElement(var1);
    }

    public Enumeration<? extends Principal> members() {
        return this.groupMembers.elements();
    }

    public boolean equals(Object object) {
        if(this == object) {
            return true;
        } else if(!(object instanceof Group)) {
            return false;
        } else {
            Group g = (Group) object;
            return this.id.equals(g.toString());
        }
    }

    public boolean equals(Group var1) {
        return this.equals((Object)var1);
    }

    public String toString() {
        return this.id;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean isMember(Principal var1) {
        if(this.groupMembers.contains(var1)) {
            return true;
        } else {
            Vector var2 = new Vector(10);
            return this.isMemberRecurse(var1, var2);
        }
    }

    public String getName() {
        return this.id;
    }

    boolean isMemberRecurse(Principal var1, Vector<Group> var2) {
        Enumeration var3 = this.members();

        boolean var4;
        do {
            if(!var3.hasMoreElements()) {
                return false;
            }

            var4 = false;
            Principal var5 = (Principal)var3.nextElement();
            if(var5.equals(var1)) {
                return true;
            }

            if(var5 instanceof GroupImpl) {
                GroupImpl var6 = (GroupImpl)var5;
                var2.addElement(this);
                if(!var2.contains(var6)) {
                    var4 = var6.isMemberRecurse(var1, var2);
                }
            } else if(var5 instanceof Group) {
                Group var7 = (Group)var5;
                if(!var2.contains(var7)) {
                    var4 = var7.isMember(var1);
                }
            }
        } while(!var4);

        return var4;
    }
}
