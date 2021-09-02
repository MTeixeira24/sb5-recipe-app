package pt.mteixeira.sb5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(exclude = {"recipe"}, callSuper = true)
@NoArgsConstructor
@Entity
public class Ingredient extends BaseEntity {

    private String description;
    private String amount;
    @ManyToOne
    private Recipe recipe;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Ingredient(String amount, String description, UnitOfMeasure unitOfMeasure) {
        this.amount = amount;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
    }
}
