package codegen.vtable;

import codegen.ast.Type;

public class CodeForFunct {
    private final String functName; // todo check is necessary or not
    private final String code;      // todo check is necessary or not
    private final Type returnType;

    public Type getReturnType() {
        return returnType;
    }

    public String getCode() {
        return code;
    }

    public String getFunctName() {
        return functName;
    }

    public CodeForFunct(String functName, String code, Type returnType) {
        this.functName = functName;
        this.code = code;
        this.returnType = returnType;
    }
}
