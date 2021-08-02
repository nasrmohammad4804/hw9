package ir.maktab56.repository.impl;

import ir.maktab56.base.repository.impl.BaseRepositoryImpl;
import ir.maktab56.domain.Customer;
import ir.maktab56.domain.OrderDetail;
import ir.maktab56.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsRepositoryImpl extends BaseRepositoryImpl<OrderDetail, Long> {

    private final String ALL_ORDER = " select  od.*, o.customer_id , orderDate,name from orders as o join orderdetails as od on " +
            " od.order_id=o.id join product as p on p.id=od.product_id  ";


    private final String ADD_ORDER_OF_DETAILS = "insert into orderDetails(order_id,product_id, product_number,price) values(?,?,?,?) ";


    public OrderDetailsRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void add(OrderDetail element) throws SQLException {

        PreparedStatement preparedStatement;
        for (Product p : element.getList()) {

            preparedStatement = connection.prepareStatement(ADD_ORDER_OF_DETAILS);

            preparedStatement.setLong(1, element.getId());
            preparedStatement.setLong(2, p.getId());
            preparedStatement.setInt(3, p.getNumberOfProduct());
            preparedStatement.setInt(4, p.getPrice());
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public List<OrderDetail> getAll() throws SQLException {

        List<OrderDetail> list = new ArrayList<>();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(ALL_ORDER);

        while (resultSet.next()) {

            ArrayList<Product> arr = new ArrayList<>();

            arr.add(new Product(resultSet.getLong("product_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("product_number"),
                    resultSet.getInt("price")));

            Customer customer = new Customer();
            customer.setId(resultSet.getLong("customer_id"));
            list.add(new OrderDetail(resultSet.getLong("order_id"),
                    customer,
                    resultSet.getTimestamp("orderDate"),
                    arr));
        }
        return list;
    }


    @Override
    public int size() throws SQLException {
        System.out.println("now no idea for implement of size method");
        return 0;
    }
}
