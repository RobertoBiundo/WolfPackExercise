package com.example.demo.contracts.services;

import com.example.demo.objects.data_transfer_objects.PackDTO;
import com.example.demo.objects.data_transfer_objects.PackForAlterationDTO;
import com.example.demo.objects.models.Employee;
import com.example.demo.objects.models.Pack;

import java.util.List;

public interface IPackService {
    PackDTO getPack(int pack_id);
    Iterable<PackDTO> getAllPacks();
    boolean deletePack(int pack_id);
    boolean createPack(PackForAlterationDTO packDTO);
    boolean updatePack(PackForAlterationDTO packDTO);
    boolean removeEmployees(int pack_id, Iterable<Employee> employee_list);
    boolean addEmployees(int pack_id, Iterable<Employee> employee_list);
}
