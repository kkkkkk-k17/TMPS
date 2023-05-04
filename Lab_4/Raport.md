# Behavioral Design Patterns

### Obiect: TMPS
### Autor: Curcubet Ecaterina
#### Grupa: TI-204

----

## Teorie
Design patterns sunt soluții generice și testate în timp la probleme comune care apar în dezvoltarea de software. Behavioral Design Patterns se concentrează pe comunicarea între obiecte, mai precis asupra modului în care obiectele își transmit între ele mesaje și responsabilități.

Aceste tipare de proiectare permit obiectelor și entităților să comunice și să colaboreze într-un mod flexibil, oferind o mai mare extensibilitate și o mai mare ușurință în menținerea codului. În general, Behavioral Design Patterns ajută la separarea responsabilităților și la îmbunătățirea modularității aplicației.

Unele exemple de Behavioral Design Patterns includ:

- Chain of Responsibility: permite transmiterea responsabilității între obiecte într-o succesiune de verificări condiționale.
- Command: încapsulează o comandă ca un obiect, permițând astfel separarea logicii de apelare a comenzii de implementarea acesteia.
- Interpreter: definirea unui limbaj pentru a interpreta expresii și reguli.
- Iterator: oferă o modalitate simplă de a parcurge o colecție de obiecte fără a expune detaliile interne ale acesteia.
- Mediator: oferă un mediator între obiecte pentru a reduce cuplajul direct între ele.
- Memento: permite salvarea și restaurarea stării unui obiect la un moment dat.
- Observer: permite unor obiecte să urmărească și să reacționeze la schimbările de stare ale altor obiecte.
- State: permite unui obiect să își modifice comportamentul în funcție de starea internă.
- Strategy: permite schimbarea algoritmului sau a strategiei folosite de un obiect în timpul execuției.
- Template Method: permite definirea unui schelet algoritmic cu unele metode abstracte care vor fi implementate de subclasele respective.

## Implementarea

* Iterator

Interfața „Iterator” are 3 metode -current ,nxt, hasNext.

```
public interface Iterator {
    boolean hasNext();
    String current();
    void next();
}
```
În clasa "BrowseHistory" am creat o anumită istorie, iar ca stocare în memorie vom folosi un ArrayList pentru a le salva. Implementăm două metode de bază: "pop" și "push".
```
public class BrowseHistory {
    private List<String> urls = new ArrayList<>();

    public void push(String url) {
        urls.add(url);
    }

    public String pop() {
        var lastIndex = urls.size() - 1;
        var lastUrl = urls.get(lastIndex);

        urls.remove(lastUrl);
        return lastUrl;
    }
```
O altă clasă „ListIterator” care implementează „Iterator” și implementează aceste metode.
```
public class ListIterator implements Iterator {
        private BrowseHistory history;
        private int index;

        public ListIterator(BrowseHistory history) {
            this.history = history;
        }

        @Override
        public boolean hasNext() {
            return (index < history.urls.size());
        }

        @Override
        public String current() {
            return history.urls.get(index);
        }

        @Override
        public void next() {
            index++;
        }
    }
```
* Memento
  Clasa „EditorState” are un constructor care ia un șir și un getter.
```
public class EditorState {
    private final String content;

    public EditorState(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
```
Clasa „Editor” are o funcție care creează un nou „EditorState”.
Funcția restore returnează starea anterioară. De asemenea, se face un getter și setter.
```
public class Editor {
    private String content;

    public EditorState createState() {
        return new EditorState(content);
    }

    public void restore(EditorState state) {
        content = state.getContent();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
```

* Observer

În interfața „observator” adăugați 1 metodă.

```
public interface Observer {
    void update(int value);
}
```
„SpreadSheet” și „Chart” implementează metoda interfeței „Observer”.
```
public class SpreadSheet implements Observer {
    @Override
    public void update(int value) {
        System.out.println("Spreadsheet got notified." + value);
    }
}
```
Clasa „Subiect” are o listă cu observatori. În această clasă au metode de adăugare și eliminare.
Și pentru a notifica observatorii, le traversează și le actualizează pe fiecare.
```
public class Subject {
    public List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(int value) {
        for (var observer: observers)
            observer.update(value);

    }
```
„DataSource” extinde „Subject” și setValue setează valoarea pentru toți observatorii și notifică.
```
public class DataSource extends Subject {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        notifyObservers(value);
    }
}
```

* Template

„AuditTrail” are 1 metodă cu metoda de înregistrare.

```
public class AuditTrail {
    public void record() {
        System.out.println("Audit");
    }
}
```

Clasa abstractă „Task” are obiectul „AuditTrail”.
În metoda executați înregistrarea din auditTrail.
```
public abstract class Task {
    private AuditTrail auditTrail;

    public Task() {
        this.auditTrail = new AuditTrail();
    }

    public Task(AuditTrail auditTrail) {
        this.auditTrail = auditTrail;
    }

    public void execute() {
        auditTrail.record();

        doExecute();
    }
```
„TransferMoneyTask” extinde această clasă abstractă. Implementați „doExecute”.
```
public class TransferMoneyTask extends Task {
    @Override
    protected void doExecute() {
        System.out.println("Transfer Money");
    }
}
```

* Visitor

Interfață de operare cu 2 metode de aplicare.(one for heading other for anchor)

```
public interface Operation {
    void apply(HeadingNode heading);
    void apply(AnchorNode anchor);
}
```
În headingNode metoda execute.
```
public class HeadingNode implements HtmlNode {
    @Override
    public void execute(Operation operation) {
        operation.apply(this);
    }
}
```

HighlightOperation implementează Operation cu acele 2 metode.
```
public class HighlightOperation implements Operation {
    @Override
    public void apply(HeadingNode heading) {
        System.out.println("Highlight-heading");
    }

    @Override
    public void apply(AnchorNode anchor) {
        System.out.println("Highlight-ancor");
    }
}
```

## Concluzie
În concluzie, am înțeles cum funcționează modelele de proiectare comportamentale, am învățat cum să le implementăm, care este diferența dintre ele, când să le utilizăm și care sunt avantajele lor.