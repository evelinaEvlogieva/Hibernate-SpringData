package entities.shampoos;

import entities.ingredients.BasicIngredient;
import entities.labels.BasicLabel;
import org.hibernate.engine.jdbc.Size;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public class BasicShampoo implements Shampoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal price;

    private String brand;

    @Enumerated
    private entities.shampoos.Size size;


    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "label", referencedColumnName = "id")
    private BasicLabel basicLabel;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "shampoos_ingredients",
    joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;


    protected BasicShampoo(){
        this.setIngredients(new HashSet<>());
    }

    public BasicShampoo (String brand, BigDecimal price, entities.shampoos.Size size, BasicLabel basicLabel){
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.basicLabel = basicLabel;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {

    }

    @Override
    public String getBrand() {
        return null;
    }

    @Override
    public void setBrand(String brand) {

    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public void setPrice(BigDecimal price) {

    }

    @Override
    public Size getSize() {
        return null;
    }

    @Override
    public void setSize(Size size) {

    }

    @Override
    public BasicLabel getLabel() {
        return null;
    }

    @Override
    public void setLabel(BasicLabel label) {

    }

    @Override
    public Set<BasicIngredient> getIngredients() {
        return new HashSet<>();
    }

    @Override
    public void setIngredients(Set<BasicIngredient> ingredients) {

    }
}
