package codegen.vtable;

public class CodeForFunct {
    private final String functName;
    private final String code;

    public String getCode() {
        return code;
    }

    public String getFunctName() {
        return functName;
    }

    public CodeForFunct(String functName, String code) {
        this.functName = functName;
        this.code = code;
    }
}
