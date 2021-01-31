import codegen.CodeGen;
import parser.*;
import scanner.*;

import java.io.FileReader;

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

        CodeGen.cgen(parser.getRoot());
        CodeGen.compile();
    }
}
