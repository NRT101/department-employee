package com.business.department.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class DepartmentKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="DepartmentName")
	private String departmentName=null;
	@Column(name="DepartmentType")
	private String departmentType=null;
	
	public DepartmentKey(){
		this.departmentName="blank";
		this.departmentType="blank";
	}

	public DepartmentKey(String departmentName, String departmentType) {
		this.departmentName = departmentName;
		this.departmentType = departmentType;
	}
	/*
	 * Department name must be defined first before anything else.
	 * Department name can be changed at any time unless they are Human Resources or Facility.
	 * Human resources will only have one instance of itself once instantiated
	 * Facility departments must start with 'Facility -'
	 */

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

}
