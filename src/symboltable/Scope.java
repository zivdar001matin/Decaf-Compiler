package symboltable;

import java.util.ArrayList;
import java.util.HashMap;

public class Scope {

    private final HashMap<String, DSCP> scope;
    private final Scope parent;
    private final String name;
    private ArrayList<Scope> children;

    public Scope(String name, Scope parent) {
        this.scope = new HashMap<>();
        this.name = name;
        this.parent = parent;
        children = new ArrayList<>();
    }

    public HashMap<String, DSCP> getScope() {
        return this.scope;
    }

    public void addChild(Scope child) {
        children.add(child);
    }

    public String getName() {
        return name;
    }

    public Scope getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return name;
    }
}
