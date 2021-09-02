package pt.mteixeira.sb5recipeapp.converters;

import com.sun.istack.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pt.mteixeira.sb5recipeapp.commands.UnitOfMeasureCommand;
import pt.mteixeira.sb5recipeapp.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null) {
            return null;
        }

        return UnitOfMeasureCommand.builder()
                .id(unitOfMeasure.getId())
                .unit(unitOfMeasure.getUnit())
                .build();
    }
}
