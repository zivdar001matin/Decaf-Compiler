package ast.literal;

import ast.PrimitiveType;

public class IntegerLiteralNode extends Literal {
    private final int value;

    public IntegerLiteralNode(String value) {
        super(PrimitiveType.INT);
        this.value = Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public int getValue() {
        return value;
    }
}
