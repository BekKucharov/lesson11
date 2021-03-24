package uz.pdp.springbootwarehouseproject.payload;

import lombok.Data;
import uz.pdp.springbootwarehouseproject.entity.Category;

@Data
public class CategoryDto {

    private String name;

    private Integer parentCategoryId;

}
