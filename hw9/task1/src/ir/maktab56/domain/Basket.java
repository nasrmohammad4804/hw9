package ir.maktab56.domain;

import ir.maktab56.base.domain.BaseEntity;

public class Basket extends BaseEntity<Long> {

    private Customer customer;
    private Product product;


    public Basket( Customer customer, Product product) {

        this.customer = customer;
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "customer=" + customer +
                ", product=" + product +
                '}';
    }
}

