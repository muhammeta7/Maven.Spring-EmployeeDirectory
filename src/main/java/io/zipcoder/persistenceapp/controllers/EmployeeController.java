package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService service;

    // POST
    //===============================================================================================================
    @PostMapping("/API/employees/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee e){
        return new ResponseEntity<>(service.create(e), HttpStatus.CREATED);
    }

    // GET
    //===============================================================================================================
    @GetMapping("/API/employees/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Integer id){
        return new ResponseEntity<>(service.findEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping("/API/employees/")
    public ResponseEntity<Iterable<Employee>> findAllEmployees(){
        return new ResponseEntity<>(service.findAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("API/employees/department/{deptNumber}")
    public ResponseEntity<ArrayList<Employee>> getEmployeesByDept(@PathVariable Integer deptNumber){
        return new ResponseEntity<>(service.getEmployeesByDepartment(deptNumber),HttpStatus.OK);
    }

    @GetMapping("/API/employees/manager/{id}")
    public ResponseEntity<Employee> getEmployeeManager(@PathVariable Integer id){
        return new ResponseEntity<>(service.getManager(id),HttpStatus.OK);
    }

    @GetMapping("API/employees/getByManager/{managerId}")
    public ResponseEntity<ArrayList<Employee>> getEmployeesByManager(@PathVariable Integer managerId){
        return new ResponseEntity<>(service.getEmployeesByManagerId(managerId),HttpStatus.OK);
    }

    @GetMapping( "API/employees/noManager" )
    public ResponseEntity<Iterable<Employee>> getEmployeesWithoutManager() {
        return new ResponseEntity<>(service.getEmployeesWhereManagerIsNull(), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/API/employees/manager/getDirectReports/{id}")
    public ResponseEntity<ArrayList<Employee>> getDirectReports(@PathVariable Integer id){
        return new ResponseEntity<>(service.getAllDirectReports(id),HttpStatus.OK);
    }

    @GetMapping("/API/employees/getDepartment/{id}")
    public ResponseEntity<Integer> getEmployeeDepartment(@PathVariable Integer id){
        return new ResponseEntity<>(service.getDepartment(id),HttpStatus.OK);
    }

    @GetMapping("/API/employees/getTitle/{id}")
    public ResponseEntity<String> getEmployeeTitle(@PathVariable Integer id){
        return new ResponseEntity<>(service.getTitle(id),HttpStatus.OK);
    }

    @GetMapping("/API/employees/getEmail/{id}")
    public ResponseEntity<String> getEmployeeEmail(@PathVariable Integer id){
        return new ResponseEntity<>(service.getEmail(id),HttpStatus.OK);
    }

    @GetMapping("/API/employees/getHierarchy/{id}")
    public ResponseEntity<ArrayList<Employee>> getReportingHierarchy(@PathVariable Integer id){
        return new ResponseEntity<>(service.findHierarchy(id),HttpStatus.OK);
    }

    @GetMapping("/API/employees/findByManager/directIndirect/{managerId}")
    public ResponseEntity<HashSet<Employee>> findByManagerIncludingIndirect(@PathVariable Integer managerId) {
        return new ResponseEntity<>(service.findAllDirectAndIndirectReports(managerId), HttpStatus.OK);
    }

    // UPDATE
    //===============================================================================================================
    @PutMapping("/API/employees/updateFirstName/{id}")
    public ResponseEntity<Employee> updateFirst(@RequestParam String firstName, @PathVariable Integer id){
        return new ResponseEntity<>(service.updateFirstName(id,firstName), HttpStatus.OK);
    }

    @PutMapping("/API/employees/updateLastname/{id}")
    public ResponseEntity<Employee> updateLast(@RequestParam String lastName, @PathVariable Integer id){
        return new ResponseEntity<>(service.updateLastName(id,lastName), HttpStatus.OK);
    }

    @PutMapping("/API/employees/updateTitle/{id}")
    public ResponseEntity<Employee> updateTitle(@RequestParam String title, @PathVariable Integer id){
        return new ResponseEntity<>(service.updateTitle(id,title), HttpStatus.OK);
    }

    @PutMapping("/API/employees/updatePhoneNumber/{id}")
    public ResponseEntity<Employee> updatePhoneNumber(@RequestParam String num, @PathVariable Integer id){
        return new ResponseEntity<>(service.updatePhoneNum(id,num), HttpStatus.OK);
    }

    @PutMapping("/API/employees/updateEmail/{id}")
    public ResponseEntity<Employee> updateEmail(@RequestParam String email, @PathVariable Integer id){
        return new ResponseEntity<>(service.updateEmail(id,email), HttpStatus.OK);
    }

    @PutMapping("/API/employees/updateHiredate/{id}")
    public ResponseEntity<Employee> updateHireDate(@RequestParam String hireDate, @PathVariable Integer id){
        return new ResponseEntity<>(service.updateHireDate(id,hireDate), HttpStatus.OK);
    }

    @PutMapping("/API/employees/updateDepartment/{id}")
    public ResponseEntity<Employee> updateDepartment(@RequestParam Integer departmentId, @PathVariable Integer id){
        return new ResponseEntity<>(service.updateDepartment(id, departmentId), HttpStatus.OK);
    }

    @PutMapping("API/employees/removeByDepartment/{deptNum}")
    public ResponseEntity<Boolean> removeEmployeeByDept(@PathVariable Integer deptNum, @RequestParam Integer newDept){
        return new ResponseEntity<>(service.removeEmployeeFromDept(deptNum, newDept),HttpStatus.OK);
    }

    @PutMapping("/API/employees/updateManager/{id}")
    public ResponseEntity<List<Employee>> updateManager(@RequestParam Integer managerId,@PathVariable Integer id){
        return new ResponseEntity<>(service.updateManager(id,managerId), HttpStatus.OK);
    }

    @PutMapping("/API/employees/nextInLine/{id}")
    public ResponseEntity<List<Employee>> removeManagerAndSwap(@PathVariable Integer id){
        return new ResponseEntity<>(service.changeManagerToNextInLine(id), HttpStatus.OK);
    }

    @PutMapping("/API/mergeDepartments")
    public ResponseEntity<Boolean> mergeDepartmen(@RequestParam Integer previousDeptId, @PathVariable Integer deptToMergeInto){
        return new ResponseEntity<>(service.mergeTwoDepartments(previousDeptId,deptToMergeInto), HttpStatus.OK);
    }

    // DELETE
    //===============================================================================================================
    @DeleteMapping("/API/employees/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Integer id){
        return new ResponseEntity<>(service.deleteEmployee(id), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/API/employees/removeAll/{employeeIds}")
    public ResponseEntity<Boolean> deleteAllEmployee(@PathVariable List<Integer> employeeIds){
        return new ResponseEntity<>(service.removeMultipleEmployees(employeeIds), HttpStatus.NOT_FOUND);
    }

}


