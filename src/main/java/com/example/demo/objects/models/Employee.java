package com.example.demo.objects.models;

import com.example.demo.objects.data_transfer_objects.EmployeeForAlterationDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private int id;

    private boolean visible;

    @Column(nullable = false)
    private String name;

    private int gender;

    @Column(nullable = false)
    private Date birthdate;

    @Column(nullable = false)
    private String job;

    public Employee() {
    }

    public Employee(String name, int gender, Date birthdate, String job) {
        this.visible = true;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.job = job;
    }


    public Employee(EmployeeForAlterationDTO employeeDTO) {
        this.visible = employeeDTO.isVisible();
        this.name = employeeDTO.getName();
        this.gender = employeeDTO.getGender();
        this.birthdate = employeeDTO.getBirthdate();
        this.job = employeeDTO.getJob();
    }

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "employees",
            targetEntity = Pack.class
    )

    private Set<Pack> packs = new HashSet<>();

    public Collection getPacks() {
        return packs;
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

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
