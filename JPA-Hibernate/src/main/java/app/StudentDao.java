package app;

import jakarta.persistence.*;

import java.util.List;

public class StudentDao implements GenericDao<Student, Long> {

    private final EntityManagerFactory emf;

    public StudentDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Student entity) {
        executeInTx(em -> em.persist(entity));
    }

    @Override
    public Student findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Student.class, id);
        }
    }

    @Override
    public Student findByEmail(String email) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> q = em.createQuery(
                    "select s from Student s where s.email = :email", Student.class);
            q.setParameter("email", email);
            List<Student> res = q.getResultList();
            return res.isEmpty() ? null : res.get(0);
        }
    }

    @Override
    public List<Student> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select s from Student s order by s.id", Student.class)
                    .getResultList();
        }
    }

    @Override
    public Student update(Student entity) {
        return executeInTxReturning(em -> em.merge(entity));
    }

    @Override
    public boolean deleteById(Long id) {
        return executeInTxReturning(em -> {
            Student s = em.find(Student.class, id);
            if (s == null) return false;
            em.remove(s);
            return true;
        });
    }

    private void executeInTx(EmConsumer c) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            try {
                c.accept(em);
                tx.commit();
            } catch (RuntimeException e) {
                if (tx.isActive()) tx.rollback();
                throw e;
            }
        }
    }

    private <R> R executeInTxReturning(EmFunction<R> f) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            try {
                R r = f.apply(em);
                tx.commit();
                return r;
            } catch (RuntimeException e) {
                if (tx.isActive()) tx.rollback();
                throw e;
            }
        }
    }

    @FunctionalInterface private interface EmConsumer { void accept(EntityManager em); }
    @FunctionalInterface private interface EmFunction<R> { R apply(EntityManager em); }
}
