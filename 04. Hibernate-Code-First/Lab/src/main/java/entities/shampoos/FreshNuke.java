package entities.shampoos;

import entities.labels.BasicLabel;
import net.bytebuddy.implementation.bytecode.StackManipulation;


import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class FreshNuke extends BasicShampoo {

    public FreshNuke(BasicLabel basicLabel){
        super("Fresh Nuke", new BigDecimal(9.33), Size.LARGE, basicLabel);
    }
}
