import java.io.FileNotFoundException;
import java.io.PrintStream;

public class PrintClass
{
    static private PrintStream logFile;
    static{
        try {
            logFile = new PrintStream("battleLog.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void print(String string){
        System.out.println(string);
        logFile.println(string);
        logFile.flush();
    }
}
