package ir.maktab56.service.impl;

import ir.maktab56.base.service.impl.BaseServiceImpl;
import ir.maktab56.domain.Order;
import ir.maktab56.repository.impl.OrderRepositoryImpl;
import ir.maktab56.service.dto.OrderDTO;

import java.sql.SQLException;

public class OrderServiceImpl extends BaseServiceImpl<Order,Long, OrderRepositoryImpl> {

    private final OrderDetailServiceImpl orderDetailService;

    public OrderServiceImpl(OrderRepositoryImpl repository, OrderDetailServiceImpl orderDetailService) {
        super(repository);
        this.orderDetailService = orderDetailService;
    }

    @Override
    public void  add(Order element) throws SQLException {
        super.add(element);

    }
    public void showAllOrderedProduct(Long customerId) throws SQLException {

        repository.showAllOrderedProduct(customerId);
    }

    public void addOrder(OrderDTO dto) throws SQLException {

        add(dto.getOrder());
        //when order add to database our set id of orderDetails with repository.size();
        dto.getOrderDetail().setId((long)repository.size());
        orderDetailService.add(dto.getOrderDetail());

    }



}
