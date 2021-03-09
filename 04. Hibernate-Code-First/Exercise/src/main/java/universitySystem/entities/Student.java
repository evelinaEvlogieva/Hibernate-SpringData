package universitySystem.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person {

    @Column(name = "average_grade")
    private double averageGrade;

    private int attendance;


    @ManyToMany(targetEntity = Course.class, mappedBy = "students")
    private Set<Course> courses;

}
