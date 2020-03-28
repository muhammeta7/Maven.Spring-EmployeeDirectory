package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    public DepartmentRepository repo;

    public Department create(Department department){
        return repo.save(department);
    }

    public Department findDepartmentById(Integer id){
        return repo.findOne(id);
    }

    public Iterable<Department> findAll(){
        return repo.findAll();
    }

    public Boolean deleteDepartment(Integer id){
        repo.delete(id);
        return true;
    }

}
