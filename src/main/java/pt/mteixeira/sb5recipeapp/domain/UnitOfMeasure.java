package pt.mteixeira.sb5recipeapp.domain;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class UnitOfMeasure extends BaseEntity {

    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnitOfMeasure that = (UnitOfMeasure) o;
        return Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit);
    }
}
