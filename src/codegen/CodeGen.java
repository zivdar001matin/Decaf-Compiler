package codegen;

import ast.Node;
import symboltable.SymbolTable;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGen {

    private static final SymbolTable  spaghettiStack = new SymbolTable();

    public static String dataSeg = ".data\n" ;
    public static String textSeg = ".text\n";

    public static void cgen(Node node){
        switch (node.getNodeType()){
            case BLOCK:
                cgenBlock(node);
                break;
            case ADDITION:
                cgenAdditon(node);
                break;
            default:
                cgenAllChildren(node);
                break;
        }
    }

    private static void cgenBlock(Node node) {
        spaghettiStack.enterScope(String.valueOf(node.getParent().getChild(1)));
        cgenAllChildren(node);
        spaghettiStack.leaveScope();
    }

    private static void cgenAllChildren(Node node) {
        for (Node child : node.getChildren()) {
            cgen(child);
        }
    }

    private static void cgenAdditon(Node node) {
        System.out.println("####saw addition " + node);
    }

    public static void compile() throws IOException {
        FileWriter out = new FileWriter("out.asm");
        out.write(dataSeg + textSeg);
        out.close();
    }

}
