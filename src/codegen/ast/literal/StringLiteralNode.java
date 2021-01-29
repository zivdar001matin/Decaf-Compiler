package codegen.ast.literal;

import codegen.ast.PrimitiveType;

public class StringLiteralNode extends Literal {
    private final String value;

    public StringLiteralNode(String value) {
        super(PrimitiveType.STRING);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
