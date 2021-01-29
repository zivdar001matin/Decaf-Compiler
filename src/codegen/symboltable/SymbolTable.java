package codegen.symboltable;

public class SymbolTable {

    public SymbolTable() {
        currentScope = root;
    }

    //    private static final ArrayList<Scope> scopes = new ArrayList<>();
    private static final Scope root = new Scope("RootScope", null, BlockType.ROOT);
    private static Scope currentScope;

    public static Scope getCurrentScope() {
        return currentScope;
    }

    public Scope getEntryScope(String entry) throws Exception {
        Scope scopeCrawler = currentScope;
        while (true) {
            if (scopeCrawler.getScope().get(entry) != null)
                return scopeCrawler;
            else {
                if (scopeCrawler.getParent() != null)
                    scopeCrawler = scopeCrawler.getParent();
                else break;
            }
        }
        throw new Exception(entry + " not defined!");
    }

    /**
     * scopeCrawler crawls parent untill reach the *Root Scope*.
     *
     * @param entry entry for hashTable.
     * @return DCSP for given entry.
     * @throws Exception when given entry wasn't in any scope of parents.
     */
    public DSCP getDSCP(String entry) throws Exception {
        Scope scopeCrawler = currentScope;
        while (true) {
            if (scopeCrawler.getScope().get(entry) != null)
                return scopeCrawler.getScope().get(entry);
            else {
                if (scopeCrawler.getParent() != null)
                    scopeCrawler = scopeCrawler.getParent();
                else break;
            }
        }
        throw new Exception(entry + " not defined!");
    }

    /**
     * Add entry to the current scope in symbol table.
     *
     * @throws Exception when entry was already used in this scope.
     */
    public void addEntry(String entry, DSCP dscp) throws Exception {
        if (currentScope.getScope().containsKey(entry)) {
            throw new Exception("current scope already contains an entry for " + entry);
        }
        currentScope.getScope().put(entry, dscp);
    }

    /**
     * Remember to use when you are going to enter a scope.
     *
     * @param name name of the current scope. It can be function name, class name, etc.
     * @param blockType use for continue and break statements.
     * @param isFirstPass use for prevent defining a scope twice. use for check function argumentTypes
     */
    public void enterScope(String name, BlockType blockType, boolean isFirstPass) {
        Scope newScope = null;
        if (isFirstPass) {
            newScope = new Scope(name + "Scope", currentScope, blockType);
            currentScope.addChild(newScope);
        } else {
            for (Scope child : currentScope.getChildren()) {
                newScope = child;
                if (child.getName().equals(name + "Scope"))
                    break;
            }
        }
        currentScope = newScope;
    }

    /**
     * Remember to use when you are going to leave your scope.
     */
    public void leaveScope() {
        currentScope = currentScope.getParent();
    }

    @Override
    public String toString() {
        return currentScope.getName();
    }
}
