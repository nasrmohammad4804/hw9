package ir.maktab56.domain;

import java.sql.Timestamp;
import java.util.List;

public class OrderDetail extends Order{

    private List<Product> list;

    public OrderDetail(long id, Customer customer, Timestamp orderDate, List<Product> list) {
        super(id, customer, orderDate);
        this.list = list;
    }

    public OrderDetail(Customer customer, Timestamp orderDate, List<Product> list) {
        super(customer, orderDate);
        this.list = list;
    }
    public OrderDetail(long id  , Timestamp orderDate, List<Product> list  ){
        super(id, orderDate);

        this.list=list;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "customer=" + customer +
                ", orderDate=" + orderDate +
                ", list=" + list +
                '}';
    }
}
