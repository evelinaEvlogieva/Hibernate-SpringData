package universitySystem.entities;

import javax.persistence.*;
import javax.xml.bind.DataBindingException;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private int credits;

    @ManyToMany(targetEntity = Student.class)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Set<Student> students;


    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

}
