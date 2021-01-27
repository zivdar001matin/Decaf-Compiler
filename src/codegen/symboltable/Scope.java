package codegen.symboltable;

import java.util.ArrayList;
import java.util.HashMap;

public class Scope {

    private final HashMap<String, DSCP> scope;
    private final Scope parent;
    private final String name;
    private int argumentCounter;
    private ArrayList<Scope> children;

    public Scope(String name, Scope parent) {
        this.scope = new HashMap<>();
        this.name = name;
        this.parent = parent;
        children = new ArrayList<>();
        this.argumentCounter = 0;
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

    public int getArgumentCounter() {
        return argumentCounter;
    }

    public void addArgumentCounter() {
        this.argumentCounter++;
    }

    @Override
    public String toString() {
        return name;
    }
}
