package ir.maktab56.domain;

import ir.maktab56.base.domain.BaseEntity;

public class Basket extends BaseEntity<Long> {


    public Basket(Long basketID) {
        super(basketID);
    }

    private Customer customer;
    private Product product;

    public Basket(Long integer, Customer customer, Product product) {
        super(integer);
        this.customer = customer;
        this.product = product;
    }
}
