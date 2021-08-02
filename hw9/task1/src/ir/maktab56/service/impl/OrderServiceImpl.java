package ir.maktab56.service.impl;

import ir.maktab56.base.service.impl.BaseServiceImpl;
import ir.maktab56.domain.Order;
import ir.maktab56.repository.impl.OrderRepositoryImpl;

import java.sql.SQLException;

public class OrderServiceImpl extends BaseServiceImpl<Order,Long, OrderRepositoryImpl> {


    public OrderServiceImpl(OrderRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public void  add(Order element) throws SQLException {
        super.add(element);

    }
    public void showAllOrderedProduct(Long customerId) throws SQLException {

        repository.showAllOrderedProduct(customerId);
    }





}
