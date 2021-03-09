//package salesDB.entities;
//
//import net.bytebuddy.dynamic.TypeResolutionStrategy;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.Set;
//
//@Entity
//public class Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String name;
//
//    private Double quantity;
//
//    private BigDecimal price;
//
//    @OneToMany(mappedBy = "product", targetEntity = Sale.class,
//    fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Sale> sales;
//
//}
