package test.jbpm.api;

import java.io.Serializable;

/**
 * @description: 用户部门关系表
 * @author admin-gxf       
 * @created 2018年1月3日 下午3:07:17
 */
public class MesGroupMembership implements Serializable {

	private static final long serialVersionUID = 1L;

	 protected long dbid;

	 protected int dbversion;

	 private MesUser user;

	 private MesGroup group;

	 protected String role;

	 public int getDbversion() {
	     return dbversion;
	 }

	 public void setDbversion(int dbversion) {
	     this.dbversion = dbversion;
	 }

	 public long getDbid() {
	     return dbid;
	 }

	 public String getRole() {
	     return role;
	 }

	 public void setRole(String role) {
	     this.role = role;
	 }

	 public void setDbid(long dbid) {
	     this.dbid = dbid;
	 }

	 public MesGroup getGroup() {
	     return group;
	 }

	 public void setGroup(MesGroup group) {
	     this.group = group;
	 }

	 public String getUserNo() {
	     return user.getUserNo();
	 }

	 public String getUserID() {
	     return user.getId();
	 }

	 public String getUserName() {
	     return user.getUserName();
	 }

	 public MesUser getUser() {
	     return user;
	 }

	 public void setUser(MesUser user) {
	     this.user = user;
	 }
	
}
