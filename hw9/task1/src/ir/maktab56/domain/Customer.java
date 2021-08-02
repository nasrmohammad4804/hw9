package ir.maktab56.domain;

import ir.maktab56.base.domain.BaseEntity;

public class Customer extends BaseEntity<Long> {

    private String name;
    private String family;
    private String userName;
    private String password;

    public Customer (String name,String family,String userName,String password){
        this.family=family;
        this.name=name;
        this.userName=userName;
        this.password=password;
    }

    public Customer(String name, String family, String userName, String password, long id) {
        super(id);
        this.name = name;
        this.family = family;
        this.userName = userName;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
