package pt.mteixeira.sb5recipeapp.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pt.mteixeira.sb5recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void shouldFindByUnit() {
        String actual = "teaspoon";
        Optional<UnitOfMeasure> unitOptional = unitOfMeasureRepository.findByUnit(actual);

        assertTrue(unitOptional.isPresent());
        assertEquals(actual, unitOptional.get().getUnit());
    }

    @Test
    public void shouldFindByUnitCup() {
        String actual = "cup";
        Optional<UnitOfMeasure> unitOptional = unitOfMeasureRepository.findByUnit(actual);

        assertTrue(unitOptional.isPresent());
        assertEquals(actual, unitOptional.get().getUnit());
    }
}