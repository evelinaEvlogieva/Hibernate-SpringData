//package salesDB.entities;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//public class Customer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String name;
//
//    private String email;
//
//    private String creditCardNumber;
//
//    @OneToMany(mappedBy = "customer", targetEntity = Sale.class,
//            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Sale> sales;
//}
