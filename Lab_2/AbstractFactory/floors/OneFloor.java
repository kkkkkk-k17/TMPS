package TMPS.Lab_2.AbstractFactory.floors;

public class OneFloor implements Floor {
    @Override
    public void constructFloor() {
        System.out.println("You have created house with one floor");
    }
}
