import codegen.CodeGen;
import codegen.SemanticError;
import codegen.ast.Node;
import parser.*;
import scanner.*;

import java.io.FileReader;

public class Compiler {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("tests/test.d");
        Scanner scanner = new Scanner(fr);
        parser parser = new parser(scanner);
        boolean isSemanticError = false;
        try {
            parser.parse();
            CodeGen.cgen(parser.getRoot());
            CodeGen.compile();
            System.out.println("####################### asm code ######################");
            System.out.print(CodeGen.getDataSeg());
            System.out.print(CodeGen.getTextSeg());
            System.out.println("#######################################################");
        }catch (SemanticError e){
            isSemanticError = true;
            throw e;
        }catch (Exception e){
            if (isSemanticError)
                throw e;
            throw new SyntaxError(e.getMessage());
        }
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
