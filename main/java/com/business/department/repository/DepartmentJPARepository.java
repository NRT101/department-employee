package com.business.department.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.department.model.DepartmentKey;
import com.business.department.model.Department;


@Repository
@Transactional
public interface DepartmentJPARepository extends JpaRepository<Department,DepartmentKey>{
	
}
