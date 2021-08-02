package ir.maktab56.repository.impl;

import ir.maktab56.base.repository.impl.BaseRepositoryImpl;
import ir.maktab56.domain.Order;
import ir.maktab56.domain.OrderDetail;
import ir.maktab56.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order, Long> {

    private final String SIZE = "select count(*) as number  from orders";

    private final String ADD_ORDER = "insert into orders(customer_id , orderDate ) values(?,?)";

    private final OrderDetailsRepositoryImpl repository;

    public OrderRepositoryImpl(Connection connection, OrderDetailsRepositoryImpl repository) {
        super(connection);
        this.repository = repository;
    }

    @Override
    public void add(Order element) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);
        preparedStatement.setLong(1, element.getCustomer().getId());
        preparedStatement.setTimestamp(2, element.getOrderDate());

        preparedStatement.executeUpdate();
    }


    public void showAllOrderedProduct(Long customerId) throws SQLException {

        List<OrderDetail> list = repository.getAll();

        ArrayList<Product> allOrder = new ArrayList<>();

        long number = list.stream().filter(x -> x.getCustomer().getId().equals(customerId)).count();

        if (number > 0) {
            System.out.println("order_id    product_id    product_number     price          orderDate             productName");
        } else {
            System.out.println("not exists ordered ...\n\n");
            return;
        }

        for (OrderDetail orderDetail : list) {
            if (orderDetail.getCustomer().getId().equals(customerId))

                System.out.printf("%-15d %-13d %-12d %-11d %-27s %s\n",
                        orderDetail.getId(), orderDetail.getList().get(0).getId(),
                        orderDetail.getList().get(0).getNumberOfProduct(),
                        orderDetail.getList().get(0).getPrice(),
                        orderDetail.getOrderDate(),
                        orderDetail.getList().get(0).getName());

        }

    }

    @Override
    public int size() {
        int counter = 0;
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SIZE);
            while (resultSet.next())
                counter = resultSet.getInt("number");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return counter;
    }
}
