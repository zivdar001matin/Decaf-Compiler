package ast.literal;

import ast.PrimitiveType;

public class DoubleLiteralNode extends Literal {
    private final Double value;

    public DoubleLiteralNode(String value) {
        super(PrimitiveType.DOUBLE);
        this.value = Double.valueOf(value);
    }

    @Override
    public String toString() {
        return "DoubleLiteralNode{" +
                "value=" + value +
                '}';
    }

    public Double getValue() {
        return value;
    }
}
