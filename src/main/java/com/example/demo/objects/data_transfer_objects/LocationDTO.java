package com.example.demo.objects.data_transfer_objects;

import com.example.demo.objects.models.Location;

public class LocationDTO {
    private int id;
    private int employee;
    private float longitude;
    private float latitude;
    private int floor;

    public LocationDTO() {
    }

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.employee = location.getEmployee();
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        this.floor = location.getFloor();
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
