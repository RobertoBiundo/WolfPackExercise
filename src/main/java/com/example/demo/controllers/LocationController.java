package com.example.demo.controllers;

import com.example.demo.contracts.services.ILocationService;
import com.example.demo.objects.data_transfer_objects.LocationDTO;
import com.example.demo.objects.data_transfer_objects.LocationForAlterationDTO;
import com.example.demo.objects.data_transfer_objects.ResponseDTO;
import com.example.demo.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private final ILocationService service = new LocationService();

    @GetMapping(path="/{employee_id}")
    public @ResponseBody
    ResponseEntity<ResponseDTO> getLocation(@PathVariable int employee_id) {
        LocationDTO location = service.getLocationByEmployee(employee_id);

        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide a valid location identifier."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, location), HttpStatus.OK);
    }

    @GetMapping(path="")
    public  @ResponseBody ResponseEntity<ResponseDTO> getAllLocations() {
        Iterable<LocationDTO> locations = service.getAllLocations();

        return new ResponseEntity<>(new ResponseDTO(true, locations), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{employee_id}")
    public @ResponseBody ResponseEntity<ResponseDTO> deleteLocation(@PathVariable int employee_id) {
        if(employee_id == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "location not found."), HttpStatus.NOT_FOUND);
        }

        boolean success = service.deleteLocation(employee_id);

        if(!success){
            return new ResponseEntity<>(new ResponseDTO(false, "location not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "location has been deleted successfully."), HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> createLocation(@RequestBody LocationForAlterationDTO locationDTO) {
        if(Boolean.FALSE.equals(locationDTO.validateForCreation())){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the update"), HttpStatus.CONFLICT);
        }

        LocationDTO location = service.getLocationByEmployee(locationDTO.getEmployee());

        boolean result;
        if(location == null){
            result = service.createLocation(locationDTO);
        }else{
            locationDTO.setId(location.getId());
            result = service.updateLocation(locationDTO);
        }

        if (Boolean.FALSE.equals(result)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the update of the location."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "location has successfully been updated."), HttpStatus.CREATED);
    }
}
