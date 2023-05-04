package TMPS.Lab_2.AbstractFactory.factories;

import TMPS.Lab_2.AbstractFactory.doors.Door;
import TMPS.Lab_2.AbstractFactory.floors.Floor;

public interface HouseFactory {
    Floor buildFloor();
    Door installDoor();
}
