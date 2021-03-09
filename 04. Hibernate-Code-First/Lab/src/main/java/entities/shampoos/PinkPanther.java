package entities.shampoos;

import entities.labels.BasicLabel;

import java.math.BigDecimal;

public class PinkPanther extends BasicShampoo {
    public PinkPanther(BasicLabel basicLabel){
        super("Pink Panther", new BigDecimal(8.50), Size.MEDIUM, basicLabel);
    }
}
