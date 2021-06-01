package com.example.demo.repositories;

import com.example.demo.objects.models.Employee;
import com.example.demo.objects.models.Pack;
import org.springframework.data.repository.CrudRepository;

public interface PackRepo extends CrudRepository<Pack, Integer> {
    Pack findById(int id);
}
