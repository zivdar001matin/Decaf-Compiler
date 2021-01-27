import codegen.CodeGen;
import codegen.ast.Node;
import codegen.ast.RootNode;
import parser.parser;
import scanner.Scanner;

import java.io.File;
import java.io.FileReader;

import static codegen.CodeGen.cgen;

public class Compiler {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader(new File("tests/test.d"));
        Scanner scanner = new Scanner(fr);
        parser parser = new parser(scanner);
//        System.err.close();
//        try {
        parser.parse();
        RootNode root = parser.getRoot();
//        System.out.println("OK");
//        printTree(parser.getRoot());
        cgen(parser.getRoot());
        CodeGen.compile();
        System.out.println("cgen Completed!");
        System.out.println("####################### asm code ######################");
        System.out.print(CodeGen.getDataSeg());
        System.out.print(CodeGen.getTextSeg());
        System.out.println("#######################################################");
//        }catch (Exception e){
//            System.out.println("Syntax Error");
//        }
    }

    private static void printTree(Node node) {
        for (Node child : node.getChildren()) {
            System.out.println(child + " ---------> " + node);
        }
        for (Node child : node.getChildren()) {
            printTree(child);
        }
    }
}
