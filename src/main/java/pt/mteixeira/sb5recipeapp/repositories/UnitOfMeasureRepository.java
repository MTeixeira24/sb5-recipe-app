package pt.mteixeira.sb5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.mteixeira.sb5recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByUnit(String unit);
}
