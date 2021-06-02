package com.example.demo.controllers;

import com.example.demo.contracts.services.IEmployeeService;
import com.example.demo.objects.data_transfer_objects.EmployeeDTO;
import com.example.demo.objects.data_transfer_objects.EmployeeForAlterationDTO;
import com.example.demo.objects.data_transfer_objects.ResponseDTO;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private final IEmployeeService service = new EmployeeService();

    @GetMapping(path="/{employee_id}")
    public @ResponseBody ResponseEntity<ResponseDTO> getEmployee(@PathVariable int employee_id) {
        EmployeeDTO employee = service.getEmployee(employee_id);

        if (employee == null) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide a valid employee identifier."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, employee), HttpStatus.OK);
    }

    @GetMapping(path="")
    public  @ResponseBody ResponseEntity<ResponseDTO> getAllVisibleEmployees() {
        Iterable<EmployeeDTO> employees = service.getAllVisibleEmployees();

        return new ResponseEntity<>(new ResponseDTO(true, employees), HttpStatus.OK);
    }

    @GetMapping(path="/all")
    public  @ResponseBody ResponseEntity<ResponseDTO> getAllEmployees() {
        Iterable<EmployeeDTO> employees = service.getAllEmployees();

        return new ResponseEntity<>(new ResponseDTO(true, employees), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{employee_id}")
    public @ResponseBody ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable int employee_id) {
        if(employee_id == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "employee not found."), HttpStatus.NOT_FOUND);
        }

        boolean success = service.deleteEmployee(employee_id);

        if(!success){
            return new ResponseEntity<>(new ResponseDTO(false, "employee not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "employee has been deleted successfully."), HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> createEmployee(@RequestBody EmployeeForAlterationDTO employeeDTO) {
        if(Boolean.FALSE.equals(employeeDTO.validateForCreation())){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the creation"), HttpStatus.CONFLICT);
        }

        boolean result = service.createEmployee(employeeDTO);

        if (Boolean.FALSE.equals(result)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the creation of the employee."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "New employee has been created"), HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<ResponseDTO> updateEmployee(@RequestBody EmployeeForAlterationDTO employeeDTO) {
        if(Boolean.FALSE.equals(employeeDTO.validateForUpdate())){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the update"), HttpStatus.CONFLICT);
        }

        Boolean result = service.updateEmployee(employeeDTO);

        if (Boolean.FALSE.equals(result)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the update of the employee."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "employee has successfully been updated."), HttpStatus.OK);
    }
}
