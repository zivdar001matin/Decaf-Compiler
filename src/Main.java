import ast.RootNode;

import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader(new File("src/parser/testsANDscripts/test3.txt"));
        Scanner scanner = new Scanner(fr);
        parser parser = new parser(scanner);
//        System.err.close();
//        try {
            parser.parse();
            RootNode root = parser.root;
            System.out.println("OK");
//        }catch (Exception e){
//            System.out.println("Syntax Error");
//        }
    }
}
