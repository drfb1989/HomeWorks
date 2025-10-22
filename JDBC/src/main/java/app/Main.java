package app;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        EmployeeDAO dao = new EmployeeDAO();


        dao.initSchema();


        Employee e1 = dao.add(new Employee("John", 30, "Developer", 3000f));
        Employee e2 = dao.add(new Employee("Alice", 25, "QA",        2200f));
        Employee e3 = dao.add(new Employee("Bob",   35, "Manager",   4500f));

        System.out.println("Inserted:");
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);


        System.out.println("\nAll employees:");
        print(dao.findAll());


        e1.setSalary(3300f);
        dao.update(e1);
        System.out.println("\nAfter update John:");
        System.out.println(dao.findById(e1.getId()));


        dao.deleteById(e3.getId());
        System.out.println("\nAfter delete Bob:");
        print(dao.findAll());
    }

    private static void print(List<Employee> list) {
        list.forEach(System.out::println);
    }
}
