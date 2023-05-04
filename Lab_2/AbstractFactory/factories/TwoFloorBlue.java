package TMPS.Lab_2.AbstractFactory.factories;

import TMPS.Lab_2.AbstractFactory.doors.BlueDoor;
import TMPS.Lab_2.AbstractFactory.doors.Door;
import TMPS.Lab_2.AbstractFactory.floors.Floor;
import TMPS.Lab_2.AbstractFactory.floors.TwoFloor;

public class TwoFloorBlue implements HouseFactory {
    @Override
    public Floor buildFloor() {
        return new TwoFloor();
    }

    @Override
    public Door installDoor() {
        return new BlueDoor();
    }
}
