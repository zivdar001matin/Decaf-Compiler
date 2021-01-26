package codegen.symboltable;

import codegen.ast.Node;
import codegen.ast.Type;

public class DSCP {
    private Type type;
    private Node node;
    private String value;
    private boolean isArgument;
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
