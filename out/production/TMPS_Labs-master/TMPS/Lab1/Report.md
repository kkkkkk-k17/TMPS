# Creational Design Patterns

### Object: SDTM
### Author: Berstean Stefan 

----

## Theory
Design patterns are typical solutions to common problems
in software design. Each pattern is like a blueprint
that you can customize to solve a particular
design problem in your code.

Patterns are a toolkit of solutions to common
problems in software design. They define
a common language that helps your team
communicate more efficiently.

Design patterns differ by their complexity, level of
detail and scale of applicability. In addition,
they can be categorized by their intent
and divided into three groups

<b>Creational Design Patterns</b>
These design patterns are all about class instantiation. This pattern can be further divided into class-creation patterns and object-creational patterns. While class-creation patterns use inheritance effectively in the instantiation process, object-creation patterns use delegation effectively to get the job done.

* <b>Abstract Factory</b>
Creates an instance of several families of classes

* <b>Builder</b>
Separates object construction from its representation
* <b>Factory Method</b>
Creates an instance of several derived classes
* <b>Prototype</b>
A fully initialized instance to be copied or cloned
* <b>Singleton</b>
A class of which only a single instance can exist
## Objectives:

* Understand what are design patterns
* What types of design patterns exist
* Implement 5 creational design pattens
* Understand what abstraction is


## Implementation description

* Singleton

Here is very simple.We need to initialize object with a value for 
example. And after that create a method which will check if our 
object instance is null. If so,create new object of this type.

```
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
```

* Factory Method

I divided my implementation in 2 folders.Product is about creating
objects of apartment or villa type.For that we have an interface house
where declare a method constructHouse. For simplicity, in concrete class
"Apartment" I've implemented that method and simply print in console "Building apartment".

```
public class Apartment implements House {
    @Override
    public void constructHouse() {
        System.out.println("Building apartment");
    }
}

```

In creator folder I've alse created an BaseHouse interface
which will have a method createHouse.
```
public interface BaseHouseFactory {
    House createHouse(String type);
}
```

And at the final created "HouseFactory" (concrete implementation)
which will have a method, and in dependence of which parameter takes,
returns object of that type.

```
public class HouseFactory implements BaseHouseFactory {
    @Override
    public House createHouse(String type) {
        House house;

        switch (type) {
            case "villa":
                house = new Villa();
                break;
            case "apartment":
                house = new Apartment();
                break;
            default:
                throw new IllegalArgumentException("No such house available");
        }
        house.constructHouse();
        return house;
    }
}
```

* Abstract Factory

For simplicity, I've created doors and floors of a house.They are pretty 
simmilar.First create interface (floor and door).After that make some implementations 
.In my case I just print which kind of door or floor is.

```
public class BlueDoor implements Door {
    @Override
    public void installingDoor() {
        System.out.println("You have installed blue door");
    }
}
```

Created another folder "factories" which will have an interface HouseFactory and 2
methods declared that return Floor and Door object.In concrete implementation, just return new objects 
of these types.

```
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
```


We need one more class which we'll initialize with HouseFactory object that created 
earlier.And in build method run methods for floor and door.

```
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
```

* Builder

Create a house object with parameters which you need(structure, foundation) for example
and initialize with setters.

```
public class House {
    private String foundation;
    private String structure;
    private String roof;
    private boolean furnished;
    private boolean painted;

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public void setPainted(boolean painted) {
        this.painted = painted;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "Foundation - " + foundation + "; Structure - "
                + structure + "; Roof - " + roof + "; Is Furnished? "
                + furnished + "; Is Painted? " + painted;
    }
}
```

We need an interface which will have all methods that we'll need and a house object;
```
public interface HouseBuilder {
    void buildFoundation();
    void buildStructure();
    void buildRoof();
    void paintHouse();
    void furnishHouse();

    House getHouse();

}
```

Create as many classes you need from HouseBuilder interface.

```
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
```

In Construction class we'll have in constructor an concrete HouseBuilder object.
Declare a method which will return a house object. Before returning, run methods from that HouseBuilder object.

```
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
```

* Prototype

Create abstract class with id,type field, and buld method.
Create a method clone which will look at parent class and try to clone ,else not supported
exception.

```
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
```

Create concrete method apartment or house and for simplicity in build method just print a
message to console, and set type to "appartment" or "house" in dependence of created class.

```

public class Apartment extends Construction {
    public Apartment() {
        type = "apartemnt";
    }

    @Override
    public void build() {
        System.out.println("Inside Apartemnt: build() method");
    }
}
```

In main class we'll make a hashmap which will store our objects.
We should have a method which take id of an object and return this object.
And another method  where we create and put our objects.

```
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
```

## Conclusions
In conclusion we understood how creational design patterns work,
learned how to implement,what's the difference between them,when to use
and what are benefits.