package ir.maktab56.repository;

import ir.maktab56.domain.Product;

import java.util.List;

public interface BasketRepository {

    List<Product> getAll(Long customerId);
}
