package TMPS.Lab_2.Prototype;

public class House extends Construction {
    public House() {
        type = "house";
    }
    @Override
    void build() {
        System.out.println("Inside House: build() method.");
    }
}
