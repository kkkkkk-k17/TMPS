package TMPS.Lab_2.Builder;

public class PrefabricatedHouseBuilder implements HouseBuilder {
    private House house;

    public PrefabricatedHouseBuilder() {
        this.house = new House();
    }

    @Override
    public void buildFoundation() {
        house.setFoundation("Building fundament and other parts of house");
        System.out.println("PrefabricatedHouseBuilder: Structure complete...");
    }

    @Override
    public void buildStructure() {
        house.setStructure("Structural steels and wooden wall panels");
        System.out.println("PrefabricatedHouseBuilder: Structure complete...");
    }

    @Override
    public void buildRoof() {
        house.setRoof("Roofing sheets");
        System.out.println("PrefabricatedHouseBuilder: Roof complete...");
    }

    @Override
    public void paintHouse() {
        this.house.setPainted(true);
        System.out.println("PrefabricatedHouseBuilder: Painting done...");
    }

    @Override
    public void furnishHouse() {
        this.house.setFurnished(true);
        System.out.println("PrefabricatedHouseBuilder: Furnishing complete...");
    }

    @Override
    public House getHouse() {
        System.out.println("PrefabricatedHouseBuilder: Prefabricated house complete...");
        return this.house;
    }
}
