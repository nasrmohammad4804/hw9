package ir.maktab56.domain;

import ir.maktab56.base.domain.BaseEntity;

import java.sql.Timestamp;

public class Order extends BaseEntity<Long> {

    protected Customer customer;
    protected Timestamp orderDate;

    public Order(long id , Customer customer,Timestamp orderDate){
        super(id);
        this.customer=customer;
        this.orderDate=orderDate;
    }
    public Order(long id ,  Timestamp orderDate){
        super(id);
        this.orderDate=orderDate;
    }

    public Order(Customer customer,Timestamp orderDate){
        this.customer=customer;
        this.orderDate=orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }


}
