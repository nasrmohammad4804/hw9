package ir.maktab56.service;

import ir.maktab56.domain.Product;

import java.util.List;

public interface BasketService {

    List<Product> getAll(Long customerId);

}
