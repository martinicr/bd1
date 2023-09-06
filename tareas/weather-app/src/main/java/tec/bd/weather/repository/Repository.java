package tec.bd.weather.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// C++ Templates
// Java, C# Generics

public interface Repository<T, ID> {

    /**
     * Find item by Id
     * @param id
     * @return Item
     */
    // "nullable"
    Optional<T> findById(ID id);
    List<T> findAll();
    T save(T t);

    void delete(ID id);

    T update(T source);
}
