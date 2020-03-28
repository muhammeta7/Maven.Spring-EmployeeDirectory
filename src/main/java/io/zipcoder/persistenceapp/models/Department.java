package io.zipcoder.persistenceapp.models;

import javax.persistence.*;

@Entity
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer departmentId;
    private String name;
    private Integer managerId;

    public Department() {
    }

    @Column(name = "MANAGER_ID")
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Column(name = "DEPT_NUM")
    public Integer getDepartmentNumber() {
        return departmentId;
    }

    public void setDepartmentNumber(Integer departmentNumber) {
        this.departmentId = departmentNumber;
    }

    @Column(name = "DEPT_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
