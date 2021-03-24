package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Input;
import uz.pdp.springbootwarehouseproject.entity.InputProduct;
import uz.pdp.springbootwarehouseproject.entity.Product;
import uz.pdp.springbootwarehouseproject.payload.InputProductDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.InputProductRepository;
import uz.pdp.springbootwarehouseproject.repository.InputRepository;
import uz.pdp.springbootwarehouseproject.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public List<InputProduct> getInputProduct(){
        return inputProductRepository.findAll();
    }
    public InputProduct getInputProductById(Integer id){
        Optional<InputProduct> inputProductId = inputProductRepository.findById(id);
        if (!inputProductId.isPresent()) return new InputProduct();
        InputProduct inputProduct = inputProductId.get();
        return inputProduct;
    }
    public Result addInputProduct(InputProductDto inputProductDto){
        Optional<Product> productId = productRepository.findById(inputProductDto.getProductId());
        if (!productId.isPresent()) return new Result("Product not found", false);
        Optional<Input> inputId = inputRepository.findById(inputProductDto.getInputId());
        if (!inputId.isPresent()) return new Result("Input not found", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setProduct(productId.get());
        inputProduct.setInput(inputId.get());
        inputProductRepository.save(inputProduct);
        return new Result("Input Product saved", true);
    }
    public Result editInputProduct(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> inputProductId = inputProductRepository.findById(id);
        if (!inputProductId.isPresent()) return new Result("Input Product not found", false);
        Optional<Product> productId = productRepository.findById(inputProductDto.getProductId());
        if (!productId.isPresent()) return new Result("Product not found", false);
        Optional<Input> inputId = inputRepository.findById(inputProductDto.getInputId());
        if (!inputId.isPresent()) return new Result("Input not found", false);

        InputProduct editingInputProduct = inputProductId.get();
        editingInputProduct.setPrice(inputProductDto.getPrice());
        editingInputProduct.setExpireDate(inputProductDto.getExpireDate());
        editingInputProduct.setAmount(inputProductDto.getAmount());
        editingInputProduct.setProduct(productId.get());
        editingInputProduct.setInput(inputId.get());
        inputProductRepository.save(editingInputProduct);
        return new Result("Input Product edited", true);
    }
    public Result delInputProduct(Integer id){
        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (!byId.isPresent()) return new Result("Input product not found", false);
        inputProductRepository.deleteById(id);
        return new Result("Input product deleted", true);
    }
}
