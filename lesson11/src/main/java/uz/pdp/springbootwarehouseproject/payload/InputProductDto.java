package uz.pdp.springbootwarehouseproject.payload;

import lombok.Data;
import uz.pdp.springbootwarehouseproject.entity.Input;
import uz.pdp.springbootwarehouseproject.entity.Product;

@Data
public class InputProductDto {

    private Double price;
    private String expireDate;
    private Double amount;

    private Integer productId;
    private Integer inputId;

}
