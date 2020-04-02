package io.zipcoder.persistenceapp;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService service;

    // POST
    @PostMapping("/API/departments/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department dep){
        return new ResponseEntity<>(service.create(dep), HttpStatus.CREATED);
    }

    // GET
    @GetMapping("/API/departments/{id}")
    public ResponseEntity<Department> findEmployeeById(@PathVariable Integer id){
        return new ResponseEntity<>(service.findDepartmentById(id), HttpStatus.OK);
    }

    @GetMapping("/API/departments/")
    public ResponseEntity<Iterable<Department>> findAllEmployees(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/API/departments/updateDeptNumber/{id}")
    public ResponseEntity<Department> updateFirst(@RequestParam Integer num, @PathVariable Integer id){
        return new ResponseEntity<>(service.updateDepartmentNum(id,num), HttpStatus.OK);
    }

    @PutMapping("/API/departments/updateDeptName/{id}")
    public ResponseEntity<Department> updateLast(@RequestParam String name, @PathVariable Integer id){
        return new ResponseEntity<>(service.updateDepartmentName(id,name), HttpStatus.OK);
    }

//    @PutMapping("API/departments/addManager/{id}")
//    public ResponseEntity<> addManager(@RequestParam Integer id){
//
//    }

    @PutMapping("/API/departments/updateManager/{deptId}")
    public ResponseEntity<Department> updateManager(@RequestParam Integer managerId, @PathVariable Integer deptId){
        return new ResponseEntity<>(service.changeDepartmentManager(deptId,managerId), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/API/departments/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Integer id){
        return new ResponseEntity<>(service.deleteDepartment(id), HttpStatus.NOT_FOUND);
    }

}
