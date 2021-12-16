package mk.ukim.finki.servlet_thymeleaf.web.controller;

import mk.ukim.finki.servlet_thymeleaf.model.Category;
import mk.ukim.finki.servlet_thymeleaf.model.Manufacturer;
import mk.ukim.finki.servlet_thymeleaf.model.Product;
import mk.ukim.finki.servlet_thymeleaf.service.CategoryService;
import mk.ukim.finki.servlet_thymeleaf.service.Impl.ProductServiceImpl;
import mk.ukim.finki.servlet_thymeleaf.service.ManufacturerService;
import mk.ukim.finki.servlet_thymeleaf.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductServiceImpl productService,
                             CategoryService categoryService,
                             ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getProductsPage(@RequestParam(required = false) String error, Model model){

        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);

        return "products";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/add-form")
    public String addProduct(Model model){

        List<Category> categories = this.categoryService.listCategories();
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("manufacturers",manufacturers);

        return "add-product";

    }

    @GetMapping("/edit-form/{id}")
    public String editProduct(@PathVariable Long id, Model model){

        if(this.productService.findById(id).isPresent()){

            List<Category> categories = this.categoryService.listCategories();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            Product product = this.productService.findById(id).get();
            model.addAttribute("categories",categories);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("product",product);

            return "add-product";
        }

        return "redirect:/products?error=ProductNotFound";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long categoryId,
                              @RequestParam Long manufacturerId){

        this.productService.save(name, price, quantity, categoryId, manufacturerId);
        return "redirect:/products";
    }

}
