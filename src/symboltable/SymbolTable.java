package symboltable;

import java.util.ArrayList;

public class SymbolTable {

    public SymbolTable() {
        currentScope = root;
    }

    //    private static final ArrayList<Scope> scopes = new ArrayList<>();
    private static final Scope root = new Scope("Root Scope", null);
    private static Scope currentScope;

    /**
     * scopeCrawler crawls parent untill reach the *Root Scope*.
     *
     * @param entry entry for hashTable.
     * @return DCSP for given entry.
     * @throws Exception when given entry wasn't in any scope of parents.
     */
    public static DSCP getDSCP(String entry) throws Exception {
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
     */
    public void enterScope(String name) {
        Scope newScope = new Scope(name, currentScope);
        currentScope.addChild(newScope);
        currentScope = newScope;
    }

    /**
     * Remember to use when you are going to leave your scope.
     */
    public void leaveScope() {
        currentScope = currentScope.getParent();
    }
}
