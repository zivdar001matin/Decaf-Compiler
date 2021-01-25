package ast;

/**
 * An enum that shows type of a var
 */
public enum PrimitiveType implements Type {

    BOOL(".word", 1),
    CHAR(".byte", 1),
    INT(".word", 4),
    DOUBLE(".double", 8),
    FLOAT(".float", 4),
    //todo
    VOID("void", 0),
    STRING(".ascii", 0);

    private final String signature;
    private final int align;

    PrimitiveType(String signature, int align) {
        this.signature = signature;
        this.align = align;
    }

    public String getSignature() {
        return signature;
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
