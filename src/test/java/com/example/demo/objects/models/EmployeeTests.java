package com.example.demo.objects.models;

import com.example.demo.objects.data_transfer_objects.EmployeeForAlterationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmployeeTests {
    @Test
    void instantiateEntity(){
        int id = 1;
        String name = "Karel Jansen";
        int gender = 1;
        Date birthdate = Date.valueOf("1991-11-03");
        String job = "Software engineer";

        Employee entity = new Employee(
                id,
                true,
                name,
                gender,
                birthdate,
                job
        );

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(gender, entity.getGender());
        assertEquals(birthdate, entity.getBirthdate());
        assertEquals(job, entity.getJob());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        String name = null;
        int gender = 0;
        Date birthdate = null;
        String job = null;

        Employee entity = new Employee();

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(gender, entity.getGender());
        assertEquals(birthdate, entity.getBirthdate());
        assertEquals(job, entity.getJob());
    }

    @Test
    void instantiateEntityByDTO(){
        int id = 1;
        String name = "Karel Jansen";
        int gender = 1;
        Date birthdate = Date.valueOf("1991-11-03");
        String job = "Software engineer";

        EmployeeForAlterationDTO entityDTO = new EmployeeForAlterationDTO(
                id,
                true,
                name,
                gender,
                birthdate,
                job
        );

        Employee entity = new Employee(entityDTO);

        assertEquals(id, entity.getId());
        assertTrue(entity.isVisible());
        assertEquals(name, entity.getName());
        assertEquals(gender, entity.getGender());
        assertEquals(birthdate, entity.getBirthdate());
        assertEquals(job, entity.getJob());
    }

    @Test
    void fillEmptyEntity(){
        int id = 1;
        boolean visible = true;
        String name = "Karel Jansen";
        int gender = 1;
        Date birthdate = Date.valueOf("1991-11-03");
        String job = "Software engineer";

        Employee entity = new Employee();

        entity.setId(id);
        entity.setName(name);
        entity.setVisible(visible);
        entity.setBirthdate(birthdate);
        entity.setGender(gender);
        entity.setJob(job);

        assertEquals(id, entity.getId());
        assertTrue(entity.isVisible());
        assertEquals(name, entity.getName());
        assertEquals(gender, entity.getGender());
        assertEquals(birthdate, entity.getBirthdate());
        assertEquals(job, entity.getJob());
    }
}
