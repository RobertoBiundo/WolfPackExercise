package com.example.demo.objects.data_transfer_objects;

import com.example.demo.objects.models.Employee;

import java.sql.Date;
import java.util.Collection;

public class EmployeeDTO {
    private int id;
    private boolean visible;
    private String name;
    private int gender;
    private Date birthdate;
    private String job;
    private Collection packs;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.visible = employee.isVisible();
        this.name = employee.getName();
        this.gender = employee.getGender();
        this.birthdate = employee.getBirthdate();
        this.job = employee.getJob();
        this.packs = employee.getPacks();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getJob() {
        return job;
    }

    public Collection getPacks() {
        return packs;
    }
}
