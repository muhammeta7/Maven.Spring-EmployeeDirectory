package io.zipcoder.persistenceapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer departmentId;
    private String name;
    private Integer managerId;

    public Department() {
    }
}
