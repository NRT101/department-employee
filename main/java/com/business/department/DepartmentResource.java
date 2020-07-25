package com.business.department;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.business.department.model.Department;
import com.business.department.model.DepartmentKey;
import com.business.department.service.DepartmentService;


@RestController
public class DepartmentResource {
	@Autowired
	private DepartmentService service;
	
	/*
	 * Get methods
	 */
	@GetMapping("/all-departments-by-type")
	public Map<String, List<Department>> getAllDepartmentsByType() {
		 Map<String, List<Department>> resultList=service.getDepartmentsByType();
		 return resultList;
	}

	@GetMapping("/all-department-names-by-type")
	public Map<String, Set<String>> getAllDepartmentNamesByType() {
		Map<String, Set<String>> resultList=service.getDepartmentNamesByType();
		 return resultList;
	}
	

	/*
	 * Add methods
	 */
	public ResponseEntity<Department> addDepartment(Department Department) {
		Department addedDepartment=service.addDepartment(Department);
		if(addedDepartment==null) {
	        return ResponseEntity.notFound().build();
		}else {
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(addedDepartment.getDepartmentName())
	                .toUri();
	       
	              return ResponseEntity.created(uri)
	                .body(addedDepartment);
	          }
	}
	
	@PostMapping("/add-department")
	public ResponseEntity<Department> createExecutiveDepartment(@Validated @RequestBody Department Department) {
		ResponseEntity<Department> addedDepartment=addDepartment(Department);
		return addedDepartment;
	}
	
	/*
	 * remove methods
	 */
	@DeleteMapping("/delete-department-by-key/{name}/{type}")
	public Department deleteDepartmentByKey(@PathVariable String name,@PathVariable String type) {
		DepartmentKey key = new DepartmentKey(name,type);
		Department deletedDepartment= service.removeDepartmentByKey(key);
		return deletedDepartment;
		
	}
	@DeleteMapping("/delete-department-by-type/{type}")
	public List<Department> deleteDepartmentByType(@PathVariable String type) {
		List<Department>  deletedDepartmentList= service.removeDepartmentByType(type);
		return deletedDepartmentList;
	}
}
