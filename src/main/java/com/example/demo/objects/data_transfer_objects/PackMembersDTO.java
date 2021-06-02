package com.example.demo.objects.data_transfer_objects;

import java.util.List;

public class PackMembersDTO {
    private int pack_id;
    private List<Integer> employees;

    public PackMembersDTO() {
    }

    public PackMembersDTO(int pack_id, List<Integer> employees) {
        this.pack_id = pack_id;
        this.employees = employees;
    }

    public int getPack_id() {
        return pack_id;
    }

    public void setPack_id(int pack_id) {
        this.pack_id = pack_id;
    }

    public List<Integer> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Integer> employees) {
        this.employees = employees;
    }
}
