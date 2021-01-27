package codegen.vtable;

import java.util.HashMap;

public class VTable {
    private final HashMap<String, CodeForFunct> table;

    public VTable() {
        this.table = new HashMap<>();
    }

    public void addFunction(String name, CodeForFunct codeForFunct) {
        table.put(name, codeForFunct);
    }

    public CodeForFunct getFunction(String name) throws Exception {
        CodeForFunct code = table.get(name);
        if (code == null) {
            throw new Exception("Function " + name + " not defined!");
        }
        return code;
    }
}
