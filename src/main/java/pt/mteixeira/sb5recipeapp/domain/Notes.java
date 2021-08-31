package pt.mteixeira.sb5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"recipes"})
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
