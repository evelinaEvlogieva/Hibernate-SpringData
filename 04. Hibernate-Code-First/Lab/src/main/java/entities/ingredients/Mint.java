package entities.ingredients;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Mint extends BasicIngredient {
    public Mint(){
        super("Mint", new BigDecimal(3.54));
    }


}
