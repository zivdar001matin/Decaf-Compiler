package codegen.symboltable;

import codegen.ast.Node;
import codegen.ast.Type;

public class DSCP {
    private Type type;
    private Node node;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private boolean isConstant;

    public DSCP(Type type, Node node) {
        this.type = type;
        this.node = node;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Node getNode() {
        return node;
    }

    public boolean isConstant() {
        return isConstant;
    }

    public void setConstant(boolean constant) {
        isConstant = constant;
    }
}
