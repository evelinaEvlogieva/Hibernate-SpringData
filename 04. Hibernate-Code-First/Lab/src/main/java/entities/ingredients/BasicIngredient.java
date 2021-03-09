package entities.ingredients;

import entities.shampoos.BasicShampoo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ingredients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ingredient_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicIngredient implements Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private BigDecimal price;

    @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.ALL)
    private List<BasicShampoo> shampoos;

    protected BasicIngredient() {};

    protected BasicIngredient(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public void setPrice(BigDecimal price) {

    }

    @Override
    public List<BasicShampoo> getShampoos() {
        return null;
    }

    @Override
    public void setShampoos(List<BasicShampoo> shampoos) {

    }
}
