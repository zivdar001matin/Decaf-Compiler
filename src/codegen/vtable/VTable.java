package codegen.vtable;

import java.util.HashMap;

public class VTable {
    private final HashMap<String, CodeForFunct> scope;

    public VTable() {
        this.scope = new HashMap<>();
    }
}
