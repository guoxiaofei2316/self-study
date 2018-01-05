package test.jbpm.service.impl;

import java.util.Arrays;  
import java.util.List;  
import org.hibernate.Session;  
import org.hibernate.criterion.Projections;  
import org.hibernate.criterion.Restrictions;  
import org.jbpm.api.JbpmException;  
import org.jbpm.api.identity.Group;  
import org.jbpm.api.identity.User;  
import org.jbpm.pvm.internal.env.BasicEnvironment;  
import org.jbpm.pvm.internal.env.EnvironmentImpl;  
import org.jbpm.pvm.internal.id.DbidGenerator;  
import org.jbpm.pvm.internal.identity.spi.IdentitySession;  
import test.jbpm.api.MesGroup;
import test.jbpm.api.MesGroupMembership;
import test.jbpm.api.MesUser;
  
@SuppressWarnings("unchecked")  
public class MesIdentitySessionImpl implements IdentitySession {  
  
    protected Session session;  
  
    public MesIdentitySessionImpl() {  
  
        this.session = BasicEnvironment.getFromCurrent(Session.class);  
  
    }  
    public String createUser(String id, String userName,  
  
    String businessEmail, String familName) {  
          
        MesUser user = new MesUser(id, userName, businessEmail);  
  
        long dbid = EnvironmentImpl.getFromCurrent(DbidGenerator.class).getNextId();  
  
        user.setDbid(dbid);  
        session.save(user);  
        return user.getId();  
  
    }  
  
    public MesUser findUserById(String userId) {  
  
        return (MesUser) session.createCriteria(MesUser.class).add(  
  
        Restrictions.eq("id", userId)).uniqueResult();  
  
    }  
  
    public List<User> findUsersById(String... userIds) {  
  
        List<User> users = session.createCriteria(MesUser.class).add(  
  
        Restrictions.in("id", userIds)).list();  
  
        if (userIds.length != users.size()) {  
  
            throw new JbpmException("not all users were found: "  
  
            + Arrays.toString(userIds));  
  
        }  
  
        return users;  
  
    }  
  
    public List<User> findUsers() {  
  
        return session.createCriteria(MesUser.class).list();  
  
    }  
  
    public void deleteUser(String userId) {  
  
        // lookup the user  
  
        MesUser user = findUserById(userId);  
  
        // cascade the deletion to the memberships  
  
        List<MesGroupMembership> memberships = session.createCriteria(  
  
        MesGroupMembership.class).add(Restrictions.eq("user", user)).list();  
  
        // delete the related memberships  
  
        for (MesGroupMembership membership : memberships) {  
  
            session.delete(membership);  
  
        }  
  
        // delete the user  
  
        session.delete(user);  
  
    }  
  
    public String createGroup(String groupName, String groupType,  
  
    String parentGroupId) {  
  
        MesGroup group = new MesGroup();  
  
        String groupId = groupType != null ? groupType + "." + groupName  
  
        : groupName;  
  
        group.setId(groupId);  
  
        long dbid = EnvironmentImpl.getFromCurrent(DbidGenerator.class)  
  
        .getNextId();  
  
        group.setDbid(dbid);  
  
        group.setGroupName(groupName);  
  
        group.setGroupType(groupType);  
  
        if (parentGroupId != null) {  
  
            MesGroup parentGroup = findGroupById(parentGroupId);  
  
            group.setParentGroup(parentGroup);  
  
        }  
  
        session.save(group);  
  
        return group.getId();  
  
    }  
  
    public List<User> findUsersByGroup(String groupId) {  
  
        return session.createCriteria(MesGroupMembership.class).createAlias(  
  
        "group", "g").add(Restrictions.eq("g.id", groupId))  
  
        .setProjection(Projections.property("user")).list();  
  
    }  
  
    public MesGroup findGroupById(String groupId) {  
  
        return (MesGroup) session.createCriteria(MesGroup.class).add(  
  
        Restrictions.eq("id", groupId)).uniqueResult();  
  
    }  
  
    public List<Group> findGroupsByUserAndGroupType(String userId,  
  
    String groupType) {  
  
        return session.createQuery(  
  
        "select distinct m.group" + " from "  
  
        + MesGroupMembership.class.getName()  
  
        + " as m where m.user.id = :userId"  
  
        + " and m.group.type = :groupType").setString("userId",  
  
        userId).setString("groupType", groupType).list();  
  
    }  
  
    public List<Group> findGroupsByUser(String userId) {  
  
        List<Group> gList = session.createQuery(  
  
        "select distinct m.group" + " from "  
  
        + MesGroupMembership.class.getName()  
  
        + " as m where m.user.id = :userId").setString(  
  
        "userId", userId).list();  
  
        return gList;  
  
    }  
  
    public List<Group> findGroups() {  
  
        return session.createCriteria(MesGroup.class).list();  
  
    }  
  
    public void deleteGroup(String groupId) {  
  
        // look up the group  
  
        MesGroup group = findGroupById(groupId);  
  
        // cascade the deletion to the memberships  
  
        List<MesGroupMembership> memberships = session.createCriteria(  
  
        MesGroupMembership.class).add(Restrictions.eq("group", group))  
  
        .list();  
  
        // delete the related memberships  
  
        for (MesGroupMembership membership : memberships) {  
  
            session.delete(membership);  
  
        }  
  
        // delete the group  
  
        session.delete(group);  
  
    }  
  
    public void createMembership(String userId, String groupId, String role) {  
  
        MesUser user = findUserById(userId);  
  
        if (user == null) {  
  
            throw new JbpmException("user " + userId + " doesn't exist");  
  
        }  
  
        MesGroup group = findGroupById(groupId);  
  
        if (group == null) {  
  
            throw new JbpmException("group " + groupId + " doesn't exist");  
  
        }  
  
        MesGroupMembership membership = new MesGroupMembership();  
  
        membership.setUser(user);  
  
        membership.setGroup(group);  
  
        membership.setRole(role);  
  
        long dbid = EnvironmentImpl.getFromCurrent(DbidGenerator.class)  
  
        .getNextId();  
  
        membership.setDbid(dbid);  
  
        session.save(membership);  
  
    }  
  
    public void deleteMembership(String userId, String groupId, String role) {  
  
    	MesGroupMembership membership = (MesGroupMembership) session.createCriteria(  
  
    			MesGroupMembership.class).createAlias("user", "u").createAlias(  
  
        "group", "g").add(Restrictions.eq("u.id", userId)).add(  
  
        Restrictions.eq("g.id", groupId)).uniqueResult();  
  
        session.delete(membership);  
  
    }  
      
  
} 
