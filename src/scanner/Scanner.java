package scanner;

import java.io.FileReader;
import java.io.IOException;

public class Scanner {
    public static void main(String[] args) throws IOException {
        Lexer scanner = new Lexer(new FileReader("src/scanner/testsANDscripts/test2.txt"));
        while (true){
            int a = scanner.yylex();
            if (a == Lexer.YYEOF)
                break;
        }
    }
}
