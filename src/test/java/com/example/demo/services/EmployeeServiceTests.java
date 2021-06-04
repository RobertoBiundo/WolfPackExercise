package com.example.demo.services;

import com.example.demo.objects.data_transfer_objects.EmployeeDTO;
import com.example.demo.objects.data_transfer_objects.EmployeeForAlterationDTO;
import com.example.demo.objects.models.Employee;
import com.example.demo.repositories.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTests {
    @Mock
    EmployeeRepo repo;

    @InjectMocks
    EmployeeService service;

    private final List<Employee> entityList = new ArrayList<>();

    private EmployeeServiceTests(){
        entityList.add(new Employee(1,true, "Karel Jansen",1, Date.valueOf("1991-11-03"),"Software engineer"));
        entityList.add(new Employee(2,true, "Sophie Jansen",2, Date.valueOf("1986-08-21"),"Helpdesk"));
        entityList.add(new Employee(3,true, "peter peterson",1, Date.valueOf("1965-11-09"),"Head management"));
        entityList.add(new Employee(4,false, "Clara van Hoof",2, Date.valueOf("1982-02-26"),"Software engineer"));
        entityList.add(new Employee(5,true, "Jan karelsen",1, Date.valueOf("1983-07-08"),"Software engineer"));
    }

    @Test
    void getEmployeeTest(){
        //Arrange
        Employee entity = this.entityList.get(4);

        //Prepare overwrites
        when(repo.findById(entity.getId())).thenReturn(entity);

        //Act
        EmployeeDTO resultEntity = service.getEmployee(entity.getId());
        EmployeeDTO expectedEntity = new EmployeeDTO(entity);

        //Assert
        assertEquals(expectedEntity.getName(), resultEntity.getName());
        assertEquals(expectedEntity.getGender(), resultEntity.getGender());
        assertEquals(expectedEntity.getBirthdate(), resultEntity.getBirthdate());
        assertEquals(expectedEntity.getJob(), resultEntity.getJob());
    }

    @Test
    void getEmployeeIdZeroTest(){
        //Arrange
        int id = 0;

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        EmployeeDTO resultEntity = service.getEmployee(id);
        EmployeeDTO expectedEntity = new EmployeeDTO();

        //Assert
        assertEquals(expectedEntity.getName(), resultEntity.getName());
        assertEquals(expectedEntity.getGender(), resultEntity.getGender());
        assertEquals(expectedEntity.getBirthdate(), resultEntity.getBirthdate());
        assertEquals(expectedEntity.getJob(), resultEntity.getJob());
    }

    @Test
    void getAllEmployeesTest(){
        //Arrange

        //Prepare overwrites
        when(repo.findAll()).thenReturn(entityList);

        //Act
        List<EmployeeDTO> resultEntity = (List) service.getAllEmployees();

        //Assert
        assertEquals(entityList.size(), resultEntity.size());
    }

    @Test
    void getAllEmployeesEmptyTest(){
        //Arrange

        //Prepare overwrites
        when(repo.findAll()).thenReturn(new ArrayList<>());

        //Act
        List<EmployeeDTO> resultEntity = (List) service.getAllEmployees();

        //Assert
        assertEquals(0, resultEntity.size());
    }

    @Test
    void deleteEmployeeTestSuccess(){
        //Arrange
        Employee entity = entityList.get(2);
        int employee_id = entity.getId();

        //Prepare overwrites
        when(repo.findById(employee_id)).thenReturn(entity);
        when(repo.save((Employee)notNull())).thenReturn(entity);

        //Act
        boolean result = service.deleteEmployee(entity.getId());

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteEmployeeTestEmployeeDoesNotExistFailure(){
        //Arrange
        int employee_id = 6;

        //Prepare overwrites
        when(repo.findById(employee_id)).thenReturn(null);
        when(repo.save((Employee) isNull())).thenThrow(new IllegalArgumentException("Target object must not be null"));

        //Act
        boolean result = service.deleteEmployee(employee_id);

        //Assert
        assertFalse(result);
    }

    @Test
    void createEmployeeTestSuccess(){
        //Arrange
        Employee entity = entityList.get(3);
        EmployeeForAlterationDTO entityDTO = new EmployeeForAlterationDTO(
                entity.getName(),
                entity.getGender(),
                entity.getBirthdate(),
                entity.getJob()
        );

        //Prepare overwrites
        when(repo.save((Employee)notNull())).thenReturn(entity);

        //Act
        boolean result = service.createEmployee(entityDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    void createEmployeeTestExceptionFailure(){
        //Prepare overwrites
        when(repo.save((Employee)isNull())).thenThrow(new IllegalArgumentException("Target object must not be null"));

        //Act
        boolean result = service.createEmployee(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void UpdateEmployeeTestSuccess(){
        //Arrange
        Employee entity = entityList.get(3);
        entity.setName("Karel");
        EmployeeForAlterationDTO entityDTO = new EmployeeForAlterationDTO(
                4,
                entity.isVisible(),
                entity.getName(),
                entity.getGender(),
                entity.getBirthdate(),
                entity.getJob()
        );

        //Prepare overwrites
        when(repo.save((Employee)notNull())).thenReturn(entity);

        //Act
        Boolean result = service.updateEmployee(entityDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    void UpdateEmployeeTestFailure(){
        //Arrange
        Employee entity = entityList.get(3);
        EmployeeForAlterationDTO entityDTO = new EmployeeForAlterationDTO(
                0,
                entity.isVisible(),
                null,
                entity.getGender(),
                entity.getBirthdate(),
                entity.getJob()
        );

        //Prepare overwrites
        when(repo.save((Employee)notNull())).thenThrow(new NullPointerException("name cannot be empty"));

        //Act
        boolean result = service.updateEmployee(entityDTO);

        //Assert
        assertFalse(result);
    }
}
