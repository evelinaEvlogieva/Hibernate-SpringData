package entities.ingredients;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Lavender extends BasicIngredient {
    public Lavender(){
        super("Lavender", new BigDecimal(2));
    }
}
