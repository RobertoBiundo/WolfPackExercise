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

    @GetMapping(path="/{location_id}")
    public @ResponseBody
    ResponseEntity<ResponseDTO> getLocation(@PathVariable int location_id) {
        LocationDTO location = service.getLocation(location_id);

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

    @DeleteMapping(path = "/{location_id}")
    public @ResponseBody ResponseEntity<ResponseDTO> deleteLocation(@PathVariable int location_id) {
        if(location_id == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "location not found."), HttpStatus.NOT_FOUND);
        }

        boolean success = service.deleteLocation(location_id);

        if(!success){
            return new ResponseEntity<>(new ResponseDTO(false, "location not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "location has been deleted successfully."), HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> createLocation(@RequestBody LocationForAlterationDTO locationDTO) {
        if(locationDTO.validateForCreation()){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the creation"), HttpStatus.CONFLICT);
        }

        boolean result = service.createLocation(locationDTO);

        if (Boolean.FALSE.equals(result)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the creation of the location."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "New location has been created"), HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<ResponseDTO> updateLocation(@RequestBody LocationForAlterationDTO locationDTO) {
        if(locationDTO.validateForUpdate()){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the update"), HttpStatus.CONFLICT);
        }

        Boolean result = service.updateLocation(locationDTO);

        if (Boolean.FALSE.equals(result)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the update of the location."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "location has successfully been updated."), HttpStatus.OK);
    }
}
