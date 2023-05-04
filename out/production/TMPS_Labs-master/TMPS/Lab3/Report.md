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

<b>Behavioral Design Patterns</b>
Behavioral design patterns deal with the interaction of objects in an object-oriented software application. This article discusses various behavioral design patterns that help objects cooperate and interact with each other. Behavioral Design Patterns provides a set of guidelines formulated by software pioneers that identify common communication patterns among objects and provide a way to solve commonly occurring object-interaction-related problems in software design.

* <b>Visitor</b>
Encapsulates an algorithm inside a class
* <b>Iterator</b>
Sequentially access the elements of a collection
* <b>Memento</b>
  Capture and restore an object's internal state
* <b>Observer</b>
  A way of notifying change to a number of classes
* <b>Template</b>
  Defer the exact steps of an algorithm to a subclass
## Objectives:

* By extending your project, implement at least 1 behavioral design pattern in your project.
*  Keep your files grouped by their responsibilities
* Document your work in a separate markdown file according to the requirements presented below


## Implementation description

* Iterator

Interface "Iterator" has 3 methods -current ,next, hasNext.

```
public interface Iterator {
    boolean hasNext();
    String current();
    void next();
}
```
In "BrowseHistory" class we created some kind of history,and like memory storage
we will use array list to store them.Implement 2 basic methods pop and push.

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

Another class "ListIterator" which implements "Iterator" and implement these methods.
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

"EditorState" class have a constructor which takes a string and a getter.
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
"Editor" class we have a function which creates new "EditorState".
Restore function returns previous state.Also make a getter and setter.

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

In "observer" interface add 1 method.

```
public interface Observer {
    void update(int value);
}
```

"SpreadSheet" and "Chart" implements method of "Observer" interface.
```
public class SpreadSheet implements Observer {
    @Override
    public void update(int value) {
        System.out.println("Spreadsheet got notified." + value);
    }
}
```
"Subject" class has a list with observers.In this class have add and remove methods.
And for notifying observers traverse them and update each.
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
"DataSource" extends "Subject" and in setValue set value for all observers and notify.
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

"AuditTrail" has 1 method with record method.

```
public class AuditTrail {
    public void record() {
        System.out.println("Audit");
    }
}
```

"Task" abstract class has "AuditTrail" object.
In method execute record from auditTrail.
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

"TransferMoneyTask" extends this abstract class.Implement "doExecute".
```
public class TransferMoneyTask extends Task {
    @Override
    protected void doExecute() {
        System.out.println("Transfer Money");
    }
}
```

* Visitor

Operation interface with 2 apply methods.(one for heading other for anchor)

```
public interface Operation {
    void apply(HeadingNode heading);
    void apply(AnchorNode anchor);
}
```
In headingNode execute method.
```
public class HeadingNode implements HtmlNode {
    @Override
    public void execute(Operation operation) {
        operation.apply(this);
    }
}
```

HighlightOperation is implementing Operation with those 2 methods.

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

## Conclusions
In conclusion we understood how behavioral design patterns work,
learned how to implement,what's the difference between them,when to use
and what are benefits.