package io.zipcoder.persistenceapp.models;

import javax.persistence.*;

@Entity
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dept_number;
    private String dept_name;

    @OneToOne
    private Employee manager;

    public Department() {
    }

    public Department(String deptName, Employee manager){
        this.dept_name = deptName;
        this.manager = manager;
    }

    public Integer getDept_num() {
        return dept_number;
    }

    public void setDept_num(Integer dept_num) {
        this.dept_number = dept_num;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public Employee getDeptManager() {
        return manager;
    }

    public void setDeptManager(Employee manager) {
        this.manager = manager;
    }
}
