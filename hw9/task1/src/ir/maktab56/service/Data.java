package ir.maktab56.service;

import ir.maktab56.repository.impl.*;
import ir.maktab56.service.impl.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class Data {

    private static   Data data =new Data();

    private Connection connection;

    private final CustomerRepositoryImpl customerRepository;
    private final ProductRepositoryImpl productRepository;
    private final OrderRepositoryImpl orderRepository;
    private final OrderDetailsRepositoryImpl orderDetailsRepository;
    private final BasketRepositoryImpl basketRepository;

    private final CustomerServiceImpl customerService;
    private final BasketServiceImpl basketService;
    private final OrderServiceImpl orderService;
    private final ProductServiceImpl productService;
    private final OrderDetailServiceImpl orderDetailService;


    private Data() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw9",
                    "root", "MohammadN@sr13804804");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        customerRepository = new CustomerRepositoryImpl(connection);
        productRepository = new ProductRepositoryImpl(connection);
        orderDetailsRepository = new OrderDetailsRepositoryImpl(connection);
        orderRepository = new OrderRepositoryImpl(connection, orderDetailsRepository);
        basketRepository = new BasketRepositoryImpl(connection);

        customerService = new CustomerServiceImpl(customerRepository);
        orderService = new OrderServiceImpl(orderRepository);
        productService = new ProductServiceImpl(productRepository);
        orderDetailService=new OrderDetailServiceImpl(orderDetailsRepository);
        basketService = new BasketServiceImpl(basketRepository);

    }

    public static Data getData() {

      return data;
    }

    public Connection getConnection() {
        return connection;
    }

    public CustomerRepositoryImpl getCustomerRepository() {
        return customerRepository;
    }

    public ProductRepositoryImpl getProductRepository() {
        return productRepository;
    }

    public OrderRepositoryImpl getOrderRepository() {
        return orderRepository;
    }

    public OrderDetailsRepositoryImpl getOrderDetailsRepository() {
        return orderDetailsRepository;
    }

    public BasketRepositoryImpl getBasketRepository() {
        return basketRepository;
    }

    public CustomerServiceImpl getCustomerService() {
        return customerService;
    }

    public BasketServiceImpl getBasketService() {
        return basketService;
    }

    public OrderServiceImpl getOrderService() {
        return orderService;
    }

    public ProductServiceImpl getProductService() {
        return productService;
    }

    public OrderDetailServiceImpl getOrderDetailService() {
        return orderDetailService;
    }
}
