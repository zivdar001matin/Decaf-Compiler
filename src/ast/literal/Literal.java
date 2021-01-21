package ast.literal;

import ast.NodeType;
import ast.SimpleNode;
import ast.Type;

public abstract class Literal extends SimpleNode {

    private Type type;

    public Literal(Type type) {
        super(NodeType.LITERAL);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
