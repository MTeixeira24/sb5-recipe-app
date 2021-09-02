package pt.mteixeira.sb5recipeapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"recipes"}, callSuper = true)
@SuperBuilder
@Entity
public class Notes extends BaseEntity {

    @OneToOne
    private Recipe recipes;
    @Lob
    private String recipeNotes;

    public Notes(String notesContents) {
        this.recipeNotes = notesContents;
    }

}
