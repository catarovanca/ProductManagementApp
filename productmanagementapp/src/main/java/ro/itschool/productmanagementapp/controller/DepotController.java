package ro.itschool.productmanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.itschool.productmanagementapp.entity.DepotModel;
import ro.itschool.productmanagementapp.service.DepotService;
import ro.itschool.productmanagementapp.service.exception.DepotNotFoundException;


import java.util.List;

@Controller
public class DepotController {

    @Autowired
    private DepotService depotService;

    @GetMapping("viewDepots")
    public String viewDepots(Model myModel){
        List<DepotModel> depotModelList = depotService.getDepots();
        myModel.addAttribute("depots",depotModelList);
        return "viewdepots";
    }

    @GetMapping("delete-depot/{id}")
    public String deleteDepot(@PathVariable("id") int id){
        depotService.deleteDepot(id);
        return  "redirect:/viewDepots";
    }
    @GetMapping("addDepot")
    public String addDepot(Model model){
        model.addAttribute("depot", new DepotModel());
        return "adddepot";
    }

    @PostMapping("add-new-depot")
    public String addNewDepot(DepotModel depot) {
        depotService.addDepot(depot);
        return "redirect:/viewDepots";
    }

    @GetMapping("edit-depot-page/{depotId}")
    public String editDepotPage(@PathVariable("depotId") int depotId,Model model) throws DepotNotFoundException {
        DepotModel depotModel = depotService.getDepot(depotId);
        model.addAttribute("depot",depotModel);
        return "editdepot";
    }

    @PostMapping("edit-depot")
    public String editDepot(DepotModel depotModel) throws DepotNotFoundException{
        depotService.updateDepot(depotModel);
        return "redirect:/viewDepots";
    }
    @GetMapping("search-depot-page")
    public String searchDepot(Model model){
        model.addAttribute("depot",new DepotModel());
        return "searchdepots";
    }

    @PostMapping("search-depot")
    public String searchDepotName(String name, Model model){
        model.addAttribute("depot",new DepotModel());
        List<DepotModel> foundDepots = depotService.searchByDepotName(name);
        model.addAttribute("depots", foundDepots);
        return "founddepots";
    }

}
