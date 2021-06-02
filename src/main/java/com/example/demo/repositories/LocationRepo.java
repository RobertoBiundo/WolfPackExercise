package com.example.demo.repositories;

import com.example.demo.objects.models.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepo extends CrudRepository<Location, Integer> {
    Location findById(int id);
    Location findByEmployee_id(int employee_id);
}
