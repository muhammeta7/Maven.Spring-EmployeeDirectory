package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    // POST
    public Employee create(Employee emp){
        return repo.save(emp);
    }

    // PUT
    public Employee updateFirstName(Integer id, String first){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setFirstName(first);
        return repo.save(originalEmployee);
    }

    public Employee updateLastName(Integer id, String last){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setLastName(last);
        return repo.save(originalEmployee);
    }

    public Employee updateTitle(Integer id, String title){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setTitle(title);
        return repo.save(originalEmployee);
    }

    public Employee updatePhoneNum(Integer id, String num){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setPhoneNumber(num);
        return repo.save(originalEmployee);
    }

    public Employee updateEmail(Integer id, String email){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setPhoneNumber(email);
        return repo.save(originalEmployee);
    }

    public Employee updateHireDate(Integer id, String hireDate){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setHireDate(hireDate);
        return repo.save(originalEmployee);
    }

    public Employee updateManagerId(Integer id, Integer manadgerId){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setManagerId(manadgerId);
        return repo.save(originalEmployee);
    }

    public Employee updateDepartment(Integer id, Integer departmentId){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setDepartmentNumber(departmentId);
        return repo.save(originalEmployee);
    }

    // GET
    public Employee findEmployeeById(Integer id){
        return repo.findOne(id);
    }

    public Iterable<Employee> findAllEmployees(){
        return repo.findAll();
    }

    // DELETE
    public Boolean deleteEmployee(Integer id){
        repo.delete(id);
        return true;
    }


}
