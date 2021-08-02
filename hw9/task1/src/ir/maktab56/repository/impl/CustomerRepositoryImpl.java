package ir.maktab56.repository.impl;

import ir.maktab56.base.repository.impl.BaseRepositoryImpl;
import ir.maktab56.domain.Customer;
import ir.maktab56.mapper.CustomerMapper;

import java.sql.*;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer, Long> {

    private final String LOGIN_CUSTOMER = "select * from customer where username=? and password=? ";
    private final String REGISTER = "select * from customer where username=?";
    private final String ADD_CUSTOMER = "insert into customer(name,family,username,password) values(?,?,?,?)";
    private final String SIZE = "select * from customer";

    public CustomerRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void add(Customer element) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CUSTOMER);
            preparedStatement.setString(1, element.getName());
            preparedStatement.setString(2, element.getFamily());
            preparedStatement.setString(3, element.getUserName());
            preparedStatement.setString(4, element.getPassword());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    @Override
    public Customer update(Customer element) {
        return null;
    }

    @Override
    public int size() {
        try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet resultSet = statement.executeQuery(SIZE);
            int counter = 0;

            if (resultSet.last())
                counter = resultSet.getRow();

            return counter;

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            return 0;
        }
    }

    public Customer registerCheck(Customer customer) {

        return getCustomerForRegister(customer);
    }

    private Customer getCustomerForRegister(Customer cus) {

        int counter = 0;
        try (PreparedStatement statement = connection.prepareStatement(REGISTER)) {
            statement.setString(1, cus.getUserName());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                counter++;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        if (counter > 0)
            return null;

        return cus;


    }

    public Customer loginCustomer(String userName, String password) {

        return getCustomerForLogin(userName, password);
    }

    private Customer getCustomerForLogin(String userName, String password) {

        Customer customer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_CUSTOMER);) {

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer = CustomerMapper.mapToObjectOfCustomer(resultSet);
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return customer;
    }
}
