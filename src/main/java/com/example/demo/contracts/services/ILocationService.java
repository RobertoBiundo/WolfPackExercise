package com.example.demo.contracts.services;

import com.example.demo.objects.data_transfer_objects.LocationDTO;
import com.example.demo.objects.data_transfer_objects.LocationForAlterationDTO;
import com.example.demo.objects.models.Location;

public interface ILocationService {
    LocationDTO getLocation(int location_id);
    Iterable<LocationDTO> getAllLocations();
    boolean deleteLocation(int employee_id);
    boolean createLocation(LocationForAlterationDTO locationDTO);
    boolean updateLocation(LocationForAlterationDTO locationDTO);
}
