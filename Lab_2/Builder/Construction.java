package TMPS.Lab_2.Builder;

public class Construction {
    private HouseBuilder houseBuilder;

    public Construction(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public House constructHouse() {
        this.houseBuilder.buildFoundation();
        this.houseBuilder.buildRoof();
        this.houseBuilder.buildStructure();
        this.houseBuilder.paintHouse();
        this.houseBuilder.furnishHouse();
        return this.houseBuilder.getHouse();
    }
}
