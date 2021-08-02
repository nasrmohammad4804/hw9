package ir.maktab56.service;

public class Main {
    public static void main(String[] args) {

        App app=new App();

        app.start();

        try {
            Data.getData().getConnection().close();
        }catch (Exception e){
            System.out.println("error occurred when close of connection !!!");
        }
    }
}
