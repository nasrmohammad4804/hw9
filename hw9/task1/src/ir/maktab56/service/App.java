package ir.maktab56.service;

import ir.maktab56.database.InitialTable;
import ir.maktab56.domain.Customer;
import ir.maktab56.service.impl.BasketServiceImpl;
import ir.maktab56.service.impl.CustomerServiceImpl;
import ir.maktab56.service.impl.OrderServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    private Scanner scannerForInteger = new Scanner(System.in);


    private BasketServiceImpl basketService;
    private CustomerServiceImpl customerService;
    private OrderServiceImpl orderService ;

    
    public void start() {


        try {
            InitialTable initialTable = new InitialTable(Data.getData().getConnection());

           basketService = Data.getData().getBasketService();
           customerService=Data.getData().getCustomerService();
           orderService=Data.getData().getOrderService();

           basketService.initial();


            if (Data.getData().getProductRepository().getAll().isEmpty())
                Data.getData().getProductRepository().addDefaultProductToMarket();

            menu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("error occurred in  create table");
            start();
        }
    }

    private void menu() throws SQLException {
        System.out.println("1.register");
        System.out.println("2.login");
        System.out.println("3.exit");

        int input;

        while (true) {
            try {
                input = scannerForInteger.nextInt();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        switch (input) {

            case 1 -> {
                Customer custom = customerService.register();

                if (custom == null) {
                    System.out.println("this user with data already exists !!!");
                    menu();
                } else {
                    customerService.add(custom);
                    custom.setId((long) (customerService.size()));

                    System.out.println("id when customer register is : " + custom.getId());//-------------------
                    System.out.println("welcome " + custom.getName() + "   " + custom.getFamily() + ")))\n\n");
                    customerPanel(custom);
                }

            }
            case 2 -> {
                Customer customer = customerService.login();
                if (customer != null) {
                    System.out.println("welcome " + customer.getName() + "   " + customer.getFamily() + ")))\n\n");
                    customerPanel(customer);
                } else {
                    System.out.println("userName or password is wrong ...");
                }
            }
            case 3 -> {
                System.out.println("have nice day ##");
                System.exit(0);
            }
            default -> {
                System.out.println("your input invalid try again ");
                menu();
            }
        }


    }

    public void customerPanel(Customer customer) throws SQLException {

        System.out.println("1.add product  to basket");
        System.out.println("2.remove product from basket");
        System.out.println("3.show all product witch add to basket with number of their");
        System.out.println("4.show total price ");
        System.out.println("5.final confirm of customer ");
        System.out.println("6.all product");
        System.out.println("7.all product ordered !!!");
        System.out.println("8.back to home");

        switch (resultOfInputOnCustomerPanel()) {

            case 1 -> {
                basketService.addProduct(customer);
                customerPanel(customer);
            }
            case 2 -> {
                try {
                    basketService.removeProduct(customer);
                    customerPanel(customer);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            case 3 -> {
                basketService.showAllProductInBasket(customer);
                customerPanel(customer);
            }

            case 4 -> {
                basketService.showTotalPrice(customer);
                customerPanel(customer);
            }

            case 5 -> {
                basketService.showAllProductInBasket(customer);
                basketService.confirmForAddToOrder(customer);
                customerPanel(customer);
            }
            case 6 -> {
                basketService.showAllProduct();
                customerPanel(customer);
            }

            case 7 -> {
                try {

                    orderService.showAllOrderedProduct(customer.getId());
                    customerPanel(customer);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            case 8 -> menu();
        }
    }


    public int resultOfInputOnCustomerPanel() {
        int input;
        while (true) {
            try {
                System.out.println("enter your choice ...");
                input = scannerForInteger.nextInt();

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return input;

    }
}
