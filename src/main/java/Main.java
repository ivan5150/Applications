
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {

    public static final String KEYWORD_FOR_EXIT = "--stop";
    public static final String FILE_NAME = "output.txt";
    public static final String CHARSET_NAME = "UTF-8";
    public static final boolean APPEND_TO_END_FILE = true;

    public static void main(String[] args) {
        System.out.println("App was started. Enter '" + KEYWORD_FOR_EXIT + "' to exit.");
        System.out.println("Enter new line...");
        try (Scanner sc = new Scanner(System.in);) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (isStopTheApp(line)) {
                    break;
                }
                try {
                    writeNewLineToFile(line);
                } catch (FileNotFoundException e) {
                    System.out.println("File was not found " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Access file error " + e.getMessage());
                }
                System.out.println("Enter new line...");
            }
        }
        System.out.println("App was stopped");
    }

    private static void writeNewLineToFile(String line) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME, APPEND_TO_END_FILE);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, CHARSET_NAME);
             PrintWriter writer = new PrintWriter(outputStreamWriter);) {
            writer.println(line);
        }
    }

    private static boolean isStopTheApp(String line) {
        return line.equals(KEYWORD_FOR_EXIT);
    }
}