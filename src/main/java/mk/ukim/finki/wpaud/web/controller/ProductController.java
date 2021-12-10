package mk.ukim.finki.wpaud.web.controller;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.service.CategoryService;
import mk.ukim.finki.wpaud.service.ManufacturerService;
import mk.ukim.finki.wpaud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    // ovoj query string ni e od error porakata vo editProduct metodot
    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Product> productList = this.productService.findAll();
        model.addAttribute("products", productList);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    // /products/67   -- ova 67 e path variable
    // /products?id=78 -- ova 78 e query parameter i se prevzema so @RequestParam

    // mora da imas {} kaj id !!! za da kazes deka e PathVariable
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id); // dobar moment za AJAX :)
        return "redirect:/products";
    }

    @GetMapping("/add-form")
    public String addProductPage(Model model) {
        // ni treba modelot za da gi izlistame proizvoditelite i kategoriite
        List<Category> categoryList = this.categoryService.listCategories();
        List<Manufacturer> manufacturerList = this.manufacturerService.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("manufacturers", manufacturerList);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            List<Category> categoryList = this.categoryService.listCategories();
            List<Manufacturer> manufacturerList = this.manufacturerService.findAll();
            model.addAttribute("categories", categoryList);
            model.addAttribute("manufacturers", manufacturerList);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }

        return "redirect:/products?error=ProductNotFound";
    }

    // Ako ti se pojavi error 400 Bad Request proveri dali name atributite ti se sovpagjaat
    @PostMapping("/add")
    public String saveProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long category,
                              @RequestParam Long manufacturer)
    {

        // ovoj save metod ke pravi problemi ako probame da go editirame imeto na
        // veke postoecki produkt bidejki momentalno nemame ID kaj produktite
        // i prebaruvanjeto kaj removeIf se vrsi po ime...
        this.productService.save(name, price, quantity, category, manufacturer);
        return "redirect:/products";
    }

}

