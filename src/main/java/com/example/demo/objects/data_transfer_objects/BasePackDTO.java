package com.example.demo.objects.data_transfer_objects;

import com.example.demo.objects.models.Pack;

public class BasePackDTO {
    private int id;
    private String name;

    public BasePackDTO() {
    }

    public BasePackDTO(Pack pack) {
        this.id = pack.getId();
        this.name = pack.getName();
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
}
