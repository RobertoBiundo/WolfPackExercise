package com.example.demo.services;

import com.example.demo.contracts.services.ILocationService;
import com.example.demo.helpers.logger.LoggerService;
import com.example.demo.objects.data_transfer_objects.LocationDTO;
import com.example.demo.objects.data_transfer_objects.LocationForAlterationDTO;
import com.example.demo.objects.models.Location;
import com.example.demo.repositories.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.TextResourceOrigin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.helpers.util.Validator.emptyIfNull;

@Service
public class LocationService implements ILocationService {

    @Autowired
    private LocationRepo locationRepo;

    @Override
    public LocationDTO getLocation(int location_id) {
        if(location_id == 0){return new LocationDTO();}

        Location location = locationRepo.findById(location_id);
        return new LocationDTO(location);
    }

    @Override
    public Iterable<LocationDTO> getAllLocations() {
        List<Location> locations = (List) locationRepo.findAll();
        return this.convertListToDTO(locations);
    }

    @Override
    public boolean deleteLocation(int employee_id) {
        try{
            Location location = locationRepo.findByEmployee(employee_id);

            if(location == null){
                return false;
            }

            locationRepo.deleteByEmployee(location.getEmployee());

            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean createLocation(LocationForAlterationDTO locationDTO) {
        try{
            Location location = new Location(locationDTO);

            // set id to 0 to prevent update of existing record on create
            location.setId(0);

            locationRepo.save(location);
            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateLocation(LocationForAlterationDTO locationDTO) {
        try {
            Location location = new Location(locationDTO);
            locationRepo.save(location);
            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    private List<LocationDTO> convertListToDTO(List<Location> list){
        List<LocationDTO> locationDTOS = new ArrayList<>();

        for(Location location : emptyIfNull(list)){
            locationDTOS.add(new LocationDTO(location));
        }

        return locationDTOS;
    }
}
