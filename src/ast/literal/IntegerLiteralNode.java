package ast.literal;

import ast.PrimitiveType;

public class IntegerLiteralNode extends Literal {
    private final int value;

    public IntegerLiteralNode(int value) {
        super(PrimitiveType.INT);
        this.value = value;
    }

    @Override
    public String toString() {
        return "IntegerLiteralNode{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }
}
