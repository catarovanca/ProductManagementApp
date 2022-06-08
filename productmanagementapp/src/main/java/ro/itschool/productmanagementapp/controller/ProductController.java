package ro.itschool.productmanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.itschool.productmanagementapp.entity.DepotModel;
import ro.itschool.productmanagementapp.entity.ProductModel;
import ro.itschool.productmanagementapp.service.DepotService;
import ro.itschool.productmanagementapp.service.ProductService;
import ro.itschool.productmanagementapp.service.exception.DepotNotFoundException;
import ro.itschool.productmanagementapp.service.exception.ProductNotFoundException;

import java.util.List;

@Controller
public class ProductController {

//    @Autowired
//    private ProductService productService;
    @Autowired
    private DepotService depotService;
    @Autowired
    private ProductService productService;


    @GetMapping("addProduct")
    public String addProduct(Model model){
        model.addAttribute("product",new ProductModel());
        List<DepotModel> depots = depotService.getDepots();
        model.addAttribute("depots",depots);
        return "addproduct";
    }

    @PostMapping("add-new-product")
    public String addNewProduct(ProductModel product) {
        productService.addProduct(product);
        return "redirect:/viewProducts";
    }

    @GetMapping("viewProducts")
    public String viewProducts(Model model){
       List<ProductModel> productModel = productService.getProducts();
       model.addAttribute("products",productModel);
       return "viewproducts";
    }

    @GetMapping("edit-product-page/{productId}")
    public String editProductPage(@PathVariable("productId") int productId,Model model) throws ProductNotFoundException {
      ProductModel productModel = productService.getProduct(productId);
      List<DepotModel> depot = depotService.getDepots();

      model.addAttribute("depots",depot);
      model.addAttribute("product",productModel);

      return "editproduct";
    }

    @PostMapping("edit-product")
    public String editProduct(ProductModel productModel) throws ProductNotFoundException{
        productService.updateProduct(productModel);
        return "redirect:/viewProducts";
    }

    @GetMapping("delete-product/{id}")
    public String deleteProduct(@PathVariable("id") int productId){
        productService.removeProduct(productId);
        return  "redirect:/viewProducts";
    }

    @GetMapping("search-product-page")
    public String searchProduct(Model model){
        model.addAttribute("product",new ProductModel());
        return "searchproduct";
    }

    @PostMapping("search-product")
    public String searchProductName(String name, Model model){
        model.addAttribute("product",new ProductModel());
        List<ProductModel> foundProducts = productService.searchByProductName(name);
        model.addAttribute("products", foundProducts);
        return "foundproducts";
    }

    @GetMapping("products-indepot-page/{depotId}")
    public String productsInDepot(@PathVariable("depotId") int depotId,Model model) throws DepotNotFoundException {
        List<ProductModel> productModelList = productService.findProductsinDepot(depotId);
        DepotModel depot = depotService.getDepot(depotId);
        model.addAttribute("depot",depot);
        model.addAttribute("products",productModelList);

        return "foundproductsindepot";
    }
}