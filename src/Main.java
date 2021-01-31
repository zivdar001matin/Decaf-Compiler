import java.io.*;

/**
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            String inputFile = null;
            String outputFile = null;
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-i")) {
                        inputFile = args[i + 1];
                    }
                    if (args[i].equals("-o")) {
                        outputFile = args[i + 1];
                    }
                }
            }
            File read = null;
            if (inputFile != null) {
                read = new File(inputFile);
            }
            Writer writer = null;
            if (outputFile != null) {
                writer = new FileWriter(outputFile);
            }


            Compiler.compile(read, writer);


            assert writer != null;
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            Writer writer;
            String outputFile = null;
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-o")) {
                        outputFile = args[i + 1];
                    }
                }
            }
            if (outputFile != null) {
                writer = new FileWriter("out/" + outputFile);
            } else {
                writer = new OutputStreamWriter(System.out);
            }

            writer.write("NO");
            writer.flush();
            writer.close();
        }
    }

}