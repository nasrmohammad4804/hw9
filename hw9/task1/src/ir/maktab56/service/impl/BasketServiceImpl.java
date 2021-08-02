package ir.maktab56.service.impl;

import ir.maktab56.base.service.impl.BaseServiceImpl;
import ir.maktab56.domain.*;
import ir.maktab56.repository.impl.BasketRepositoryImpl;
import ir.maktab56.service.Data;
import ir.maktab56.service.enumaration.StateOfProductInBasket;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BasketServiceImpl extends BaseServiceImpl<Basket, Long, BasketRepositoryImpl> {

    private Scanner scanner = new Scanner(System.in);

    private ProductServiceImpl productService;
    private OrderServiceImpl orderService;
    private OrderDetailServiceImpl orderDetailService;

    public BasketServiceImpl(BasketRepositoryImpl repository) {
        super(repository);


    }
    public void initial(){
        productService=Data.getData().getProductService();
        orderService=Data.getData().getOrderService();
        orderDetailService=Data.getData().getOrderDetailService();
    }

    public void showAllProduct() {

        productService.showAllProduct();
    }

    private Product checkProductValid() {
        long productId;

        Product product;

        while (true) {
            try {

                System.out.println("enter productId ...");
                productId = scanner.nextLong();

                product = productService.findById(productId);

                if (product == null) {
                    throw new Exception("id not exists");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage() + " ..." + "try again");
            }
        }
        return product;
    }

    public void addProduct(Customer customer) throws SQLException {

        if (repository.checkNumberOfProductInBasket(customer.getId()) >= 5) {
            System.out.println("$$capacity is full if you want add product at least remove one already product$$\n\n ");
            return;
        }
        showAllProduct();
        Product product = checkProductValid();

        if (repository.numberOfProductInBasketForCustomer(customer.getId(), product.getId()) == 0) {
            repository.add(new Basket(customer, product));
            productService.update(product);
        } else {
            if (product.getNumberOfProduct() == 0)
                System.out.println("sorry dont have any of this product");

            else {
                int productNumber = product.getNumberOfProduct();
                productNumber++;

                product.setNumberOfProduct(productNumber);
                repository.update(new Basket(customer, product));

                --productNumber;
                productNumber--;

                product.setNumberOfProduct(productNumber);
                productService.update(product);
            }
        }

    }

    public void removeProduct(Customer customer) {

        Product product = checkProductValid();
        try {

            if (repository.numberOfProductInBasketForCustomer(customer.getId(), product.getId()) ==
                    StateOfProductInBasket.UNABLE_TO_DELETE.getNumber()) {
                System.out.println("!!!  impossible to removeProduct  !!!\n\n");
                return;

            } else if (repository.numberOfProductInBasketForCustomer(customer.getId(), product.getId()) ==
                    StateOfProductInBasket.COMPLETELY_DELETE.getNumber()) {

                repository.delete(new Basket(customer, product));

                int number = product.getNumberOfProduct();
                product.setNumberOfProduct(++number);
                productService.update(product);

            } else {

                int result = product.getNumberOfProduct();
                product.setNumberOfProduct(--result);
                repository.update(new Basket(customer, product));

                result++;

                product.setNumberOfProduct(++result);
                productService.update(product);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showTotalPrice(Customer customer) {

        long total = repository.getAll(customer.getId()).stream().map(x -> x.getNumberOfProduct() * x.getPrice()).reduce(Integer::sum).get();

        repository.getAll(customer.getId()).stream().map(x ->
                x.getId() + "    " + x.getNumberOfProduct() + "    " + x.getPrice() + "    " + "total price on productName  " +
                        x.getName() + " is : " + (x.getNumberOfProduct() * x.getPrice()))
                .forEach(System.out::println);

        System.out.println("--------------------------------------\n");
        System.out.println("total of all price is : " + total + "\n\n");

    }

    public void showAllProductInBasket(Customer customer) {

        if (repository.getAll(customer.getId()).isEmpty()) {
            System.out.println("basket is empty  \n\n");
            return;
        }

        System.out.println("id     productName     number       price        status\n");
        for (Product p : repository.getAll(customer.getId())) {
            System.out.printf("%-10d %-14s %-10d %-10d   %s\n", p.getId(), p.getName(), p.getNumberOfProduct(), p.getPrice(), "payment");
        }
        System.out.println("\n\n");
    }

    public void confirmForAddToOrder(Customer customer) {

        Scanner sc = new Scanner(System.in);

        if (repository.getAll(customer.getId()).isEmpty()) {
            System.out.println("basket is empty \n\n");
            return;
        }

        System.out.println("are you want to confirm basket ???\nif you want press yes else press no ");
        String result = sc.nextLine();

        try {

            switch (result) {

                case "yes" -> {
                    Data.getData().getConnection().setAutoCommit(false);
                    orderService.add(new Order(orderService.size(), customer, Timestamp.valueOf(LocalDateTime.now())));

                    orderDetailService.add(new OrderDetail(customer, Timestamp.valueOf(LocalDateTime.now()),
                            repository.getAll(customer.getId())));

                    repository.confirmBasket(customer.getId(), repository.getAll(customer.getId()));


                    Data.getData().getConnection().commit();
                }
                case  "no" -> System.out.println("final register filed !!! \n\n");

                default -> {
                    System.out.println("try again ...");
                    confirmForAddToOrder(customer);
                }
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
            e.printStackTrace();

            try {
                Data.getData().getConnection().rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }
        }
    }
}
