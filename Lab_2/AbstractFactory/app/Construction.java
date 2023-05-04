package TMPS.Lab_2.AbstractFactory.app;

import TMPS.Lab_2.AbstractFactory.doors.Door;
import TMPS.Lab_2.AbstractFactory.factories.HouseFactory;
import TMPS.Lab_2.AbstractFactory.floors.Floor;

public class Construction {
    private Floor floor;
    private Door door;

    public Construction(HouseFactory factory) {
        floor = factory.buildFloor();
        door = factory.installDoor();
    }

    public void build() {
        floor.constructFloor();
        door.installingDoor();
    }
}
