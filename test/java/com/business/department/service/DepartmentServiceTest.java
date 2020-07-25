package com.business.department.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.business.department.model.Department;
import com.business.department.model.DepartmentKey;
import com.business.department.repository.DepartmentJPARepository;
import com.business.department.service.impl.DepartmentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class DepartmentServiceTest {
	@Mock
	DepartmentJPARepository repository;
	@InjectMocks
	DepartmentServiceImpl service;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testAddDepartment() {
		DepartmentKey testKey= new DepartmentKey("CEO","Executive");
		Department test = new Department("this guy", 7, true,testKey);
		Mockito.when(repository.save(test)).thenReturn(test);
		assertEquals(test,service.addDepartment(test));
		
	}

	@Test
	void testRemoveDepartmentByKey() {
		DepartmentKey testKey= new DepartmentKey("CEO","Executive");
		Department test = new Department("this guy", 7, true,testKey);
		Optional<Department> optionTest = Optional.of(test);
		Mockito.when(repository.findById(testKey)).thenReturn(optionTest);
		Mockito.when(repository.getOne(testKey)).thenReturn(test);
		assertEquals(test,service.removeDepartmentByKey(testKey));
	}

	@Test
	void testRemoveDepartmentByType() {
		DepartmentKey testKey= new DepartmentKey("CEO","Executive");
		Department test = new Department("this guy", 7, true,testKey);
		ArrayList<Department> arrayTest= new ArrayList<Department>();
		arrayTest.add(test);
		Mockito.when(repository.findAll()).thenReturn(arrayTest);
		assertEquals(arrayTest,service.removeDepartmentByType("Executive"));
	}

	@Test
	void testGetDepartmentsByType() {
		DepartmentKey testKey= new DepartmentKey("CEO","Executive");
		Department test = new Department("this guy", 7, true,testKey);
		ArrayList<Department> arrayTest= new ArrayList<Department>();
		arrayTest.add(test);
		Mockito.when(repository.findAll()).thenReturn(arrayTest);
		// Constructing test result
		 Map<String, List<Department>> mapResult= new HashMap<String, List<Department>>();
		 mapResult.put("Executive", arrayTest);
		assertEquals(mapResult,service.getDepartmentsByType());
	}

	@Test
	void testGetDepartmentNamesByType() {
		DepartmentKey testKey= new DepartmentKey("CEO","Executive");
		Department test = new Department("this guy", 7, true,testKey);
		ArrayList<Department> arrayTest= new ArrayList<Department>();
		arrayTest.add(test);
		Mockito.when(repository.findAll()).thenReturn(arrayTest); // mocking the return value
		// constructing possible return value
		Map<String, Set<String>> mapResult = new HashMap<String, Set<String>>();
		Set<String> setResult= new HashSet<String>();
		setResult.add("CEO");
		mapResult.put("Executive", setResult);
		
		assertEquals(mapResult,service.getDepartmentNamesByType());
	}

	@Test
	void testGetOneDepartmentNamesByType() {
		DepartmentKey testKey= new DepartmentKey("CEO","Executive");
		Department test = new Department("this guy", 7, true,testKey);
		ArrayList<Department> arrayTest= new ArrayList<Department>();
		arrayTest.add(test);
		Mockito.when(repository.findAll()).thenReturn(arrayTest); // mocking the return value
		// constructing possible return value
		Map<String, Set<String>> mapResult = new HashMap<String, Set<String>>();
		Set<String> setResult= new HashSet<String>();
		setResult.add("CEO");
		mapResult.put("Executive", setResult);
		
		assertEquals(mapResult,service.getOneDepartmentNamesByType("Executive"));
	}

}
