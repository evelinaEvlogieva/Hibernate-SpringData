package entities.ingredients;

import javax.persistence.Column;
import java.math.BigDecimal;

public abstract class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredient {
    @Column(name = "chemical_formula")
    private String chemicalFormula;

    protected BasicChemicalIngredient(){};

    protected BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula){
        super(name, price);
        this.setChemicalFormula(chemicalFormula);
    }


}
