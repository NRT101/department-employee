package com.business.department.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Department")
public  class Department {
	
	@Column(name="Department_Head")
	private String departmentHead; // Head of the department;
	@Column(name="Authority_Level", nullable = false)
	private int authorityLevel; // The authority level of entities. Facilities<=2; Executive<=7
	@Column(name="Active_Flag", nullable = false)
	private boolean isActive=false;
	
	@EmbeddedId
	private DepartmentKey key;
	
	public Department() {
		this.departmentHead = null;
		this.authorityLevel = 0;
		this.isActive = false;
		this.key = new DepartmentKey();
	}
	
	
	public Department(String departmentHead, int authorityLevel, boolean isActive, DepartmentKey key) {
		this.departmentHead = departmentHead;
		this.authorityLevel = authorityLevel;
		this.isActive = isActive;
		this.key = key;
	}

	/*
	 * Department head is added second after the department's name.
	 * Highest authority of department. For executive (7) and R&D (6) of highest authority they can't be removed
	 * as former is the CEO and the latter is CTO 
	 * Head can be replaced only by employees within company
	 * Head can't be set to null
	 */
	public void setDepartmentHead(String head) {
			departmentHead=head;

	}
//	// head is the replacement
//	public boolean replaceDepartmentHead(String head) {
//		boolean beheaded=false;
//		if(departmentHead!=null  && departmentName!=null  && head!=null) {
//			beheaded=true;
//			departmentHead=head;
//		}
//		return beheaded;
//	}
	public String getDepartmentHead() {
		return departmentHead;
	}
	

	
	/*
	 *  Max authority level is going to be 7 which means this department can control all other departments. 
	 *  1 is the lowest authority
	 *  Executive departments are always going to be highest authority but only one can be 7.
	 */

	public void setAuthorityLevel(int level) {
			authorityLevel=level;
	}
	

	public int getAuthorityLevel() {
		return authorityLevel;
	}
	/*
	 * Department can't be set to inactive if there are employees still in the department. 
	 */
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public DepartmentKey getKey() {
		return key;
	}

	public void setKey(DepartmentKey key) {
		this.key = key;
	}

	public String getDepartmentType() {
		return key.getDepartmentType();
	}

	public String getDepartmentName() {
		return key.getDepartmentName();
	}

}
