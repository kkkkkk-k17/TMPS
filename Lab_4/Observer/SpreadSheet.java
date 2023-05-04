package TMPS.Lab_4.Observer;

public class SpreadSheet implements Observer {
    @Override
    public void update(int value) {
        System.out.println("Spreadsheet got notified." + value);
    }
}
