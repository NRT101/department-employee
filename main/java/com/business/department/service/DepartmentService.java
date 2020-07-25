package com.business.department.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.business.department.model.Department;
import com.business.department.model.DepartmentKey;


public interface DepartmentService {
	/*
	 * Put methods to add to server
	 */
	public Department addDepartment(Department department);
	/*
	 * Remove methods for the service
	 */
	// Uses the key to remove a department; returns said department
	public Department removeDepartmentByKey(DepartmentKey key);
	// Removes all departments of a set type
	public List<Department> removeDepartmentByType(String type);
	/*
	 * Get Methods for the service
	 */ 
	// Configures list input as an aggregated list of departments sorted by type
	public Map<String, List<Department>> getDepartmentsByType();
	// Configures list input as an aggregated list of department names sorted by type
	public Map<String, Set<String>> getDepartmentNamesByType();
	
	// Configures a list based off of input that aggregates all department names based off of type
	public Map<String, Set<String>> getOneDepartmentNamesByType(String typeFilter);
}
