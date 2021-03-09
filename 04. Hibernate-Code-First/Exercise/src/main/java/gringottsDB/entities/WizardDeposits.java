//package gringottsDB.entities;
//
//import com.sun.istack.NotNull;
//import org.hibernate.annotations.Check;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "wizard_deposits")
//@Check(constraints = "age >= 0")
//public class WizardDeposits {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "first_name", length = 50)
//    private String firstName;
//
//    @NotNull
//    @Column(name = "last_name", length = 60, nullable = false)
//    private String lastName;
//
//    @Column(name = "age", nullable = false)
//    private int age;
//
//    @Column(length = 1000)
//    private String notes;
//
//
//    public WizardDeposits(String firstName, String lastName, int age, String notes) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.age = age;
//        this.notes = notes;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//
//
//}
