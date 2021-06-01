package com.example.demo.objects.models;

import com.example.demo.objects.data_transfer_objects.LocationForAlterationDTO;

import javax.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private int employee;
    private float longitude;
    private float latitude;
    private int floor;

    public Location() {
    }

    public Location(int employee, float longitude, float latitude, int floor) {
        this.employee = employee;
        this.longitude = longitude;
        this.latitude = latitude;
        this.floor = floor;
    }

    public Location(LocationForAlterationDTO locationDTO) {
        this.employee = locationDTO.getEmployee();
        this.longitude = locationDTO.getLongitude();
        this.latitude = locationDTO.getLatitude();
        this.floor = locationDTO.getFloor();
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

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longituse) {
        this.longitude = longituse;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
