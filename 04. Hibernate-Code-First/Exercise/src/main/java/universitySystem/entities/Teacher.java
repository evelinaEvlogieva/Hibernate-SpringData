package universitySystem.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    private String email;

    @Column(name = "salary_per_hour")
    private double salaryPerHour;

    @OneToMany(mappedBy = "teacher", targetEntity = Course.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Course> courses;
}
