package ast;

public class EmptyNode extends SimpleNode {
    public EmptyNode() {
        super(NodeType.EMPTY_STATEMENT);
    }
}
