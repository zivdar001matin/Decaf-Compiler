package ast;

public class PrimitiveNode extends SimpleNode{
    private Type type;

    public PrimitiveNode(NodeType nodeType, Type type) {
        super(nodeType);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
