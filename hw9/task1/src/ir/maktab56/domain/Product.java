package ir.maktab56.domain;

import ir.maktab56.base.domain.BaseEntity;

public class Product extends BaseEntity<Long> {

    private String name;
    private int numberOfProduct;
    private int price;
    private String categoryName;

    public Product(long id, String name, int numberOfProduct, int price, String categoryName) {
        super(id);
        this.name = name;
        this.numberOfProduct = numberOfProduct;
        this.price = price;
        this.categoryName = categoryName;
    }

    public Product(long id, String name , int numberOfProduct, int price){
        super(id);
        this.name=name;
        this.numberOfProduct=numberOfProduct;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", numberOfProduct=" + numberOfProduct +
                ", price=" + price +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}

