package com.example.demo.controllers;

import com.example.demo.contracts.services.IEmployeeService;
import com.example.demo.contracts.services.IPackService;
import com.example.demo.objects.data_transfer_objects.PackDTO;
import com.example.demo.objects.data_transfer_objects.PackForAlterationDTO;
import com.example.demo.objects.data_transfer_objects.PackMembersDTO;
import com.example.demo.objects.data_transfer_objects.ResponseDTO;
import com.example.demo.objects.models.Employee;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/pack")
public class PackController {

    @Autowired
    private final IPackService service = new PackService();

    @Autowired
    private final IEmployeeService employeeService = new EmployeeService();

    @GetMapping(path="/{pack_id}")
    public @ResponseBody
    ResponseEntity<ResponseDTO> getPack(@PathVariable int pack_id) {
        PackDTO pack = service.getPack(pack_id);

        if (pack == null) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide a valid pack identifier."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, pack), HttpStatus.OK);
    }

    @GetMapping(path="")
    public  @ResponseBody ResponseEntity<ResponseDTO> getAllPacks() {
        Iterable<PackDTO> packs = service.getAllPacks();

        return new ResponseEntity<>(new ResponseDTO(true, packs), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{pack_id}")
    public @ResponseBody ResponseEntity<ResponseDTO> deletePack(@PathVariable int pack_id) {
        if(pack_id == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "pack not found."), HttpStatus.NOT_FOUND);
        }

        boolean success = service.deletePack(pack_id);

        if(!success){
            return new ResponseEntity<>(new ResponseDTO(false, "pack not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "pack has been deleted successfully."), HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> createPack(@RequestBody PackForAlterationDTO packDTO) {
        if(Boolean.FALSE.equals(packDTO.validateForCreation())){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the creation"), HttpStatus.CONFLICT);
        }

        Iterable<Employee> employee_list = null;
        if(packDTO.getEmployees() != null && !packDTO.getEmployees().isEmpty()){
                employee_list = employeeService.getEmployees(packDTO.getEmployees());
        }

        boolean result = service.createPack(packDTO, employee_list);

        if (Boolean.FALSE.equals(result)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the creation of the pack."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "New pack has been created"), HttpStatus.CREATED);
    }

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<ResponseDTO> addToPack(@RequestBody PackMembersDTO packMembersDTO) {
        if(packMembersDTO.getPack_id() == 0 || packMembersDTO.getEmployees().isEmpty()){
            return new ResponseEntity<>(new ResponseDTO(false, "please provide a valid pack identifier with employee identifiers."), HttpStatus.CONFLICT);
        }

        Iterable<Employee> employee_list = employeeService.getEmployees(packMembersDTO.getEmployees());

        boolean result = service.addEmployees(packMembersDTO.getPack_id(), employee_list);

        if (Boolean.FALSE.equals(result)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong while adding wolfs to the pack."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "New wolfs have been added to the pack"), HttpStatus.CREATED);
    }

    @PostMapping(path="/remove")
    public @ResponseBody ResponseEntity<ResponseDTO> removeFromPack(@RequestBody PackMembersDTO packMembersDTO) {
        if(packMembersDTO.getPack_id() == 0 || packMembersDTO.getEmployees().isEmpty()){
            return new ResponseEntity<>(new ResponseDTO(false, "please provide a valid pack identifier with employee identifiers."), HttpStatus.CONFLICT);
        }

        Iterable<Employee> employee_list = employeeService.getEmployees(packMembersDTO.getEmployees());

        boolean result = service.removeEmployees(packMembersDTO.getPack_id(), employee_list);

        if (Boolean.FALSE.equals(result)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong while removing wolfs from the pack."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "All supplied wolfs have been removed from the pack"), HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<ResponseDTO> updatePack(@RequestBody PackForAlterationDTO packDTO) {
        if(Boolean.FALSE.equals(packDTO.validateForUpdate())){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the update"), HttpStatus.CONFLICT);
        }

        Boolean result = service.updatePack(packDTO);

        if (Boolean.FALSE.equals(result)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the update of the pack."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "pack has successfully been updated."), HttpStatus.OK);
    }
}
