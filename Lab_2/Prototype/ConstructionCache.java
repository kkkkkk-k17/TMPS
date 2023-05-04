package TMPS.Lab_2.Prototype;

import java.util.HashMap;
import java.util.Map;

public class ConstructionCache {

    private static Map<String, Construction> constructionMap = new HashMap<>();

    public static Construction getConstruction(String constructionId) {
        Construction cachedConstruction = constructionMap.get(constructionId);
        return  cachedConstruction;
    }

    public static void loadCache() {
        Apartment apartment = new Apartment();
        apartment.setId("1");
        constructionMap.put(apartment.getId(), apartment);

        House house = new House();
        house.setId("2");
        constructionMap.put(house.getId(), house);
    }
}
