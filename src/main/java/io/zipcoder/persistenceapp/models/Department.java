package io.zipcoder.persistenceapp.models;

import javax.persistence.*;

@Entity
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer deptNum;
    private String deptName;

    @OneToOne
    private Employee manager;

    public Department() {
    }

//    public Department(String deptName, Employee manager){
//        this.dept_name = deptName;
//        this.manager = manager;
//    }

    public Integer getDept_num() {
        return deptNum;
    }

    public void setDept_num(Integer dept_num) {
        this.deptNum = dept_num;
    }

    public String getDept_name() {
        return deptName;
    }

    public void setDept_name(String dept_name) {
        this.deptName = dept_name;
    }

    public Employee getDeptManager() {
        return manager;
    }

    public void setDeptManager(Employee manager) {
        this.manager = manager;
    }
}
