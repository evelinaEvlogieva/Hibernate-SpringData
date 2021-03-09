package entities.ingredients;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class AmmoniumChloride extends BasicChemicalIngredient {
    public AmmoniumChloride(){
        super("Ammonium Chloride", new BigDecimal(0.59), "NH4Cl");
    }



    @Override
    public String getChemicalFormula() {
        return null;
    }

    @Override
    public void setChemicalFormula(String chemicalFormula) {

    }
}
