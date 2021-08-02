package ir.maktab56.service.impl;

import ir.maktab56.base.service.impl.BaseServiceImpl;
import ir.maktab56.domain.Customer;
import ir.maktab56.repository.impl.CustomerRepositoryImpl;
import ir.maktab56.service.Data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, CustomerRepositoryImpl> {

    private static Scanner scanner = new Scanner(System.in);

    public CustomerServiceImpl(CustomerRepositoryImpl repository) {
        super(repository);

    }

    public static Customer register() {
        
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", "");
        map.put("family", "");
        map.put("userName", "");
        map.put("password", "");


        for (Map.Entry<String, String> str : map.entrySet()) {
            while (true) {
                try {
                    System.out.println("enter " + str.getKey());
                    map.put(str.getKey(), scanner.nextLine());

                    break;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        Customer c = new Customer(map.get("name"), map.get("family"), map.get("userName"), map.get("password"));
        return Data.getData().getCustomerRepository().registerCheck(c);
    }

    public static Customer login() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userName", "");
        map.put("password", "");
        for (Map.Entry<String, String> ptr : map.entrySet()) {
            while (true) {
                try {
                    System.out.println("enter " + ptr.getKey());
                    map.put(ptr.getKey(), scanner.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return Data.getData().getCustomerRepository().loginCustomer(map.get("userName"), map.get("password"));
    }



    /*@Override
    public Customer findById(Long aLong) {
        return null;
    }*/
}
