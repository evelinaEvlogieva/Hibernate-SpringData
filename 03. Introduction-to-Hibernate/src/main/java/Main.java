import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory =
                cfg.buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // Създаване на запис:
//        Student student = new Student();
//        student.setName("Mima");
//        student.setRegistrationDate(new Date());
//        session.save(student);

        // Принтиране на запис:
//        Student student = session.get(Student.class, 1);
//        System.out.println(student.toString());

        //Лист от обекти:
//        List<Student> studentList =
//                session.createQuery("FROM Student ").list();
//        for (Student student : studentList) {
//            System.out.println(student.toString());
//        }

        //Получаване на данни чрез Criteria:
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<Student> root = criteria.from(Student.class);
        criteria.select(root).where(builder.like(root.get("name"),"P%"));
        List<Student> studentList = 	session.createQuery(criteria).getResultList();
        for (Student student : studentList) {
            System.out.println(student.getName());
        }

        session.getTransaction().commit();
        session.close();
    }
}
