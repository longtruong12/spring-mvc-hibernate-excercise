package com.example.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mvc.entity.Product;
import com.example.mvc.services.BaseService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final BaseService<Product> productService;

    @Autowired
    public ProductController(BaseService<Product> productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listProducts(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/add-form";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products/list";
    }

    @GetMapping("/update")
    public String showUpdateProductForm(@RequestParam("productId") int id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "product/update-form";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products/list";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productId") int id) {
        productService.delete(id);
        return "redirect:/products/list";
    }
}
