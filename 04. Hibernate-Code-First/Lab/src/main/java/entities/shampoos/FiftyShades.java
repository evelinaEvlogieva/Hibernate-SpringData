package entities.shampoos;

import entities.labels.BasicLabel;

import java.math.BigDecimal;

public class FiftyShades extends BasicShampoo {
    public FiftyShades(BasicLabel basicLabel){
        super("Fifty Shades", new BigDecimal(6.69), Size.SMALL, basicLabel);
    }
}
