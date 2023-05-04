# Model de Proiectare Structural

### Obiect: TMPS
### Autor: Curcubet Ecaterina
#### Grupa: TI-204

----

## Teoria
<b>Structural Design Patterns</b> sunt tipare de proiectare software care se concentrează pe relațiile dintre obiecte și modul în care acestea sunt compuse în structuri mai mari. Acestea oferă o modalitate de a organiza obiectele și clasele într-un mod flexibil și extensibil, astfel încât acestea să poată fi ușor modificate sau extinse fără a afecta restul sistemului.

Structural Design Patterns se concentrează pe îmbunătățirea modului în care obiectele și clasele lucrează împreună, astfel încât să poată fi ușor integrate și extinse. Acestea includ tipare cum ar fi Adapter, Bridge, Composite, Decorator, Facade, Flyweight și Proxy. Fiecare dintre aceste tipare are un scop specific și poate fi utilizat pentru a rezolva probleme specifice de proiectare software.
* <b>Adapter</b>
   este un pattern structural de design care permite obiectelor cu interfete incompatibile sa colaboreze.
* <b>Bridge</b>
  Bridge este un pattern structural de design care va permite sa impartiti o clasa mare sau un set de clase strans legate in doua ierarhii separate - abstractizare si implementare - care pot fi dezvoltate independent una de cealalta.
* <b>Decorator</b>
  este un pattern structural de design care va permite sa atasati noi comportamente obiectelor prin plasarea acestor obiecte in interiorul unor obiecte wrapper speciale care contin comportamentele.
* <b>Facade</b>
  este un pattern structural de design care ofera o interfata simplificata catre o biblioteca, un framework sau orice alt set complex de clase.
* <b>Flyweight</b>
* este un pattern structural de design care va permite sa incadrati mai multe obiecte in cantitatea disponibila de RAM prin impartirea componentelor comune de stare intre mai multe obiecte, in loc sa pastrati toate datele in fiecare obiect.

## Implementarea

* Decorator

Aici este foarte simplu. Mai întâi trebuie să facem o interfață cu unele clase. După aceea, facem câteva clase care implementează această interfață.
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

Acum trebuie să creăm o altă clasă (abstractă). În această clasă avem o metodă privată de interfață pe care am creat-o anterior. Și în metodele acestei clase utilizăm atributele interfeței.

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
Pentru simplitate, adaugam inca o clasa care extinde clasa abstracta anterioara.
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

Am creat o interfață care va avea, de exemplu, 2 metode.
Acum vom crea o clasă concretă care implementează această interfață.


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

Și în constructorul funcției principale (ShopKeeper) declaram 2 implementări concrete private ale interfeței anterioare. Acum implementăm metoda pentru fiecare clasă.
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

Ca de obicei, creați o interfață cu câteva metode. Implementați o clasă concretă a acestei interfețe.

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

Acum, în PenFactory avem un hashmap. Creați o funcție pentru fiecare dimensiune a stiloului (mediu, gros). Setăm culoarea și punem obiectul în hashmap.

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

Creați o interfață cu o funcție. Creați o clasă concretă cu funcția implementată din interfață. De asemenea, am adăugat o metodă suplimentară.

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

În clasa main implementăm clasa data.
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

Acum creem o simpla interfata cu 3 functii

```
public interface Device {
    void turnOn();
    void turnOff();
    void setChannel(int number);
}

```

Creați o clasă care implementează acea interfață. Pentru simplitate, vom tipări doar câteva metode.
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

Se creează o clasă cu un constructor care primește o clasă a acelei interfețe. Se vor face 2 metode (turnOn și turnOff).

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
    }}

```

## Concluzie
În concluzie, am înțeles cum funcționează modelele de proiectare structurale, am învățat cum să le implementăm, care este diferența între ele, când să le utilizăm și care sunt beneficiile lor.