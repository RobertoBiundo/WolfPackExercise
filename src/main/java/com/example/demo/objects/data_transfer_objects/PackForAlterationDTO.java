package com.example.demo.objects.data_transfer_objects;

public class PackForAlterationDTO {
    private int id;
    private String name;

    public PackForAlterationDTO() {
    }

    public PackForAlterationDTO(String name) {
        this.name = name;
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
