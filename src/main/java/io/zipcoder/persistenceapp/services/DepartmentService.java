package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repo;

    // POST - Create a Department
    //===============================================================================================================
    public Department create(Department department){
        return repo.save(department);
    }

    // GET
    public Department findDepartmentById(Integer id){
        return repo.findOne(id);
    }

    public Iterable<Department> findAll(){
        return repo.findAll();
    }

    // PUT
    //===============================================================================================================
    // Update the department number
    public Department updateDepartmentNum(Integer id, Integer value){
        Department originalDept = repo.findOne(id);
        originalDept.setDept_num(value);
        return repo.save(originalDept);
    }

    // Change the name of a department
    public Department updateDepartmentName(Integer id, String value){
        Department originalDept = repo.findOne(id);
        originalDept.setDept_name(value);
        return repo.save(originalDept);
    }

    // Add Department manager
    public Department addDeptManager(Integer deptId, Integer managerId){
        EmployeeService service = new EmployeeService();
        Department department = repo.findOne(deptId);
        Employee manager = service.findEmployeeById(managerId);
        department.setDeptManager(manager);
        return repo.save(department);
    }

    // Update Department Manager
    public Department changeDepartmentManager(Integer id, Integer managerId){
        EmployeeService service = new EmployeeService();
        Department original = repo.findOne(id);
        Employee manager = service.findEmployeeById(managerId);
        original.setDeptManager(manager);
        return repo.save(original);
    }

    //DELETE
    //===============================================================================================================
    // Delete a department
    public Boolean deleteDepartment(Integer id){
        repo.delete(id);
        return true;
    }

}
