package TMPS.Lab_4.Visitor;

public class Client {
    public static void main(String[] args) {
        var document = new HtmlDocument();

        document.add(new HeadingNode());
        document.add(new AnchorNode());

        document.execute(new HighlightOperation());
    }
}
