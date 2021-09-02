package pt.mteixeira.sb5recipeapp.converters;

import com.sun.istack.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pt.mteixeira.sb5recipeapp.commands.UnitOfMeasureCommand;
import pt.mteixeira.sb5recipeapp.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if (unitOfMeasureCommand == null) {
            return null;
        }

        return UnitOfMeasure.builder()
                .Id(unitOfMeasureCommand.getId())
                .unit(unitOfMeasureCommand.getUnit())
                .build();
    }

}
