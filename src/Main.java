import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader(new File("src/parser/testsANDscripts/test2.txt"));
        Scanner scanner = new Scanner(fr);
        parser parser = new parser(scanner);
        parser.parse();
    }
}
