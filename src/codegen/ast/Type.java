package codegen.ast;

public interface Type {

    String getSignature();

    String getInitialValue();

    int getAlign();

    PrimitiveType getPrimitive();

}
