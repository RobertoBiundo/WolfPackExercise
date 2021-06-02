package com.example.demo.objects.models;

import com.example.demo.objects.data_transfer_objects.BaseEmployeeDTO;
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
        this.id = packDTO.getId();
        this.name = packDTO.getName();
    }

    @ManyToMany(
        cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
        name = "pack_members",
        joinColumns = { @JoinColumn(name = "pack_id") },
        inverseJoinColumns = { @JoinColumn(name = "id")}
    )
    Set<Employee> employees = new HashSet<>();

    public Collection getEmployees() {
        Set<BaseEmployeeDTO> employeeCollection = new HashSet<>();
        for(Employee employee : employees){
            employeeCollection.add(new BaseEmployeeDTO(employee));
        }

        return employeeCollection;
    }

    public void addEmployee(Employee employee){
        this.employees.add(employee);
        employee.getPacks().add(this);
    }

    public void removeEmployee(Employee employee){
        this.employees.remove(employee);
        employee.getPacks().remove(this);
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
