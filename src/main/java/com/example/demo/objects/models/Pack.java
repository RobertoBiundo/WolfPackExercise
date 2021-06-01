package com.example.demo.objects.models;

import com.example.demo.objects.data_transfer_objects.PackForAlterationDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pack implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pack_id")
    private int id;
    private String name;

    public Pack() {
    }

    public Pack(String name) {
        this.name = name;
    }

    public Pack(PackForAlterationDTO packDTO) {
        this.name = packDTO.getName();
    }

    @ManyToMany(
//        targetEntity=com.example.demo.objects.models.Employee.class,
        cascade={CascadeType.ALL}
    )
    @JoinTable(
        name = "pack_members",
        joinColumns = { @JoinColumn(name = "pack_id") },
        inverseJoinColumns = { @JoinColumn(name = "employee_id")}
    )
    Set<Employee> employees = new HashSet<>();

    public Collection getEmployees() {
        return employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
