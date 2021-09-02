package pt.mteixeira.sb5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@ToString(exclude = "recipes", callSuper = true)
@Entity
public class Category extends BaseEntity {

    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
