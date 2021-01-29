package codegen.symboltable;

/**
 * An enum to declare type of Block Scope.
 * used to check that "continue", "break" are legal or not
 */
public enum BlockType {
    ROOT,
    CLASS,
    METHOD,
    CONDITION,
    LOOP,
}
