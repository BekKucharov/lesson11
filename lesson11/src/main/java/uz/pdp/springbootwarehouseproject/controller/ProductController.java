package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.Product;
import uz.pdp.springbootwarehouseproject.payload.ProductDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping
    public List<Product> getProduct(){
        return productService.getProduct();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }
    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }
    @PutMapping("/{id}")
    public Result editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.editProduct(id, productDto);
    }
    @DeleteMapping("/{id}")
    public Result delProduct(@PathVariable Integer id){
        return productService.delProduct(id);
    }



}
