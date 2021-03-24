package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Output;
import uz.pdp.springbootwarehouseproject.entity.OutputProduct;
import uz.pdp.springbootwarehouseproject.entity.Product;
import uz.pdp.springbootwarehouseproject.payload.OutputProductDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.OutputProductRepository;
import uz.pdp.springbootwarehouseproject.repository.OutputRepository;
import uz.pdp.springbootwarehouseproject.repository.ProductRepository;

import java.awt.image.RescaleOp;
import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public List<OutputProduct> getOutputProduct(){
        return outputProductRepository.findAll();
    }
    public OutputProduct getOutputProduct(Integer id){
        Optional<OutputProduct> outputProductId = outputProductRepository.findById(id);
        if (!outputProductId.isPresent()) return new OutputProduct();
        OutputProduct outputProduct = outputProductId.get();
        return outputProduct;
    }
    public Result addOutputProduct(OutputProductDto outputProductDto){
        Optional<Product> productId = productRepository.findById(outputProductDto.getProductId());
        if (!productId.isPresent()) return new Result("Product not found", false);
        Optional<Output> outputId = outputRepository.findById(outputProductDto.getOutputId());
        if (!outputId.isPresent()) return new Result("Output not found", false);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(productId.get());
        outputProduct.setOutput(outputId.get());
        outputProductRepository.save(outputProduct);
        return new Result("Output Product added", true);
    }
    public Result editOutputProduct(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> outputProductId = outputProductRepository.findById(id);
        if (!outputProductId.isPresent()) return new Result("Output Product not found", false);
        Optional<Product> productId = productRepository.findById(outputProductDto.getProductId());
        if (!productId.isPresent()) return new Result("Product not found", false);
        Optional<Output> outputId = outputRepository.findById(outputProductDto.getOutputId());
        if (!outputId.isPresent()) return new Result("Output not found", false);

        OutputProduct outputProduct = outputProductId.get();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(productId.get());
        outputProduct.setOutput(outputId.get());
        outputProductRepository.save(outputProduct);
        return new Result("Output Product edited", true);
    }
    public Result delOutputProduct(Integer id){
        Optional<OutputProduct> outputProductId = outputProductRepository.findById(id);
        if (!outputProductId.isPresent()) return new Result("Output Product not found", false);
        outputProductRepository.deleteById(id);
        return new Result("Output Product deleted", true);
    }
}
