package ir.maktab56.repository.impl;

import ir.maktab56.base.repository.impl.BaseRepositoryImpl;
import ir.maktab56.domain.Product;
import ir.maktab56.mapper.ProductMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl extends BaseRepositoryImpl<Product, Long> {

    private final String GET_ALL_PRODUCT = "select * from product";

    private final String ADD_PRODUCT = "insert into product(name,number,price,category_name) values(?,?,?,?)";

    private final String CHECK_EXISTS = "select * from product where id=?";

    private final String UPDATE_PRODUCT = "update product set number=? where name=? ";


    public ProductRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void add(Product element) {

        System.out.println("product now not able to add");

    }

    @Override
    public Product update(Product element) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
        preparedStatement.setInt(1, element.getNumberOfProduct());
        preparedStatement.setString(2, element.getName());
        preparedStatement.executeUpdate();


        return findById(element.getId());

    }

    @Override
    public List<Product> getAll() {

        List<Product> myList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCT);

            while (resultSet.next()) {
                myList.add(ProductMapper.mapToObjectOfProduct(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return myList;
    }

    @Override
    public Product findById(Long id) {

        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EXISTS)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                product = ProductMapper.mapToObjectOfProduct(resultSet);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return product;
    }

    @Override
    public int size() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCT);
        int counter = 0;

        while (resultSet.next())
            counter++;

        return counter;
    }


    public void addDefaultProductToMarket() {

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            //insert into product(name,number,price,category_name) values(?,?,?,?)
            preparedStatement.setString(1, "radio");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 5000);
            preparedStatement.setString(4, "electronic");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "television");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 7000);
            preparedStatement.setString(4, "electronic");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "sport shoes");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 2000);
            preparedStatement.setString(4, "shoes");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "official");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 2500);
            preparedStatement.setString(4, "shoes");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "book");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 1000);
            preparedStatement.setString(4, "readable");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "magazine");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 700);
            preparedStatement.setString(4, "readable");
            preparedStatement.executeUpdate();

            System.out.println("add default product to market !!!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
