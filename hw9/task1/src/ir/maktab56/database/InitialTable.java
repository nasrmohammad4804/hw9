package ir.maktab56.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitialTable {

    private static final String BASKET_REPO = "create table if not exists basket(id int primary key auto_increment ," +
            "product_id int , customer_id int , number_product int , price int , status varchar (50) ," +
            "foreign key(product_id) references product(id) , foreign key(customer_id) references customer(id)   )";


    private static final String CUSTOMER_REPO = "create table if not exists customer(id int primary key auto_increment," +
            "name varchar(50) not null , family varchar(50) not null , username varchar(50) not null unique ," +
            "password varchar(50) not null )";


    private static final String ORDER_DETAILS_REPO = "create table if not exists orderDetails(order_id int,product_id int,product_number int ,price int," +
            "foreign key(order_id) references orders(id) , foreign key(product_id ) references product(id)   )";


    private static final String ORDER_REPO = "create table if not exists orders(id int primary key auto_increment ," +
            "customer_id int,orderDate timestamp, foreign key(customer_id) references customer(id) )";


    private static final String PRODUCT_REPO = "create table if not exists product(id int primary key auto_increment ," +
            "name varchar(50) not null unique , number int not null , price int not null,category_name varchar(50) not null)";


    private Connection connection;

    public InitialTable(Connection connection) throws SQLException {

        this.connection = connection;


        Statement statement = this.connection.createStatement();

        statement.executeUpdate(CUSTOMER_REPO);
        statement.executeUpdate(PRODUCT_REPO);
        statement.executeUpdate(ORDER_REPO);
        statement.executeUpdate(ORDER_DETAILS_REPO);
        statement.executeUpdate(BASKET_REPO);

        statement.close();


    }
}
