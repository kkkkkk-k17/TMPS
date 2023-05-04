package TMPS.Lab_2.Factory.Creator;

import TMPS.Lab_2.Factory.Product.House;

public interface BaseHouseFactory {
    House createHouse(String type);
}
