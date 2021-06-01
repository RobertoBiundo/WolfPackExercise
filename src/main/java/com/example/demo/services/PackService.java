package com.example.demo.services;

import com.example.demo.contracts.services.IPackService;
import com.example.demo.helpers.logger.LoggerService;
import com.example.demo.objects.data_transfer_objects.PackDTO;
import com.example.demo.objects.data_transfer_objects.PackForAlterationDTO;
import com.example.demo.objects.models.Employee;
import com.example.demo.objects.models.Pack;
import com.example.demo.repositories.PackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.demo.helpers.util.Validator.emptyIfNull;

@Service
public class PackService implements IPackService {

    @Autowired
    private PackRepo packRepo;

    @Override
    public PackDTO getPack(int pack_id) {
        if(pack_id == 0){return new PackDTO();}

        Pack pack = packRepo.findById(pack_id);
        return new PackDTO(pack);
    }

    @Override
    public Iterable<PackDTO> getAllPacks() {
        List<Pack> packs = (List) packRepo.findAll();
        return this.convertListToDTO(packs);
    }

    @Override
    public boolean deletePack(int pack_id) {
        try{
            Pack pack = packRepo.findById(pack_id);

            if(pack == null){
                return false;
            }

            Iterator<Employee> employeeIterator = pack.getEmployees().iterator();
            while(employeeIterator.hasNext()){
                pack.removeEmployee(employeeIterator.next());
            }

            packRepo.deleteById(pack.getId());

            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    @Override
    public Pack createPack(PackForAlterationDTO packDTO) {
        try{
            Pack pack = new Pack(packDTO);

            // set id to 0 to prevent update of existing record on create
            pack.setId(0);

            return packRepo.save(pack);
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return null;
        }
    }

    @Override
    public PackDTO updatePack(PackForAlterationDTO packDTO) {
        try {
            Pack pack = new Pack(packDTO);
            packRepo.save(pack);
            return new PackDTO(pack);
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean removeEmployees(int pack_id, List<Employee> employee_list) {
        try{
            if(pack_id == 0 || employee_list == null){ return false; }

            Pack pack = packRepo.findById(pack_id);

            if(pack == null){ return false; }

            for(Employee employee : employee_list){
                pack.removeEmployee(employee);
            }

            return true;
        }
        catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean addEmployees(int pack_id, List<Employee> employee_list) {
        try{
            if(pack_id == 0 || employee_list == null){ return false; }

            Pack pack = packRepo.findById(pack_id);

            if(pack == null){ return false; }

            for(Employee employee : employee_list){
                pack.addEmployee(employee);
            }

            return true;
        }
        catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    private List<PackDTO> convertListToDTO(List<Pack> list){
        List<PackDTO> packDTOS = new ArrayList<>();

        for(Pack pack : emptyIfNull(list)){
            packDTOS.add(new PackDTO(pack));
        }

        return packDTOS;
    }
}
