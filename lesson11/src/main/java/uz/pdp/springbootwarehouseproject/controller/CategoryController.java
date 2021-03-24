package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootwarehouseproject.entity.Category;
import uz.pdp.springbootwarehouseproject.payload.CategoryDto;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getCategory(){
        return categoryService.getCategory();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategory(categoryDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result editCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id){
        return categoryService.editCategory(categoryDto, id);
    }

    @DeleteMapping("/{id}")
    public Result delCategory(@PathVariable Integer id){
        return categoryService.delCategory(id);
    }


}
