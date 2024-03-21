import java.util.Scanner;
import java.util.List;

public class Main {

    public static boolean SearchArray(String string, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        System.out.println("Hello, and welcome to my note taking Application.");
        System.out.println("Would you like to create 'New', 'Select', or 'Delete'?");

        String[] optionList = {"new", "select", "delete"};

        String option = scnr.nextLine();
        while (!SearchArray(option, optionList) ) {
            System.out.println("Please try again");
            option = scnr.nextLine();
        }
        System.out.println("That's a valid option!");



    }
}