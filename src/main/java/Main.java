import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        System.out.println("Hello, and welcome to my note taking Application.");
        String option = Inputs.mainLoop();

        if (option.equalsIgnoreCase("new")) {
            Inputs.newNoteLoop();
            Inputs.mainLoop();
        }



    }
}