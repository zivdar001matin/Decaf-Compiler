package codegen;

import ast.Node;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGen {

    public static String dataSeg = ".data\n" ;
    public static String textSeg = ".text\n";

    public static void cgen(Node node){
        switch (node.getNodeType()){
            case ADDITION:
                cgenAdditon(node);
                break;
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
