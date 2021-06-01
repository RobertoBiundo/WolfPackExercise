package com.example.demo.objects.data_transfer_objects;

import java.sql.Date;

import static com.example.demo.helpers.util.Validator.validateStringValue;

public class EmployeeForAlterationDTO {
    private int id;
    private boolean visible = true;
    private String name;
    private int gender;
    private Date birthdate;
    private String job;

    public EmployeeForAlterationDTO() {
    }

    public EmployeeForAlterationDTO(int id, boolean visible, String name, int gender, Date birthdate, String job) {
        this.id = id;
        this.visible = visible;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.job = job;
    }

    public EmployeeForAlterationDTO(String name, int gender, Date birthdate, String job) {
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.job = job;
    }

    public boolean validateForUpdate(){
        return this.validateForCreation() && this.id > 0;
    }

    public boolean validateForCreation(){
        return validateStringValue(this.name) && this.gender > 0;
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
}
