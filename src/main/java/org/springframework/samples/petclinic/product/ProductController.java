package org.springframework.samples.petclinic.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    private static final String VIEWS_FORM = "products/createOrUpdateProductForm";

    @GetMapping(path = "/create")
    public String viewForm(ModelMap map){
        String view = VIEWS_FORM;
        map.addAttribute("product", new Product());
        map.addAttribute("productType", productService.findAllProductTypes());
        return view;
    }

    @PostMapping(path = "/create")
    public String createProduct(@Valid Product product, BindingResult res, ModelMap map){
        String view = "welcome";
        if(res.hasErrors()){
            map.addAttribute("product", product);
            map.addAttribute("productType", productService.findAllProductTypes());
            return VIEWS_FORM;
        }else{
            productService.save(product);
            map.addAttribute("message", "Product succesfully save");
        }
        return view;
    }
    
}
