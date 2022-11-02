package org.springframework.samples.petclinic.product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductsCheaperThan(double price) {
        return productRepository.findByPriceLessThan(price);
    }

    public ProductType getProductType(String typeName){ 
        return productRepository.findProductType(typeName);
    }

     public Product save(Product p){
        return productRepository.save(p);
     }

/*    public void save(Product p){
        productRepository.save(p);
    } 
*/
    public List<ProductType> findAllProductTypes(){
        return productRepository.findAllProductTypes();
    }
    
}
