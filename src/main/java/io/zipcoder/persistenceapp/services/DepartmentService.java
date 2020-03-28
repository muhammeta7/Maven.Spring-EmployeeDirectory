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
    // Change update the department
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

    // TODO Set a new department manager
//    public Department updateManager(Integer id, Integer managerId){
//        Department original = findDepartmentById(id);
//        original.setDeptManager();
//    }

    // TODO Merge departments (given two department names eg: A and B, move the manager of B to
    //  report to the manager of A, and update all other employees to be members of department A)

    //DELETE
    //===============================================================================================================
    // Delete a department
    public Boolean deleteDepartment(Integer id){
        repo.delete(id);
        return true;
    }

}
