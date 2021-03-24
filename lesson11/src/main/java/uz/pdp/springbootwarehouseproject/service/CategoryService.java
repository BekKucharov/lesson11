package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootwarehouseproject.entity.Category;
import uz.pdp.springbootwarehouseproject.payload.CategoryDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Integer id){
        Optional<Category> categoryId = categoryRepository.findById(id);
        if (!categoryId.isPresent()) return new Category();
        Category category = categoryId.get();
        return category;
    }

    public Result addCategory(CategoryDto categoryDto){

        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName) return new Result("This category is already exist", false);

        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> categoryId = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!categoryId.isPresent()) return new Result("This kind of parent category doesnt exist", false);
            category.setParentCategory(categoryId.get());
        }
        categoryRepository.save(category);
        return new Result("Category added successfully", true);
    }

    public Result editCategory(CategoryDto categoryDto, Integer id){
        Optional<Category> categoryId = categoryRepository.findById(id);
        if (!categoryId.isPresent()) return new Result("Category not found", false);
        Category editingCategory = categoryId.get();
        editingCategory.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> parentCategoryId = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!parentCategoryId.isPresent()) return new Result("This kind of parent category doesnt exist", false);
            editingCategory.setParentCategory(parentCategoryId.get());
        }
        categoryRepository.save(editingCategory);
        return new Result("Category edited", true);
    }

    public Result delCategory(Integer id){
        Optional<Category> categoryId = categoryRepository.findById(id);
        if (categoryId.isPresent()) return new Result("Category not found", false);
        categoryRepository.deleteById(id);
        return new Result("Category deleted", true);
    }
}
