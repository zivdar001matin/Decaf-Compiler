package ast.literal;

import ast.PrimitiveType;

public class BooleanLiteralNode extends Literal {
    private final boolean value;
    private final int intVal;

    public BooleanLiteralNode(boolean value) {
        super(PrimitiveType.BOOL);
        this.value = value;
        intVal = value ? 1 : 0;
    }

    public int getIntVal() { //todo
        return intVal;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return intVal + "";
    }
}
