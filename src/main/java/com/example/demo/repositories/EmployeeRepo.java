package com.example.demo.repositories;

import com.example.demo.objects.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
    Employee findById(int id);
    List<Employee> findAllByIdIsIn(List<Integer> employee_ids);
}
