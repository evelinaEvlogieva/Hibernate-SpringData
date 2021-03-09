//package salesDB.entities;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "store_location")
//public class StoreLocation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String locationName;
//
//    @OneToMany(mappedBy = "storeLocation", targetEntity = Sale.class,
//            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Sale> sales;
//}
