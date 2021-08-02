package ir.maktab56.service.impl;

import ir.maktab56.base.service.impl.BaseServiceImpl;
import ir.maktab56.domain.OrderDetail;
import ir.maktab56.repository.impl.OrderDetailsRepositoryImpl;

public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail,Long, OrderDetailsRepositoryImpl> {

    public OrderDetailServiceImpl(OrderDetailsRepositoryImpl repository) {
        super(repository);
    }
}
