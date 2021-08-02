package ir.maktab56.base.repository;

import ir.maktab56.base.domain.BaseEntity;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<E extends BaseEntity<ID>,ID> {

    void add(E element) throws SQLException;

    E update(E element) throws SQLException;

    List<E> getAll() throws SQLException;

    E findById(ID id);


    int size() throws SQLException;

}
