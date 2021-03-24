package uz.pdp.springbootwarehouseproject.payload;

import lombok.Data;
import uz.pdp.springbootwarehouseproject.entity.Output;
import uz.pdp.springbootwarehouseproject.entity.Product;

@Data
public class OutputProductDto {
    private Double amount;
    private Double price;
    private Integer productId;
    private Integer outputId;
}
