package codegen.ast;

/**
 * An enum that shows type of a var
 */
public enum PrimitiveType implements Type {

    BOOL("0", ".word", 1),
    CHAR("", ".byte", 1),
    INT("0", ".word", 4),
    DOUBLE("0.0", ".double", 8),
    FLOAT("", ".float", 4),
    //todo
    VOID("", "void", 0),
    STRING("\"\"", ".ascii", 0);

    private final String initialValue;
    private final String signature;
    private final int align;

    PrimitiveType(String initialValue, String signature, int align) {
        this.initialValue = initialValue;
        this.signature = signature;
        this.align = align;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public String getInitialValue() {
        return this.initialValue;
    }


    @Override
    public String toString() {
        return signature;
    }

    public int getAlign() {
        return align;
    }

    @Override
    public PrimitiveType getPrimitive() {
        return this;
    }
}
