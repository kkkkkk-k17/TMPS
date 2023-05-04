package TMPS.Lab_2.AbstractFactory.factories;

import TMPS.Lab_2.AbstractFactory.doors.Door;
import TMPS.Lab_2.AbstractFactory.doors.GreenDoor;
import TMPS.Lab_2.AbstractFactory.floors.Floor;
import TMPS.Lab_2.AbstractFactory.floors.OneFloor;

public class OneFloorGreen implements HouseFactory {
    @Override
    public Floor buildFloor() {
        return new OneFloor();
    }

    @Override
    public Door installDoor() {
        return new GreenDoor();
    }
}
