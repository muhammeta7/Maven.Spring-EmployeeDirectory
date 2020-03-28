package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService service;

//    // POST
//    @PostMapping("/API/departments/")
//    public ResponseEntity<Department> createDepartment(@RequestBody Department dep){
//        return new ResponseEntity<>(service.create(dep), HttpStatus.CREATED);
//    }
//
//    // GET
//    @GetMapping("/API/departments/{id}")
//    public ResponseEntity<Department> findEmployeeById(@PathVariable Integer id){
//        return new ResponseEntity<>(service.findDepartmentById(id), HttpStatus.OK);
//    }
//
//    @GetMapping("/API/departments/")
//    public ResponseEntity<Iterable<Department>> findAllEmployees(){
//        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
//    }
//
//    // UPDATE
//
//
//    // DELETE
//    @DeleteMapping("/API/departments/{id}")
//    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Integer id){
//        return new ResponseEntity<>(service.deleteDepartment(id), HttpStatus.NOT_FOUND);
//    }



}
