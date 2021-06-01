package com.example.demo.objects.data_transfer_objects;

public class LocationForAlterationDTO {
    private int id;
    private int employee;
    private float longitude;
    private float latitude;
    private int floor;

    public LocationForAlterationDTO() {
    }

    public LocationForAlterationDTO(int id, int employee, float longitude, float latitude, int floor) {
        this.id = id;
        this.employee = employee;
        this.longitude = longitude;
        this.latitude = latitude;
        this.floor = floor;
    }

    public LocationForAlterationDTO(int employee, float longitude, float latitude, int floor) {
        this.employee = employee;
        this.longitude = longitude;
        this.latitude = latitude;
        this.floor = floor;
    }

    public boolean validateForUpdate(){
        return this.validateForCreation() && this.id > 0;
    }

    public boolean validateForCreation(){
        return this.employee > 0 && this.longitude > 0 && this.latitude > 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee() {
        return employee;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public int getFloor() {
        return floor;
    }
}
