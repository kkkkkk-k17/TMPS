package TMPS.Lab_3.Facade;

public class Samsung implements IMobileShop {
    @Override
    public void getMobileModelNumber() {
        System.out.println("The model is: Galaxy 11");
    }

    @Override
    public void getMobilePrice() {
        System.out.println("The price is: 100 USD ");
    }
}
