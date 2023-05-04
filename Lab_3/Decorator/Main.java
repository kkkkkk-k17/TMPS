package TMPS.Lab_3.Decorator;

public class Main {
    public static void main(String[] args) {

        SupportPopupDecorator supportPopupDecoratored = new SupportPopupDecorator(new SupportLinkDecorator(new SupportReport()));
        System.out.println(supportPopupDecoratored.getFirstColumnData());
    }
}
