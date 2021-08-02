package ir.maktab56.base.service.impl;

import ir.maktab56.base.domain.BaseEntity;
import ir.maktab56.base.repository.BaseRepository;
import ir.maktab56.base.service.BaseService;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseServiceImpl<E extends BaseEntity<ID>, ID, R extends BaseRepository<E, ID>> implements BaseService<E, ID> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }


    @Override
    public E findById(ID id) {
        return  repository.findById(id);
    }

    @Override
    public List<E> getAll() throws SQLException {
        return repository.getAll();
    }

    @Override
    public void add(E element) throws SQLException {
        repository.add(element);
    }

    @Override
    public E update(E element) throws SQLException {

        return repository.update(element);
    }


    @Override
    public int size() throws SQLException {
        return repository.size();
    }
}

