import java.util.Scanner;

public class Inputs {

    public static boolean SearchArray(String string, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    public static String mainLoop() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Would you like to create 'New', 'Select', or 'Delete'?");

        String[] optionList = {"new", "select", "delete"};

        String option = scnr.nextLine();
        while (!SearchArray(option, optionList) ) {
            System.out.println("Please try again");
            option = scnr.nextLine();
        }
        return option;
    }

    public static void newNoteLoop() {
        Scanner scnr = new Scanner(System.in);
        String newNoteName = null;
        System.out.println("What would you like the new note to be named?");

        newNoteName = scnr.nextLine();
        while (newNoteName.isEmpty()) {
            System.out.println("Note name cannot be empty.");
            newNoteName = scnr.nextLine();
        }

        SQLite.createNote("notes.db", newNoteName);
        SQLite.listNoteNames("notes.db");
    }
}
