package pt.mteixeira.sb5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"}, callSuper = true)
@ToString(exclude = "recipes", callSuper = true)
@Entity
@NoArgsConstructor
@SuperBuilder
public class Category extends BaseEntity {

    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
