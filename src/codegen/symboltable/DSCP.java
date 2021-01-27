package codegen.symboltable;

import codegen.ast.Node;
import codegen.ast.Type;

public class DSCP {
    private Type type;
    private Node node;
    private String value;
    private boolean isArgument;
    private boolean isFunction; // Shows that node is Function and can't be constant folded.

    public boolean isFunction() {
        return isFunction;
    }

    public void setFunction() {
        isFunction = true;
    }

    private int argumentPlace; // 1st, 2nd, 3rd, 4th

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
        this.isArgument = false;
    }

    public DSCP(Type type, Node node, boolean isFunction) {
        this.type = type;
        this.node = node;
        this.isArgument = false;
        this.isFunction = isFunction;
    }

    public void setArgumentTrue(int argumentPlace) {
        isArgument = true;
        this.argumentPlace = argumentPlace;
    }

    public int getArgumentPlace() {
        return argumentPlace;
    }

    public boolean isArgument() {
        return isArgument;
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
