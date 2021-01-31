import codegen.CodeGen;
import codegen.SemanticError;
import parser.*;
import scanner.*;

import java.io.*;

public class Compiler {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("tests/test.d");
        Scanner scanner = new Scanner(fr);
        parser parser = new parser(scanner);

        try {
            parser.parse();
        } catch (Exception e){
            throw new SyntaxError(e.getMessage());
        }

        CodeGen.compile(parser.getRoot());
    }

    public static void compile(File read, Writer writer) throws Exception { // for auto testing
        FileReader fr = new FileReader(read);
        Scanner scanner = new Scanner(fr);
        parser parser = new parser(scanner);

        try {
            parser.parse();
        } catch (Exception e){
            // ignore
            SyntaxError.writeError(writer);
        }

        try {
            CodeGen.compile(parser.getRoot(), writer);
        } catch (Exception e){
            // ignore
            SemanticError.writeError(writer);
        }
    }
}
