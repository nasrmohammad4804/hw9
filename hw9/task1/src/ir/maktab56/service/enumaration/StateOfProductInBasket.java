package ir.maktab56.service.enumaration;

public enum StateOfProductInBasket {

    UNABLE_TO_DELETE(0),COMPLETELY_DELETE(1),DECREASE_PRODUCT_NUMBER(2);
   private int number;

    StateOfProductInBasket(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
