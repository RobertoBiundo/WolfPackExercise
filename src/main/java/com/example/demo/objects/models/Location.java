package com.example.demo.objects.models;

import com.example.demo.objects.data_transfer_objects.LocationForAlterationDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private int employee_id;
    private float longitude;
    private float latitude;
    private int floor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Employee employee;

    public Location() {
    }

    public Location(int employee, float longitude, float latitude, int floor) {
        this.employee_id = employee;
        this.longitude = longitude;
        this.latitude = latitude;
        this.floor = floor;
    }

    public Location(LocationForAlterationDTO locationDTO) {
        this.id = locationDTO.getId();
        this.employee_id = locationDTO.getEmployee();
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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
