package ir.maktab56.base.repository.impl;

import ir.maktab56.base.domain.BaseEntity;
import ir.maktab56.base.repository.BaseRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>, ID>
        implements BaseRepository<E, ID> {

    final protected Connection connection;

    public BaseRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public E findById(ID id) {
        System.out.println("now  no idea for implement of findByID ");
        return null;
    }

    @Override
    public List<E> getAll() throws SQLException {
        System.out.println("now  no idea for implement of getAll");
        return null;
    }

    @Override
    public E update(E element) throws SQLException {

        System.out.println("now  no idea for implement of update");
        return null;
    }
}
