package ir.maktab56.repository.impl;

import ir.maktab56.base.repository.impl.BaseRepositoryImpl;
import ir.maktab56.domain.Basket;
import ir.maktab56.domain.Product;
import ir.maktab56.repository.BasketRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BasketRepositoryImpl extends BaseRepositoryImpl<Basket, Long> implements BasketRepository {

    private final String ADD_BASKET = "insert into basket(product_id,number_product,customer_id ,price,status) values(?,?,?,?,'payment')";

    private final String SIZE = "select count(*) from basket";

    private final String DELETE_FROM_BASKET = "delete from basket where customer_id=? and product_id=? and status='payment'";

    /*private final String GET_ALL_PRODUCT_IN_BASKET_OF_CUSTOMER="select b.*, p.name as name from basket as b join product " +
            "as p on p.id=b.product_id where customer_id=? and status='payment' ";*/

    private final String GET_ALL_PRODUCT_IN_BASKET_OF_CUSTOMER = "select b.*, p.name as name from basket as b join product " +
            "as p on p.id=b.product_id where  customer_id=?  and  status='payment' ";


    private final String UPDATE = "update basket set  number_product=? where customer_id=? and product_id=? and status='payment'";

    private final String NUMBER_PRODUCT_BASKET = "select  number_product from basket where customer_id=? and product_id=?  and  status='payment' ";

    private final String CHANGE_STATUS = "update basket set status='complete' where customer_id=? and product_id=? ";


    public BasketRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void add(Basket element) throws SQLException {

        System.out.println(element.getCustomer().getId());
        System.out.println(element.getCustomer().getId());
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_BASKET);
        preparedStatement.setLong(1, element.getProduct().getId());
        preparedStatement.setInt(2, 1); //number_of product when add to table basket is : 1
        preparedStatement.setLong(3, element.getCustomer().getId());
        preparedStatement.setInt(4, element.getProduct().getPrice());

        preparedStatement.executeUpdate();
    }


    @Override
    public List<Product> getAll(Long customerId) {
        List<Product> list = new LinkedList<>();

        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCT_IN_BASKET_OF_CUSTOMER)) {

            statement.setLong(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                list.add(new Product(resultSet.getInt("product_id"), resultSet.getString("name"),
                        resultSet.getInt("number_product"), resultSet.getInt("price")));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Basket update(Basket element) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setInt(1, element.getProduct().getNumberOfProduct());
        preparedStatement.setLong(2, element.getCustomer().getId());
        preparedStatement.setLong(3, element.getProduct().getId());
        preparedStatement.executeUpdate();
        return element;
    }

    public int numberOfProductInBasketForCustomer(Long customer_id, Long product_id) throws SQLException {

        int counter = 0;

        PreparedStatement preparedStatement = connection.prepareStatement(NUMBER_PRODUCT_BASKET);

        preparedStatement.setLong(1, customer_id);
        preparedStatement.setLong(2, product_id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            counter = resultSet.getInt("number_product");
        }
        return counter;

    }

    public int checkNumberOfProductInBasket(Long customer_id) {
        int counter = 0;
        try (PreparedStatement statement = connection.prepareStatement("select  * from basket where customer_id=? and status='payment'  ")) {

            statement.setLong(1, customer_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                counter++;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return counter;

    }


    @Override
    public int size() {

        int counter = 0;
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SIZE);
            while (resultSet.next())
                counter++;

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return counter;
    }

    public void confirmBasket(Long customer_id, List<Product> list) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_STATUS);
        for (Product p : list) {

            preparedStatement.setLong(1, customer_id);
            preparedStatement.setLong(2, p.getId());
            preparedStatement.executeUpdate();
        }

    }

    public void delete(Basket x) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_BASKET)) {

            preparedStatement.setLong(1, x.getCustomer().getId());
            preparedStatement.setLong(2, x.getProduct().getId());
            preparedStatement.executeUpdate();
            System.out.println("successfully delete from basket");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
