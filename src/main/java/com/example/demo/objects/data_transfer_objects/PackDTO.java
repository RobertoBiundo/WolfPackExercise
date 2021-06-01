package com.example.demo.objects.data_transfer_objects;

import com.example.demo.objects.models.Pack;

import java.util.Collection;

public class PackDTO {
    private int id;
    private String name;
    private Collection employees;

    public PackDTO() {
    }

    public PackDTO(Pack pack) {
        this.name = pack.getName();
        this.employees = pack.getEmployees();
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

    public Collection getEmployees() {
        return employees;
    }
}
