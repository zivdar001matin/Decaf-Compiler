package codegen.symboltable;

import java.util.ArrayList;
import java.util.HashMap;

public class Scope {

    private final HashMap<String, DSCP> scope;
    private final Scope parent;
    private final String name;
    private final BlockType blockType;
    private final ArrayList<Scope> children;
    private int argumentCounter;
    private int conditionStmtCounter;
    private int loopStmtCounter;
    private int stringLiteralCounter;

    public Scope(String name, Scope parent, BlockType blockType) {
        this.scope = new HashMap<>();
        this.name = name;
        this.parent = parent;
        children = new ArrayList<>();
        this.argumentCounter = 0;
        this.conditionStmtCounter = 0;
        this.blockType = blockType;
    }

    public HashMap<String, DSCP> getScope() {
        return this.scope;
    }

    public BlockType getBlockType() {
        return blockType;
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

    public int getConditionStmtCounter() {
        return conditionStmtCounter;
    }

    public void addConditionStmtCounter() {
        this.conditionStmtCounter++;
    }

    public int getLoopStmtCounter() {
        return loopStmtCounter;
    }

    public void addLoopStmtCounter() {
        this.loopStmtCounter++;
    }

    public int getStringLiteralCounter() {
        return stringLiteralCounter;
    }

    public void addStringLiteralCounter() {
        this.stringLiteralCounter++;
    }

    ArrayList<Scope> getChildren() {
        return this.children;
    }

    @Override
    public String toString() {
        return name;
    }
}
