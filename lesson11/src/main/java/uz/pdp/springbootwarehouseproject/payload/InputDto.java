package uz.pdp.springbootwarehouseproject.payload;

import lombok.Data;
import uz.pdp.springbootwarehouseproject.entity.Currency;
import uz.pdp.springbootwarehouseproject.entity.Supplier;
import uz.pdp.springbootwarehouseproject.entity.Warehouse;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class InputDto {

    private String date;

    private String factureNumber;

    private Integer warehouseId;

    private Integer supplierId;

    private Integer currencyId;
}
