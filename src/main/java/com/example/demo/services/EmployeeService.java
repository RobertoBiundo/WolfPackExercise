package com.example.demo.services;

import com.example.demo.contracts.services.IEmployeeService;
import com.example.demo.helpers.logger.LoggerService;
import com.example.demo.objects.data_transfer_objects.EmployeeDTO;
import com.example.demo.objects.data_transfer_objects.EmployeeForAlterationDTO;
import com.example.demo.objects.models.Employee;
import com.example.demo.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.helpers.util.Validator.emptyIfNull;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeDTO getEmployee(int employee_id) {
        if(employee_id == 0){return new EmployeeDTO();}

        Employee employee = employeeRepo.findById(employee_id);
        return new EmployeeDTO(employee);
    }

    @Override
    public Iterable<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = (List) employeeRepo.findAll();
        return this.convertListToDTO(employees);
    }

    @Override
    public Iterable<Employee> getEmployees(List<Integer> employee_ids) {
        return employeeRepo.findAllById(employee_ids);
    }

    @Override
    public boolean deleteEmployee(int employee_id) {
        try{
            Employee employee = employeeRepo.findById(employee_id);

            if(employee == null){
                return false;
            }

            employee.setVisible(false);

            employeeRepo.save(employee);

            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean createEmployee(EmployeeForAlterationDTO employeeDTO) {
        try{
            Employee employee = new Employee(employeeDTO);

            // set id to 0 to prevent update of existing record on create
            employee.setId(0);

            employeeRepo.save(employee);
            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateEmployee(EmployeeForAlterationDTO employeeDTO) {
        try {
            Employee employee = new Employee(employeeDTO);
            employeeRepo.save(employee);
            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    private List<EmployeeDTO> convertListToDTO(List<Employee> list){
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for(Employee employee : emptyIfNull(list)){
            employeeDTOS.add(new EmployeeDTO(employee));
        }

        return employeeDTOS;
    }
}
