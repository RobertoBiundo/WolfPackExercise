package com.example.demo.contracts.services;

import com.example.demo.objects.data_transfer_objects.EmployeeDTO;
import com.example.demo.objects.data_transfer_objects.EmployeeForAlterationDTO;
import com.example.demo.objects.models.Employee;

import java.util.List;

public interface IEmployeeService {
    EmployeeDTO getEmployee(int employee_id);
    Iterable<EmployeeDTO> getAllEmployees();
    Iterable<EmployeeDTO> getAllVisibleEmployees();
    Iterable<Employee> getEmployees(List<Integer> employee_ids);
    boolean deleteEmployee(int employee_id);
    boolean createEmployee(EmployeeForAlterationDTO employeeDTO);
    boolean updateEmployee(EmployeeForAlterationDTO employeeDTO);
}
