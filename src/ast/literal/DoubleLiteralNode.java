package ast.literal;

import ast.PrimitiveType;

public class DoubleLiteralNode extends Literal {
    private final Double value;

    public DoubleLiteralNode(Double value) {
        super(PrimitiveType.DOUBLE);
        this.value = value;
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
