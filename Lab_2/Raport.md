# Model de Proiectare Creațional

### Obiect: TMPS
### Autor: Curcubet Ecaterina
#### Grupa: TI-204

----

## Teoria
<b>Creational Design Patterns</b> (Model de Proiectare Creațional) este o categorie de modele de proiectare software care se concentrează pe procesul de creare a obiectelor și a claselor într-un mod eficient și flexibil. Aceste modele sunt utile pentru a reduce dependența dintre obiecte și pentru a îmbunătăți flexibilitatea și reutilizarea codului.

Modelele de proiectare creaționale pot fi împărțite în două subcategorii: modele de creare a claselor și modele de creare a obiectelor. Modelele de creare a claselor se concentrează pe procesul de creare a unei ierarhii de clase, în timp ce modelele de creare a obiectelor se concentrează pe crearea obiectelor în mod dinamic, la nevoie.

Exemple de modele de proiectare creaționale includ Singleton, Factory Method, Abstract Factory, Builder și Prototype. Fiecare dintre aceste modele abordează problema creării de obiecte și clase într-un mod specific, adaptat la nevoile și contextul particular al aplicației.

* <b>Abstract Factory</b>
  Creează o instanță a mai multor familii de clase
* <b>Builder</b>
  Separă construcția obiectului de reprezentarea acestuia
* <b>Factory Method</b>
  Creează o instanță pentru mai multe clase derivate
* <b>Prototype</b>
  O instanță complet inițializată care poate fi copiată sau clonată
* <b>Singleton</b>
  O clasă din care poate exista doar o singură instanță.

## Implementare

* Singleton

Aici este foarte simplu. Avem nevoie să inițializăm un obiect cu o valoare, de exemplu. Și după aceea, să creăm o metodă care va verifica dacă instanța obiectului nostru este nulă. În caz afirmativ, se va crea un nou obiect de acest tip.

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

Am împărțit implementarea mea în 2 foldere. Produsul este despre crearea de obiecte de tip apartament sau vilă. Pentru aceasta, avem o interfață "Casa" în care declarăm o metodă "constructHouse". Pentru simplitate, în clasa concretă "Apartament" am implementat această metodă și am afișat pur și simplu în consolă "Construim apartament".
```
public class Apartment implements House {
    @Override
    public void constructHouse() {
        System.out.println("Building apartment");
    }
}

```
În folderul "creator" am creat, de asemenea, o interfață "BaseHouse" care va avea o metodă "createHouse".
```
public interface BaseHouseFactory {
    House createHouse(String type);
}
```

Și în final am creat "HouseFactory" (implementarea concretă) care va avea o metodă, și în funcție de parametrul pe care îl primește, va returna obiectul de acel tip.

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

Pentru simplitate, am creat uși și podele pentru o casă. Acestea sunt destul de similare. Mai întâi, am creat o interfață (floor și door). După aceea, am realizat câteva implementări. În cazul meu, am afișat pur și simplu ce fel de ușă sau podea este.

```
public class BlueDoor implements Door {
    @Override
    public void installingDoor() {
        System.out.println("You have installed blue door");
    }
}
```

Am creat un alt folder "factories" care va avea o interfață "HouseFactory" și 2 metode declarate care returnează obiecte "Floor" și "Door". În implementarea concretă, vom returna pur și simplu obiecte noi de aceste tipuri.

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



Avem nevoie de încă o clasă pe care o vom inițializa cu obiectul "HouseFactory" creat anterior. Și în metoda "build" vom rula metodele pentru podea și ușă.

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

Creați un obiect de tip casă cu parametrii de care aveți nevoie (structură, fundație) și inițializați-l cu setteri.

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

Avem nevoie de o interfață care va avea toate metodele de care avem nevoie și un obiect de tip casă.
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

Creați atâtea clase câte aveți nevoie din interfața "HouseBuilder".

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
În clasa "Construction" vom avea în constructor un obiect concret de tip "HouseBuilder". Vom declara o metodă care va returna un obiect de tip casă. Înainte de a returna, vom rula metodele de pe acel obiect "HouseBuilder".

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

Creați o clasă abstractă cu câmpurile "id" și "type" și metoda "build". Creați o metodă "clone" care va căuta clasa părinte și va încerca să cloneze, altfel va arunca o excepție de tipul "NotSupportedException".

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

Creați o metodă concretă "apartment" sau "house" și pentru simplitate în metoda "build" tipăriți un mesaj în consolă și setați tipul la "apartment" sau "house" în funcție de clasa creată.
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

În clasa principală vom crea o hartă hash care va stoca obiectele noastre. Trebuie să avem o metodă care să ia ID-ul unui obiect și să returneze acest obiect. Și o altă metodă în care creăm și punem obiectele noastre în hashmap.
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

## Concluzie
Creational design patterns sunt o unealtă utilă în dezvoltarea software-ului, iar învățarea cum să le implementăm și înțelegerea diferențelor dintre ele poate ajuta la crearea unui cod mai modular și mai ușor de întreținut.

Este important să alegem tipul potrivit de pattern în funcție de necesitățile proiectului și să înțelegem beneficiile fiecăruia. Creational design patterns oferă o serie de avantaje precum crearea de obiecte mai flexibile și mai ușor de gestionat, eliminarea duplicării codului și îmbunătățirea modularității.

Prin aplicarea acestor pattern-uri, putem îmbunătăți calitatea și performanța software-ului, precum și productivitatea echipei de dezvoltare.