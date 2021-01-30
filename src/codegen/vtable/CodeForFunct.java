package codegen.vtable;

import codegen.ast.PrimitiveType;
import codegen.ast.Type;

import java.util.ArrayList;

public class CodeForFunct {
    private final String functName; // todo check is necessary or not
    private final String code;      // todo check is necessary or not
    private final Type returnType;
    private final ArrayList<Argument> arguments;
    private int argumentCounter = 0;

    public CodeForFunct(String functName, String code, Type returnType) {
        this.functName = functName;
        this.code = code;
        this.returnType = returnType;
        this.arguments = new ArrayList<>();
    }

    public Type getReturnType() {
        return returnType;
    }

    public String getCode() {
        return code;
    }

    public String getFunctName() {
        return functName;
    }

    public void addArgument(PrimitiveType primitiveType) {
        arguments.add(new Argument(argumentCounter++, primitiveType));
    }

    public PrimitiveType getArgument(int placeInArguments) {
        return arguments.get(placeInArguments).type;
    }
}

class Argument {

    int place;
    PrimitiveType type;

    public Argument(int place, PrimitiveType type) {
        this.place = place;
        this.type = type;
    }
}