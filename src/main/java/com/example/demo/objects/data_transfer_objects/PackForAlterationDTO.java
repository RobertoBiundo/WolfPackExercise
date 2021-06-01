package com.example.demo.objects.data_transfer_objects;

import static com.example.demo.helpers.util.Validator.validateStringValue;

public class PackForAlterationDTO {
    private int id;
    private String name;

    public PackForAlterationDTO() {
    }

    public PackForAlterationDTO(String name) {
        this.name = name;
    }

    public boolean validateForUpdate(){
        return this.validateForCreation() && this.id > 0;
    }

    public boolean validateForCreation(){
        return validateStringValue(this.name);
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
