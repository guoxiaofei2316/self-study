package test.jbpm.api;

import java.io.Serializable;

import org.jbpm.api.identity.Group;

/**
 * @description: 部门表 
 * @author admin-gxf       
 * @created 2018年1月3日 下午2:55:31
 */
public class MesGroup implements Serializable, Group {

	private static final long serialVersionUID = 1L;

	 private String id;

	 private String groupName;// 组织名称

	 private String groupType;// 组织类型

	 private MesGroup parentGroup;// 父组织

	 private String remarks;// 备注

	 protected long dbid;

	 protected int dbversion;

	 public int getDbversion() {
	     return dbversion;
	 }

	 public void setDbversion(int dbversion) {
	     this.dbversion = dbversion;
	 }

	 public long getDbid() {
	     return dbid;
	 }

	 public void setDbid(long dbid) {
	     this.dbid = dbid;
	 }

	 public String getParentGroupID() {
	     return parentGroup != null ? parentGroup.getId() : null;
	 }

	 public String getParentGroupName() {
	     return parentGroup == null ? "xxx" : parentGroup.getGroupName();
	 }

	 public void setId(String id) {
	     this.id = id;
	 }

	 public String getGroupName() {
	     return groupName;
	 }

	 public void setGroupName(String groupName) {
	     this.groupName = groupName;
	 }

	 public String getGroupType() {
	     return groupType;
	 }

	 public void setGroupType(String groupType) {
	     this.groupType = groupType;
	 }

	 public MesGroup getParentGroup() {
	     return parentGroup;
	 }

	 public void setParentGroup(MesGroup parentGroup) {
	     this.parentGroup = parentGroup;
	 }

	 public String getRemarks() {
	     return remarks;
	 }

	 public void setRemarks(String remarks) {
	     this.remarks = remarks;
	 }

	 // 实现Group接口必须的几个方法

	 public String getName() {
	     return this.groupName;
	 }

	 public String getType() {
	     return this.groupType;
	 }

	 public String getId() {
	     return id;
	 }

}
