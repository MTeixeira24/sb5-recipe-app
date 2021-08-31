package pt.mteixeira.sb5recipeapp.domain;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class UnitOfMeasure extends BaseEntity {

    private String unit;

}
