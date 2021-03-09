import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Engine implements Runnable {

    private final EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run() {
        this.increaseSalaries();
        //this.getLatest10Projects();
        //this.getEmployeeWithProject();
        //this.getAddressesWithEmployeesCount();
        //this.addAddress();
        // this.getEmployeesFromDepartments();
        // this.getEmployeesWith50000Salary();
        //this.checkIfTheNameIsInDB();
        //this.changeTownNames();
    }

    private void increaseSalaries() {
        this.entityManager.getTransaction().begin();

        List<Department> id = this.entityManager
                .createQuery("FROM Department WHERE name IN('Engineering'," +
                        " 'Tool Design', 'Marketing', 'Information Services')", Department.class)
                .getResultList();


        for (Department department : id) {
            Query query = this.entityManager
                    .createNativeQuery("UPDATE employees SET salary = salary * 1.12 " +
                            "WHERE department_id = ?");

           query.setParameter(1, department.getId());
           query.executeUpdate();


            List<Employee> employees = this.entityManager
                    .createQuery("FROM Employee WHERE department.name " +
                            "IN ('Engineering', 'Tool Design', 'Marketing'," +
                            " 'Information Services')", Employee.class)
                    .getResultList();

            employees.forEach(employee ->
                    System.out.printf("%s %s ($%s)%n",
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getSalary()));

        }

        this.entityManager.getTransaction().commit();

    }

    private void getLatest10Projects() {
        this.entityManager.getTransaction().begin();

        List<Project> projectsList = this.entityManager
                .createQuery("FROM Project ORDER BY startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

       projectsList.sort((f, s) -> f.getName().compareTo(s.getName()));

        for (Project project : projectsList) {
            System.out.printf("Project name: %s", project.getName());
            System.out.println();
            System.out.printf("   Project Description: %s%n" +
                    "   Project Start Date: %s%n" +
                    "   Project End Date: %s%n",
                    project.getDescription(),
                    project.getStartDate(),
                    project.getEndDate());
        }
        this.entityManager.getTransaction().commit();

    }

    private void getEmployeeWithProject() {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        this.entityManager.getTransaction().begin();
        Employee employee = this.entityManager
                .createQuery("FROM Employee WHERE id =: input", Employee.class)
                .setParameter("input", Integer.parseInt(input))
                .getSingleResult();

       Query projectsId = this.entityManager
                .createNativeQuery("SELECT p.name\n" +
                        "FROM employees_projects AS ep\n" +
                        "JOIN projects AS p\n" +
                        "ON ep.project_id = p.project_id\n" +
                        "WHERE employee_id = ?\n" +
                        "ORDER BY p.name");

       projectsId.setParameter(1, Integer.parseInt(input));

       List<String> list = projectsId.getResultList();

        System.out.println();
        System.out.printf("%s %s - %s%n",
                employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        for (String s : list) {
            System.out.println(s);
        }
        this.entityManager.getTransaction().commit();

    }

    private void getAddressesWithEmployeesCount() {
        this.entityManager.getTransaction().begin();

        List<Address> employees = this.entityManager
                .createQuery("FROM Address ORDER BY size(employees) DESC, town.id", Address.class)
                .setMaxResults(10)
                .getResultList();

        employees.forEach(e -> System.out.printf("%s, %s - %s employees",
                e.getText(), e.getTown().getName(), e.getEmployees().size()).println());


        this.entityManager.getTransaction().commit();


    }

    private void addAddress() {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();


        this.entityManager.getTransaction().begin();

        Town town = this.entityManager
                .createQuery("FROM Town WHERE id = 32", Town.class)
                .getSingleResult();

        Address address = new Address();
        address.setText("Vitoshka 15");
        address.setTown(town);

        this.entityManager.persist(address);
        this.entityManager.getTransaction().commit();

        this.entityManager.getTransaction().begin();
        Employee employee = this.entityManager
                .createQuery("FROM Employee WHERE lastName =: input", Employee.class)
                .setParameter("input", input)
                .getSingleResult();

        employee.setAddress(address);

        this.entityManager.flush();
        this.entityManager.getTransaction().commit();


    }

    private void getEmployeesFromDepartments() {
        this.entityManager.getTransaction().begin();

        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee WHERE department = 6 ORDER BY salary, id")
                .getResultList();

        for (Employee employee : employees) {
            System.out.printf("%s %s from Research and Development - $%.2f%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary());
        }

        this.entityManager.getTransaction().commit();

    }

    private void getEmployeesWith50000Salary() {
        this.entityManager.getTransaction().begin();
        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee WHERE salary > 50000")
                .getResultList();

        for (Employee employee : employees) {
            System.out.println(employee.getFirstName());
        }

        this.entityManager.getTransaction().commit();

    }

    private void checkIfTheNameIsInDB() {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();


        this.entityManager.getTransaction().begin();

        try {
            Employee employee = this.entityManager
                    .createQuery("FROM Employee WHERE CONCAT(firstName,' ',lastName) = :input", Employee.class)
                    .setParameter("input", input)
                    .getSingleResult();

            System.out.println("YES");
        } catch (Exception e) {
            System.out.println("NO");
        }

        this.entityManager.getTransaction().commit();
    }

    private void changeTownNames() {
        this.entityManager.getTransaction().begin();

        Query query = this.entityManager
                .createQuery("UPDATE Town SET name = LOWER(name) WHERE length(name) > 5");
        query.executeUpdate();

        this.entityManager.getTransaction().commit();
    }
}
