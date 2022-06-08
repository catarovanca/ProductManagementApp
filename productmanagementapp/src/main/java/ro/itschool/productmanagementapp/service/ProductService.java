package ro.itschool.productmanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.itschool.productmanagementapp.entity.ProductModel;
import ro.itschool.productmanagementapp.repository.DepotRepository;
import ro.itschool.productmanagementapp.repository.ProductRepository;
import ro.itschool.productmanagementapp.service.exception.ProductNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DepotRepository depotRepository;

    public void addProduct(ProductModel product){
        productRepository.save(product);
    }

    public List<ProductModel> getProducts() {
        List<ProductModel> models = productRepository.findAll();
        return models;
    }

    public ProductModel getProduct(int productId) throws ProductNotFoundException {
        Optional<ProductModel> optionalProductModel = productRepository.findById(productId);
        if(optionalProductModel.isEmpty()){
            throw new ProductNotFoundException("Product with id:" +  productId + "doesn't exist");
        }
        ProductModel productModel = optionalProductModel.get();
        return productModel;
    }
    public void updateProduct(ProductModel modifiedProduct) throws ProductNotFoundException {
        ProductModel existingProduct = getProduct(modifiedProduct.getId());
        existingProduct.setBrand(modifiedProduct.getBrand());
        existingProduct.setDepot(modifiedProduct.getDepot());
        existingProduct.setName(modifiedProduct.getName());
        existingProduct.setDescription(modifiedProduct.getDescription());
        existingProduct.setQuantity(modifiedProduct.getQuantity());
        productRepository.save(modifiedProduct);
    }


    public void removeProduct(int id){
        productRepository.deleteById(id);
    }

    public  List<ProductModel> searchByProductName(String startWith){
        List<ProductModel> productModels = productRepository.findAll();
        List<ProductModel> productResults = new ArrayList<>();
        for(ProductModel productModel : productModels){
            String productName = productModel.getName();
            if(productName.startsWith(startWith)){
                productResults.add(productModel);
            }
        }
        return productResults;
    }

    public List<ProductModel> findProductsinDepot(int depotId){
        List<ProductModel> productModelList = productRepository.searchByDepotID(depotId);
        return productModelList;
    }
}
