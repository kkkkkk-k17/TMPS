package TMPS.Lab_3.Flyweight;

public class Main
{
    public static void main(String[] args)
    {
        Pen yellowThinPen1 = PenFactory.getThickPen("YELLOW");
        yellowThinPen1.draw("Hello World !!");

        Pen yellowThinPen2 = PenFactory.getThickPen("YELLOW");
        yellowThinPen2.draw("Hello World !!");

        Pen blueThinPen = PenFactory.getThickPen("BLUE");
        blueThinPen.draw("Hello World !!");

        System.out.println(yellowThinPen1.hashCode());
        System.out.println(yellowThinPen2.hashCode());

        System.out.println(blueThinPen.hashCode());
    }
}
