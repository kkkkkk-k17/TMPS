package TMPS.Lab_2.Singleton;

public class Singleton {

    private static Singleton instance;
    public String value;

    public Singleton(String value) {
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null)
                    instance = new Singleton(value);
            }
        }
        return instance;
    }
}
