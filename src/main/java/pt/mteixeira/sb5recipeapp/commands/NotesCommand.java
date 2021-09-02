package pt.mteixeira.sb5recipeapp.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotesCommand {
    private Long id;
    private String recipeNotes;
}
