package TMPS.Lab_4.Visitor;

import java.util.ArrayList;
import java.util.List;

public class HtmlDocument {

    private final List<HtmlNode> nodes = new ArrayList<>();

    public void add(HtmlNode node) {nodes.add(node);}

    public void execute(Operation operation) {
        for (var node: nodes)
            node.execute(operation);
    }
}
