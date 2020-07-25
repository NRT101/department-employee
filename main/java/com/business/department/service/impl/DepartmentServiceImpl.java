package com.business.department.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.department.model.Department;
import com.business.department.model.DepartmentKey;
import com.business.department.repository.DepartmentJPARepository;
import com.business.department.service.DepartmentService;


@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentJPARepository repository;
	
	DepartmentServiceImpl(DepartmentJPARepository departmentJPARepository){
		this.repository=departmentJPARepository;
	}
	
	@Override
	public Department addDepartment(Department department) {
		// the Department has to have a department name; TODO: THROW A CUSTOM ERROR for invalid entry
		if(department.getKey().getDepartmentName()==null)
			return null;
		
		// checks to see what type of department and adjusts accordingly
		else if(department.getKey().getDepartmentType().equals("Executive") && department.getKey().getDepartmentName()!=null && department.getAuthorityLevel()>=4 && department.getAuthorityLevel()<=7 ) {
			repository.save(department);
			return department;
		}
		else if(department.getKey().getDepartmentType().equals("Facility") && department.getKey().getDepartmentName().startsWith("Facility -") && department.getAuthorityLevel()<=2 && department.getAuthorityLevel()>=1) {
			repository.save(department);
			return department;
		}
		System.out.println("Got here3");
		return null;
	}

	@Override
	public Department removeDepartmentByKey(DepartmentKey key) {
		Department result =null;
		if(repository.findById(key) != null) {
			result=repository.getOne(key);
			repository.deleteById(key);
		}
		return result;
	}

	@Override
	public List<Department> removeDepartmentByType(String type) {
		Map<String, List<Department>> departmentsList= getDepartmentsByType();
		List<Department> typeDepartmentList= departmentsList.get(type);
		// There is something so delete
		if(!typeDepartmentList.isEmpty()) {
			repository.deleteAll(typeDepartmentList);
		}
		return typeDepartmentList;
	}

	@Override
	public Map<String, List<Department>> getDepartmentsByType() {
		List<Department> departmentList=repository.findAll();
		Map<String, List<Department>> groupByTypeMap = departmentList.stream().
				collect(Collectors.groupingBy(Department::getDepartmentType));
		return groupByTypeMap;
	}

	@Override
	public Map<String, Set<String>> getDepartmentNamesByType() {
		List<Department> departmentList=repository.findAll();
		Map<String, Set<String>> groupByTypeMap = departmentList.stream().
				collect(Collectors.groupingBy(Department::getDepartmentType, 
						Collectors.mapping(Department::getDepartmentName, Collectors.toSet())));
		return groupByTypeMap;
	}

	@Override
	public Map<String, Set<String>> getOneDepartmentNamesByType(String typeFilter) {
		List<Department> departmentList=repository.findAll();
		Map<String, Set<String>> groupByTypeMap = departmentList.stream().filter(o->o.getDepartmentType().equals(typeFilter)).
				collect(Collectors.groupingBy(Department::getDepartmentType, 
						Collectors.mapping(Department::getDepartmentName, Collectors.toSet())));
		return groupByTypeMap;
	}

}
