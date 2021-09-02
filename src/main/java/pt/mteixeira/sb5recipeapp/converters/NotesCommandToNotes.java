package pt.mteixeira.sb5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pt.mteixeira.sb5recipeapp.commands.NotesCommand;
import pt.mteixeira.sb5recipeapp.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if (notesCommand == null) {
            return null;
        }

        return Notes.builder()
                .Id(notesCommand.getId())
                .recipeNotes(notesCommand.getRecipeNotes())
                .build();
    }
}
