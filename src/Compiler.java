import codegen.CodeGen;
import codegen.SemanticError;
import parser.SyntaxError;
import parser.parser;
import scanner.Scanner;

import java.io.File;
import java.io.FileReader;
import java.io.Writer;

public class Compiler {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("tests/manual/test.d");
        Scanner scanner = new Scanner(fr);
        parser parser = new parser(scanner);

        try {
            parser.parse();
        } catch (Exception e) {
            throw new SyntaxError(e.getMessage());
        }

        CodeGen.compile(parser.getRoot());
    }

    public static void compile(File read, Writer writer) throws Exception { // for auto testing
        FileReader fr = new FileReader(read);
        Scanner scanner = new Scanner(fr);
        parser parser = new parser(scanner);

        String errorCode = null;
        boolean isError = false;

        try {
            parser.parse();
        } catch (Exception e) {
            // ignore
            errorCode = SyntaxError.writeError();
            isError = true;
        }

        try {
            CodeGen.compile(parser.getRoot(), writer);
        } catch (Exception e) {
            // ignore
            errorCode = SemanticError.writeError();
            isError = true;
        }

        if (isError) {
            writer.write(errorCode);
        }
    }
}
