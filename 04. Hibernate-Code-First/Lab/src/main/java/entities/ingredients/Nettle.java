package entities.ingredients;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Nettle extends BasicIngredient {
    public Nettle(){
        super("Nettle", new BigDecimal(6.12));
    }
}
