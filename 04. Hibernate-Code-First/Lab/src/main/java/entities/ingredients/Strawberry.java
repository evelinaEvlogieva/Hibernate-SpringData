package entities.ingredients;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Strawberry extends BasicIngredient {
    public Strawberry(){
        super("Strawberry", new BigDecimal(4.85));
    }
}
