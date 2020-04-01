package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    private EmployeeRepository repo;
    private DepartmentService depService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService){
        this.repo = employeeRepository;
        this.depService = departmentService;
    }

    public EmployeeService(){

    }

    // POST - Create employee
    //===============================================================================================================
    public Employee create(Employee emp){
        return repo.save(emp);
    }

    // PUT - Update other employee fields
    //===============================================================================================================
    public Employee updateFirstName(Integer id, String first){
        Employee originalEmployee = repo.findEmployeeById(id);
        originalEmployee.setFirstName(first);
        return repo.save(originalEmployee);
    }

    public Employee updateLastName(Integer id, String last){
        Employee originalEmployee = repo.findEmployeeById(id);
        originalEmployee.setLastName(last);
        return repo.save(originalEmployee);
    }

    public Employee updateTitle(Integer id, String title){
        Employee originalEmployee = repo.findEmployeeById(id);
        originalEmployee.setTitle(title);
        return repo.save(originalEmployee);
    }

    public Employee updatePhoneNum(Integer id, String num){
        Employee originalEmployee = repo.findEmployeeById(id);
        originalEmployee.setPhoneNumber(num);
        return repo.save(originalEmployee);
    }

    public Employee updateEmail(Integer id, String email){
        Employee originalEmployee = repo.findEmployeeById(id);
        originalEmployee.setPhoneNumber(email);
        return repo.save(originalEmployee);
    }

    public Employee updateHireDate(Integer id, String hireDate){
        Employee originalEmployee = repo.findEmployeeById(id);
        originalEmployee.setHireDate(hireDate);
        return repo.save(originalEmployee);
    }

    public Employee updateDepartment(Integer id, Integer departmentId){
        Employee originalEmployee = repo.findEmployeeById(id);
        originalEmployee.setDepartmentNumber(departmentId);
        return repo.save(originalEmployee);
    }

    // Update an employee to set their manager
    public List<Employee> updateManager(Integer id, Integer managerId){
        List<Employee> employeeList = repo.findAllByManagerId(id);
        Employee newManager = repo.findEmployeeById(managerId);
        employeeList.forEach(e -> e.setManager(newManager));
        repo.saveAll(employeeList);
        return employeeList;
    }

    public List<Employee> changeManagerToNextInLine(Integer managerId){
        return updateManager(managerId, getManager(managerId).getId());
    }

    //  Update an employee to set their manager
    public List<Employee> changeEmployeeManager(Integer oldId, Integer newId){
        List<Employee> employeeList = repo.findAllByManagerId(oldId);
        Employee newManager = repo.findEmployeeById(newId);
        employeeList.forEach(e -> e.setManager(newManager));
        repo.saveAll(employeeList);
        return employeeList;
    }

    // GET
    //===============================================================================================================
    public Optional<Employee> findEmpById(Integer id){
        return Optional.ofNullable(repo.findEmployeeById(id));
    }

    public Employee findEmployee(Integer id){
        return repo.findEmployeeById(id);
    }

    public Iterable<Employee> findAllEmployees(){
        return repo.findAll();
    }

    public Employee getManager(Integer id){
        return repo.findEmployeeById(id).getManager();
    }

    public Integer getDepartment(Integer id){
        return repo.findEmployeeById(id).getDepartmentNumber();
    }

    public String getTitle(Integer id){
        return repo.findEmployeeById(id).getTitle();
    }

    public String getEmail(Integer id){
        return repo.findEmployeeById(id).getEmail();
    }

    public List<Employee> getEmployeesWhereManagerIsNull(){
        return repo.findEmployeesByManagerIsNull();
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
        Employee employee = findEmployee(id);
        while (employee.getManager() != null) {
            Employee manager = employee.getManager();
            managers.add(manager);
            employee = manager;
        }
        return managers;
    }

    // Get Employees By Indirect
    public HashSet<Employee> findAllDirectAndIndirectReports(Integer managerId){
        ArrayList<Employee> resultList = new ArrayList<>(getEmployeesByManagerId(managerId));
        for (int i = 0; i < resultList.size(); i++) {
            resultList.addAll(findAllDirectAndIndirectReports(resultList.get(i).getId()));
        }
        return new HashSet<>(resultList);
    }

    // Remove a particular employee or list of employees
    public boolean removeEmployeeFromDept(Integer deptNumber, Integer num){
        getEmployeesByDepartment(num).stream().forEach(e -> {
            e.setDepartmentNumber(num);
            repo.save(e);
        });
        return true;
    }


    // Merge Departments
    public Boolean mergeTwoDepartments(Integer depToRemove, Integer depToMergeInto){
        Employee previousManager = depService.findDepartmentById(depToRemove).getDeptManager();
        Employee newManager = depService.findDepartmentById(depToMergeInto).getDeptManager();
        List<Employee> prevDeptEmployees = getEmployeesByDepartment(depToRemove);
        for(Employee e : prevDeptEmployees){
            e.setDepartmentNumber(depToMergeInto);
            repo.save(e);
        }
        changeEmployeeManager(previousManager.getId(), newManager.getId());
        return true;
    }

    // DELETE - Remove a particular employee
    //===============================================================================================================
    // Remove one employee
    public Boolean delete(Integer id){
        repo.deleteById(id);
        return true;
    }

    // Remove List of people
    public Boolean removeMultipleEmployees(List<Integer> list){
        Iterable<Employee> employees = findAllEmployees();
        for(Employee e : employees ){
            if(list.contains(e.getId())){
                repo.delete(e);
            }
        }
        return true;
    }

}
