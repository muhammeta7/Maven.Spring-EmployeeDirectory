package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
    public Employee updateManager(Integer id, Integer managerId){
        Employee originalEmployee = repo.findOne(id);
        originalEmployee.setManager(repo.findOne(managerId));
        return repo.save(originalEmployee);
    }
    //  Update an employee to set their manager
    public List<Employee> changeEmployeeManager(Integer oldId, Integer newId){
        List<Employee> employeeList = repo.findAllByManagerId(oldId);
        Employee newManager = repo.findOne(newId);
        employeeList.forEach(e -> e.setManager(newManager));
        repo.save(employeeList);
        return employeeList;
    }




    // GET
    //===============================================================================================================
    public Employee findEmployeeById(Integer id){
        return repo.findOne(id);
    }

    public Iterable<Employee> findAllEmployees(){
        return repo.findAll();
    }

    public Employee getManager(Integer id){
        return repo.findOne(id).getManager();
    }

    public Integer getDepartment(Integer id){
        return repo.findOne(id).getDepartmentNumber();
    }

    public String getTitle(Integer id){
        return repo.findOne(id).getTitle();
    }

    public String getEmail(Integer id){
        return repo.findOne(id).getEmail();
    }

    // Get the list of employees of a particular department
    public ArrayList<Employee> getEmployeesByDepartment(Integer deptNumber){
        ArrayList<Employee> employees = (ArrayList<Employee>) findAllEmployees();
        return (ArrayList<Employee>) employees.stream()
                .filter(e -> e.getDepartmentNumber() == deptNumber)
                .collect(Collectors.toList());
    }

    // Get all Directory Reports
    public ArrayList<Employee> getAllDirectReports(Integer managerId){
        ArrayList<Employee> employees = (ArrayList<Employee>) findAllEmployees();
        ArrayList<Employee> empsByManager = new ArrayList<>();
        for (Employee e : employees) {
            if(e.getManager() != null){
                if(e.getManager().getId() == managerId){
                    empsByManager.add(e);
                }
            }

        }
        return empsByManager;
    }

    // Get all employees by manager
    public ArrayList<Employee> getEmployeesByManagerId(Integer managerId){
        return (ArrayList<Employee>) repo.findAllByManagerId(managerId);
    }

    // Get Employee Hierarchy
    public ArrayList<Employee> findHierarchy(Integer id) {
        ArrayList<Employee> managers = new ArrayList<>();
        Employee employee = findEmployeeById(id);
        while (employee.getManager() != null) {
            Employee manager = employee.getManager();
            managers.add(manager);
            employee = manager;
        }
        return managers;
    }

    // Get Employees By Indirect
    public Set<Employee> findAllByManagerIncIndirect(Integer managerId) {
        Set<Employee> employees = new TreeSet<>();
        for (Employee employee : findAllEmployees()) {
            if (findHierarchy(employee.getId()).contains(findEmployeeById(managerId))) {
                employees.add(employee);
            }
        }
        return employees;
    }

    // DELETE - Remove a particular employee
    //===============================================================================================================
    public Boolean deleteEmployee(Integer id){
        repo.delete(id);
        return true;
    }

    // Remove a particular employee or list of employees
    public boolean removeEmployeeFromDept(Integer deptNumber, Integer num){
        getEmployeesByDepartment(num).stream().forEach(e -> {
            e.setDepartmentNumber(num);
            repo.save(e);
        });
        return true;
    }


}
