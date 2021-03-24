package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Attachment;
import uz.pdp.springbootwarehouseproject.entity.Category;
import uz.pdp.springbootwarehouseproject.entity.Measurement;
import uz.pdp.springbootwarehouseproject.entity.Product;
import uz.pdp.springbootwarehouseproject.payload.ProductDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.AttachmentRepository;
import uz.pdp.springbootwarehouseproject.repository.CategoryRepository;
import uz.pdp.springbootwarehouseproject.repository.MeasurementRepository;
import uz.pdp.springbootwarehouseproject.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public List<Product> getProduct() {
        return productRepository.findAll();
    }
    public Product getProductById(Integer id) {
        Optional<Product> productId = productRepository.findById(id);
        if (!productId.isPresent()) return new Product();
        Product product = productId.get();
        return product;
    }
    public Result addProduct(ProductDto productDto) {

        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByNameAndCategoryId)
            return new Result("This kind of product is already exist", false);
        Optional<Category> categoryId = categoryRepository.findById(productDto.getCategoryId());
        if (!categoryId.isPresent())
            return new Result("Category not found", false);
        Optional<Attachment> photoId = attachmentRepository.findById(productDto.getPhotoId());
        if (!photoId.isPresent())
            return new Result("Photo not found", false);
        Optional<Measurement> measurementId = measurementRepository.findById(productDto.getMeasurementId());
        if (!measurementId.isPresent())
            return new Result("Measurement not found", false);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(categoryId.get());
        product.setPhoto(photoId.get());
        product.setMeasurement(measurementId.get());
        productRepository.save(product);
        return new Result("Product successfully added", true);
    }
    public Result editProduct(Integer id, ProductDto productDto) {
        Optional<Product> productId = productRepository.findById(id);
        if (!productId.isPresent()) return new Result("Product not found", false);
        Optional<Category> categoryId = categoryRepository.findById(productDto.getCategoryId());
        if (!categoryId.isPresent())
            return new Result("Category not found", false);
        Optional<Attachment> photoId = attachmentRepository.findById(productDto.getPhotoId());
        if (!photoId.isPresent())
            return new Result("Photo not found", false);
        Optional<Measurement> measurementId = measurementRepository.findById(productDto.getMeasurementId());
        if (!measurementId.isPresent())
            return new Result("Measurement not found", false);

        Product editingProduct = productId.get();
        editingProduct.setName(productDto.getName());
        editingProduct.setCategory(categoryId.get());
        editingProduct.setPhoto(photoId.get());
        editingProduct.setMeasurement(measurementId.get());
        productRepository.save(editingProduct);
        return new Result("Product info edited", true);
    }
    public Result delProduct(Integer id){
        Optional<Product> productId = productRepository.findById(id);
        if (!productId.isPresent()) return new Result("Product not found", false);
        productRepository.deleteById(id);
        return new Result("Product deleted", true);
    }

}
