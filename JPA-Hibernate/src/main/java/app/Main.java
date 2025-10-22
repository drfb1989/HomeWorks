package app;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hillel-persistence-unit");
        StudentDao dao = new StudentDao(emf);

        Student s = new Student("Artem", "Fedorenko", "artem@example.com");
        s.addHomework(new Homework("JPA mapping", LocalDate.now().plusDays(3), 0));
        s.addHomework(new Homework("DAO layer", LocalDate.now().plusDays(5), 0));

        dao.save(s);
        System.out.println("Saved: " + s);

        Student found = dao.findByEmail("artem@example.com");
        System.out.println("Found by email: " + found);

        found.setLastName("Ivanov");
        dao.update(found);
        System.out.println("Updated: " + dao.findById(found.getId()));

        boolean deleted = dao.deleteById(found.getId());
        System.out.println("Deleted? " + deleted);

        emf.close();
    }
}
