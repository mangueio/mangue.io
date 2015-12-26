package io.mangue;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mangue.models.AppUser;
import io.mangue.models.User;
//import io.mangue.models.security.AclEntryImpl;
import io.mangue.models.security.AclImpl;
import io.mangue.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sun.security.acl.AclEntryImpl;
import sun.security.acl.GroupImpl;
import sun.security.acl.PermissionImpl;
import sun.security.acl.PrincipalImpl;

import java.security.acl.Acl;
import java.security.acl.AclEntry;
import java.security.acl.Group;
import java.security.acl.Permission;
import java.util.Enumeration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AclTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        AppUser p1 = new AppUser();
        p1.id = "123";
        p1.name = "p1";
        p1.principal = new PrincipalImpl(p1.id);
        AppUser p2 = new AppUser();
        p2.id = "321";
        p2.name = "p2";
        p2.principal = new PrincipalImpl(p2.id);
        AppUser owner = new AppUser();
        owner.id = "321";
        owner.name = "owner";
        owner.principal = new PrincipalImpl(owner.id);

        Permission read = new PermissionImpl("READ");
        Permission write = new PermissionImpl("WRITE");

        System.out.println("Creating a new group with two members: user1 and user2");
        Group g = new GroupImpl("group1");
        g.addMember(p1.principal);
        g.addMember(p2.principal);

        // create a new acl with the name "exampleAcl"
        //
        System.out.println("Creating a new Acl named 'exampleAcl'");
        Acl acl = new AclImpl(owner.principal, "exampleAcl");

        AclEntry entry1 = new AclEntryImpl(g);
        entry1.addPermission(read);
        entry1.addPermission(write);
        acl.addEntry(owner.principal, entry1);

        String json = mapper.writeValueAsString(acl);
        json = mapper.writeValueAsString(acl);
        String json2 = mapper.writeValueAsString(entry1);
        json2 = mapper.writeValueAsString(acl);

        //
        // Take away WRITE permissions for
        // user1. All others in groups still have
        // WRITE privileges.
        //
        System.out.println("Creating a new Acl Entry in exampleAcl for user1");
        System.out.println("  without write permission");
        AclEntry entry2 = new AclEntryImpl(p1.principal);
        entry2.addPermission(write);
        entry2.setNegativePermissions();
        acl.addEntry(owner.principal, entry2);

        //
        // This enumeration is an enumeration of
        // Permission interfaces. It should return
        // only "READ" permission.
        Enumeration e1 = acl.getPermissions(p1.principal);
        System.out.println("Permissions for user1 are:");
        while (e1.hasMoreElements()) {
            System.out.println("  " + e1.nextElement());
        }

        //
        // This enumeration should have "READ" and"WRITE"
        // permissions.
        Enumeration e2 = acl.getPermissions(p2.principal);
        System.out.println("Permissions for user2 are:");
        while (e2.hasMoreElements()) {
            System.out.println("  " + e2.nextElement());
        }

        // This should return false.
        boolean b1 = acl.checkPermission(p1.principal, write);
        System.out.println("user1 has write permission: " + b1);

        AppUser user = new AppUser();
        user.name = "Misael";
        user.password = "misa99zz";
        user.username = "misawsneto";

        userRepository.save(user);

        // This should all return true;
        boolean b2 = acl.checkPermission(p1.principal, read);
        boolean b3 = acl.checkPermission(p2.principal, read);
        boolean b4 = acl.checkPermission(p2.principal, write);
        System.out.println("user1 has read permission: " + b2);
        System.out.println("user2 has read permission: " + b3);
        System.out.println("user2 has write permission: " + b4);

    }

}