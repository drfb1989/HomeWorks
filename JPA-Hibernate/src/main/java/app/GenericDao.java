package app;

import java.util.List;

public interface GenericDao<T, ID> {
    void save(T entity);
    T findById(ID id);
    T findByEmail(String email);
    List<T> findAll();
    Student update(T entity);     // оставляю сигнатуру как в задании
    boolean deleteById(ID id);
}
