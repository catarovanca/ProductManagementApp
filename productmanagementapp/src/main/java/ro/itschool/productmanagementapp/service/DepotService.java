package ro.itschool.productmanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.itschool.productmanagementapp.entity.DepotModel;
import ro.itschool.productmanagementapp.entity.ProductModel;
import ro.itschool.productmanagementapp.repository.DepotRepository;
import ro.itschool.productmanagementapp.service.exception.DepotNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepotService {

    @Autowired
    private DepotRepository depotRepository;

    public List<DepotModel> getDepots() {
        return depotRepository.findAll();
    }

    public void deleteDepot(int id) {
        depotRepository.deleteById(id);
    }

    public void addDepot(DepotModel depot) {
        depotRepository.save(depot);

    }

    public void updateDepot(DepotModel modifiedDepot) throws DepotNotFoundException {

        DepotModel existingDepot = getDepot(modifiedDepot.getId());
        existingDepot.setName(modifiedDepot.getName());
        existingDepot.setLocation(modifiedDepot.getLocation());
        depotRepository.save(modifiedDepot);
    }

    public DepotModel getDepot(int depotId)throws DepotNotFoundException{
        Optional<DepotModel> optionalDepotModel = depotRepository.findById(depotId);
        if(optionalDepotModel.isEmpty()){
            throw  new DepotNotFoundException("Depot with id: " + depotId + " doesn't exist!");
        }
        DepotModel depotModel = optionalDepotModel.get();
        return depotModel;
    }
    public  List<DepotModel> searchByDepotName(String startWith){
        List<DepotModel> depotModels = depotRepository.findAll();
        List<DepotModel> depotResults = new ArrayList<>();
        for(DepotModel depotModel : depotModels){
            String depotName = depotModel.getName();
            if(depotName.startsWith(startWith)){
                depotResults.add(depotModel);
            }
        }
        return depotResults;
    }
}
