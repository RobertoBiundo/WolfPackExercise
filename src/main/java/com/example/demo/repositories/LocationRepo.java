package com.example.demo.repositories;

import com.example.demo.objects.models.Employee;
import com.example.demo.objects.models.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepo extends CrudRepository<Location, Integer> {
    Location findById(int id);
    Location findByEmployee(int employee_id);
    void deleteByEmployee(int employee_id);
}
