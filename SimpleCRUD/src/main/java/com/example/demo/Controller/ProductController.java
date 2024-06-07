package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Model.Product;
import com.example.demo.Repository.productRepository;

public class ProductController {
	@GetMapping("/create")
	public String createAction(Model model) {
	    model.addAttribute("message", "Enter The Product Details");
	    return "create";
	}

	@PostMapping("/create")
	public String createActionProcess(Product productData, Model model) {
	    Product product = new Product();
	    product.setName(productData.getName());
	    product.setDescription(productData.getDescription());
	    product.setExpirydate(productData.getExpirydate());
	    product.setMRP(productData.getMRP());
	    productRepository.save(Product);

	    model.addAttribute("message", "The Product " + productData.getName() + " has been created successfully");
	    return "create";
	}
	
	@GetMapping("/all")
	public String getAllProducts(Model model) {
	    Iterable<Product> product = productRepository.findAll();
	    model.addAttribute("products", product);
	    return "list";
	}
	
	@GetMapping("/update/{id}")
	public String updateProduct(@PathVariable Integer id, Model model) {    
	    Optional<Product> optionalProductDetails = productRepository.findById(id);
	    Product productDetails = optionalProductDetails.get();

	    model.addAttribute("id", id);
	    model.addAttribute("productDetails", productDetails);
	    return "update";
	}

	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable Integer id, String name, String description, String expirydate, String MRP, Model model) {
	    Optional<Product> optionalProductDetails = ProductRepository.findById(id);
	    Product productDetails = optionalProductDetails.get();
	    productDetails.setName(name);
	    productDetails.setDescription(description);
	    productDetails.setExpirydate(expirydate);
	    productDetails.setMRP(MRP);
	    ProductRepository.save(productDetails);
	    return "redirect:/all";
	}

}
