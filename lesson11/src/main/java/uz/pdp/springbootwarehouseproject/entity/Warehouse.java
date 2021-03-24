package uz.pdp.springbootwarehouseproject.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.springbootwarehouseproject.entity.template.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Warehouse extends AbsEntity {
}
