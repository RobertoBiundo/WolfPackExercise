package com.example.demo.controllers;

import com.example.demo.contracts.services.IEmployeeService;
import com.example.demo.objects.data_transfer_objects.EmployeeDTO;
import com.example.demo.objects.data_transfer_objects.EmployeeForAlterationDTO;
import com.example.demo.objects.data_transfer_objects.ResponseDTO;
import com.example.demo.objects.models.Employee;
import com.example.demo.services.EmployeeService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEmployeeService MockService;

    private final List<Employee> entityList = new ArrayList<>();
    private final List<EmployeeDTO> entityDTOList = new ArrayList<>();

    ObjectMapper mapper = new ObjectMapper();

    private EmployeeControllerTests(){
        entityList.add(new Employee(1,true, "Karel Jansen",1, Date.valueOf("1991-11-03"),"Software engineer"));
        entityList.add(new Employee(2,true, "Sophie Jansen",2, Date.valueOf("1986-08-21"),"Helpdesk"));
        entityList.add(new Employee(3,true, "peter peterson",1, Date.valueOf("1965-11-09"),"Head management"));
        entityList.add(new Employee(4,false, "Clara van Hoof",2, Date.valueOf("1982-02-26"),"Software engineer"));
        entityList.add(new Employee(5,true, "Jan karelsen",1, Date.valueOf("1983-07-08"),"Software engineer"));

        entityDTOList.add(new EmployeeDTO(entityList.get(0)));
        entityDTOList.add(new EmployeeDTO(entityList.get(1)));
        entityDTOList.add(new EmployeeDTO(entityList.get(2)));
        entityDTOList.add(new EmployeeDTO(entityList.get(3)));
        entityDTOList.add(new EmployeeDTO(entityList.get(4)));

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }


    @Test
    void getEmployee() throws Exception {
        // Setup
        EmployeeDTO entity = entityDTOList.get(1);
        ResponseDTO responseDTO = new ResponseDTO(true, entity);

        // Configure UserService.getAllUsers(...).
        when(MockService.getEmployee(entity.getId())).thenReturn(entity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employee/" + entity.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(responseDTO), response.getContentAsString());
    }

    @Test
    void getAllVisibleEmployees() throws Exception {
        // Setup
        entityDTOList.remove(3);
        ResponseDTO responseDTO = new ResponseDTO(true, entityDTOList);

        String test = ((List<EmployeeDTO>) responseDTO.getBody()).get(1).getBirthdate().toString();
        // Configure UserService.getAllUsers(...).
        when(MockService.getAllVisibleEmployees()).thenReturn(entityDTOList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employee")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(responseDTO), response.getContentAsString());
    }

    @Test
    void getAllEmployees() throws Exception {
        // Setup
        ResponseDTO responseDTO = new ResponseDTO(true, entityDTOList);

        // Configure UserService.getAllUsers(...).
        when(MockService.getAllEmployees()).thenReturn(entityDTOList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employee/all")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(responseDTO), response.getContentAsString());
    }

    @Test
    void deleteEmployee() throws Exception {
        // Setup
        ResponseDTO responseDTO = new ResponseDTO(true, "employee has been deleted successfully.");
        int employee_id = entityList.get(1).getId();

        // Configure UserService.getAllUsers(...).
        when(MockService.deleteEmployee(employee_id)).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/employee/" + employee_id)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(responseDTO), response.getContentAsString());
    }

//    @PostMapping(path="")
    @Test
    void createEmployee() throws Exception {
        // Setup
        ResponseDTO responseDTO = new ResponseDTO(true, "New employee has been created");
        Employee employee = entityList.get(1);
        EmployeeForAlterationDTO employeeDTO = new EmployeeForAlterationDTO(
                employee.getName(),
                employee.getGender(),
                employee.getBirthdate(),
                employee.getJob()
        );

        // Configure UserService.getAllUsers(...).
        when(MockService.createEmployee((EmployeeForAlterationDTO)notNull())).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/employee")
                .content(mapper.writeValueAsString(employeeDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(responseDTO), response.getContentAsString());

    }

//    @PutMapping(path ="")
    @Test
    void updateEmployee() throws Exception {
        // Setup
        ResponseDTO responseDTO = new ResponseDTO(true, "employee has successfully been updated.");
        Employee employee = entityList.get(1);
        employee.setName("Tina turner");
        EmployeeForAlterationDTO employeeDTO = new EmployeeForAlterationDTO(
                employee.getId(),
                employee.isVisible(),
                employee.getName(),
                employee.getGender(),
                employee.getBirthdate(),
                employee.getJob()
        );

        // Configure UserService.getAllUsers(...).
        when(MockService.updateEmployee((EmployeeForAlterationDTO)notNull())).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/employee")
                .content(mapper.writeValueAsString(employeeDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(responseDTO), response.getContentAsString());

    }



}
