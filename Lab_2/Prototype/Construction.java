package TMPS.Lab_2.Prototype;

public abstract class Construction implements Cloneable {

    private String id;
    protected String type;

    abstract void build();

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();
        }

        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }

}
