package ir.maktab56.service.dto;

import ir.maktab56.domain.Order;
import ir.maktab56.domain.OrderDetail;

public class OrderDTO {

    private Order order;

    private OrderDetail orderDetail;

    public OrderDTO(Order order, OrderDetail orderDetail) {
        this.order = order;
        this.orderDetail = orderDetail;
    }

    public Order getOrder() {
        return order;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }
}
