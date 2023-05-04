# Stuctural Design Patterns

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

<b>Structural Design Patterns</b>
Structural design patterns are concerned with how classes and objects can be composed, to form larger structures.
The structural design patterns simplifies the structure by identifying the relationships.
These patterns focus on, how the classes inherit from each other and how they are composed from other classes.

* <b>Adapter</b>
  Adapter is a structural design pattern that allows objects with incompatible interfaces to collaborate.
* <b>Bridge</b>
  Bridge is a structural design pattern that lets you split a large class or a set of closely related classes into two separate hierarchies—abstraction and implementation—which can be developed independently of each other.
* <b>Decorator</b>
  Decorator is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.
* <b>Facade</b>
  Facade is a structural design pattern that provides a simplified interface to a library, a framework, or any other complex set of classes.
* <b>Flyweight</b>
  Flyweight is a structural design pattern that lets you fit more objects into the available amount of RAM by sharing common parts of state between multiple objects instead of keeping all of the data in each object.
## Objectives:

* Study and understand the Structural Design Patterns.
* As a continuation of the previous laboratory work, think about the functionalities that your system will need to provide to the user.
* Implement some additional functionalities using structural design patterns.


## Implementation description

* Decorator

Here is very simple.We need first of all to make an interface with some
 classes. After that, make some classes that implement this interface.

```
   public class SupportReport implements Report {

    @Override
    public Object[][] getReportData(String reportId) {
        return null;
    }

    @Override
    public String getFirstColumnData() {
        return "Support data";
    }

}
```

Now we need to create another class (abstract). In this class we have 
to private interface method which we created earlier.And in methods of 
this class use atributes of interface.

```
public abstract class ColumDecorator implements Report {
    private Report decoratedReport;

    public ColumDecorator(Report report){
        this.decoratedReport = report;
    }

    public String getFirstColumnData() {
        return decoratedReport.getFirstColumnData();
    }

    @Override
    public Object[][] getReportData(String reportId) {
        return decoratedReport.getReportData(reportId);
    }
}
```

And for simplicity add one more class which extends previous abstract class.
```
public class SupportLinkDecorator extends ColumDecorator{

    public SupportLinkDecorator(Report report) {
        super(report);
    }

    public String getFirstColumnData() {
        return addMoreInfo (super.getFirstColumnData()) ;
    }

    private String addMoreInfo(String data){
        return data  + " - support link - ";
    }
}
```
* Facade

Create an interface which will have for example 2 methods.
Now we make a concrete class implementation of this interface.


```
public class Iphone implements IMobileShop {
    @Override
    public void getMobileModelNumber() {
        System.out.println("The model is: IPhone 13");
    }

    @Override
    public void getMobilePrice() {
        System.out.println("The price is: 200 USD ");
    }
}

```

And in main function(ShopKeeper) constructor declare 2 private concrete implementation of previous interface.
Now implement method for each class.
```
public class ShopKeeper {
    private IMobileShop iphone;
    private IMobileShop samsung;

    public ShopKeeper() {
        iphone = new Iphone();
        samsung = new Samsung();
    }

    public void iphonePhoneSale() {
        iphone.getMobileModelNumber();
        iphone.getMobilePrice();
    }

    public void samsungPhoneSale() {
        samsung.getMobileModelNumber();
        samsung.getMobilePrice();
    }
}

```

* Flyweight

As usual make an interface with some methods.
Implement a concrete class of this interface.

```
public class MediumPen implements Pen {

    final BrushSize brushSize = BrushSize.MEDIUM;
    private String color = null;

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void draw(String content) {
        System.out.println("Drawing MEDIUM content in color : " + color);
    }
}
```

Now in PenFactory we have a hashmap.Create a function for each size of pen(medium,thick)
Set the color and put in hashmap this object.

```
public class PenFactory
{
    private static final HashMap<String, Pen> pensMap = new HashMap<>();
    public static Pen getThickPen(String color) {
        String key = color + "-THICK";
        Pen pen = pensMap.get(key);
        if(pen != null) {
            return pen;
        } else {
            pen = new ThickPen();
            pen.setColor(color);
            pensMap.put(key, pen);
        }
        return pen;
    }
    public static Pen getThinPen(String color) {
        String key = color + "-THIN";
        Pen pen = pensMap.get(key);
        if(pen != null) {
            return pen;
        } else {
            pen = new ThinPen();
            pen.setColor(color);
            pensMap.put(key, pen);
        }
        return pen;
    }

    public static Pen getMediumPen(String color) {
        String key = color + "-MEDIUM";
        Pen pen = pensMap.get(key);

        if(pen != null) {
            return pen;
        } else {
            pen = new MediumPen();
            pen.setColor(color);
            pensMap.put(key, pen);
        }
        return pen;
    }
}

```



* Adapter

Make an interface with a function.Make a concrete class with implemented function 
from interface.Also I added an additional method.

```
public class MediaAdapter implements MediaPlayer {

    public static final String VLC = "vlc";
    public static final String MP_4 = "mp4";

    private AdvancedMediaPlayer advancedMusicPlayer;
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase(VLC)) {
            advancedMusicPlayer = new VLCMusicPlayer();
        } else if (audioType.equalsIgnoreCase(MP_4)) {
            advancedMusicPlayer = new MP4MusicPlayer();
        }
    }

    @Override
    public void playMusic(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase(VLC)) {
            advancedMusicPlayer.playVlcPlayer(fileName);
        } else if (audioType.equalsIgnoreCase(MP_4)) {
            advancedMusicPlayer.playMp4Player(fileName);
        }
    }
}

```

In main class we implement that class.
```
public class Main {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.playMusic("mp3", "song1.mp3");
        audioPlayer.playMusic("mp4", "song2.mp4");
        audioPlayer.playMusic("vlc", "song3.vlc");
        audioPlayer.playMusic("xyz", "song4.avi");
    }
}
```


* Bridge

Now we create simple interface with 3 functions.

```
public interface Device {
    void turnOn();
    void turnOff();
    void setChannel(int number);
}

```

Make a class which implements that interface.For simplicity will just print some method.

```
public class SonyTV implements Device {
    @Override
    public void turnOn() {
        System.out.println("Sony turn on");
    }

    @Override
    public void turnOff() {
        System.out.println("Sony turn off");
    }

    @Override
    public void setChannel(int number) {
        System.out.println("Channel: " +  number);
    }
}
```

Create a class with a constructor which takes a class of that interface.And make
2 methods(turnOn and turnOff).

```
public  class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void turnOn() {
        device.turnOn();
    }

    public void turnOff() {
        device.turnOff();
    }

}```

## Conclusions
In conclusion we understood how structural design patterns work,
learned how to implement,what's the difference between them,when to use
and what are benefits.