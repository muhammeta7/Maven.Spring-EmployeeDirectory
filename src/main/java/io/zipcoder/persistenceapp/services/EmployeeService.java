package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    // POST - Create employee
    //===============================================================================================================
    public Employee create(Employee emp){
        return repo.save(emp);
    }

    // PUT - Update other employee fields
    //===============================================================================================================
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

    public Employee updateDepartment(Integer id, Integer departmentId){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setDepartmentNumber(departmentId);
        return repo.save(originalEmployee);
    }

    // Update an employee to set their manager
    public Employee updateManager(Integer id, Employee manager){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setManager(manager);
        return repo.save(originalEmployee);
    }

    // GET
    //===============================================================================================================
    public Employee findEmployeeById(Integer id){
        return repo.findOne(id);
    }

    public Iterable<Employee> findAllEmployees(){
        return repo.findAll();
    }

    // Get the list of employees of a particular department
    public ArrayList<Employee> getEmployeesByDepartment(Integer deptNumber){
        ArrayList<Employee> employees = (ArrayList<Employee>) findAllEmployees();
        return (ArrayList<Employee>) employees.stream()
                .filter(e -> e.getDepartmentNumber() == deptNumber)
                .collect(Collectors.toList());
    }

    // TODO Get the list of employees under a particular manager
    public Iterable<Employee> getEmployeesByManager(Integer managerId){
        return null;
    }

    // TODO Get all employees who report directly or indirectly to a particular manager

    // TODO Get a list of employees with no assigned manager

    // TODO Get the entire reporting hierarchy for an employee (their manager + manager's manager etc.)


    // DELETE - Remove a particular employee
    //===============================================================================================================
    public Boolean deleteEmployee(Integer id){
        repo.delete(id);
        return true;
    }

    // TODO Remove a particular employee or list of employees

    // TODO Remove all employees under a particular manager (Including indirect reports)

    // TODO Remove all direct reports to a manager. Any employees previously managed by the deleted employees should now be managed by the next manager up the hierarchy.

}
