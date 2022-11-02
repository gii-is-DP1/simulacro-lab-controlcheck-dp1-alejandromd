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
    private ProductService ps;
    
    private static final String VIEW_FORMULARIO = "products/createOrUpdateProductForm";

    @GetMapping(path="/create")
    public String initForm(ModelMap modelMap){
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("productType",ps.findAllProductTypes());
        return VIEW_FORMULARIO;
    }

    @PostMapping(path="/create")
    public String processCreationForm(@Valid Product p, BindingResult result, ModelMap modelmap){
        String view= "welcome";
        if(result.hasErrors()){
            modelmap.addAttribute("product", p);
            modelmap.addAttribute("productType", ps.findAllProductTypes());
            return VIEW_FORMULARIO;
        }else{
            ps.save(p);
            modelmap.addAttribute("message", "se ha añadido correctamente");
        }
        return view;
    }
}
