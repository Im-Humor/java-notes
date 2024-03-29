import java.util.Scanner;

public class Inputs {

    // helper functions for finding in array and
    // prompting user for correct string

    public static boolean SearchArray(String string, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    public static String checkString(String [] optionList)  {
        Scanner scnr = new Scanner(System.in);
        String option = scnr.nextLine();
        if (option.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
        while(!SearchArray(option, optionList)) {
            System.out.println("Please try again");
            option = scnr.nextLine();
        }
        return option;
    }

    // initial function for user action

    public static void firstOption() {
        System.out.println("Would you like to create 'New', 'Select', or 'Delete'?");

        String option = checkString(new String[]{"new", "select", "delete"});

        if (option.equalsIgnoreCase("new")) {
            newNote();
        }
        if (option.equalsIgnoreCase("select")) {
            selectNote();
        }
        if (option.equalsIgnoreCase("delete")) {
            deleteNote();
        }
    }

    // prompts user for note document name

    public static void newNote() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("What would you like the new note document to be named?");

        String newNoteName = scnr.nextLine();
        while (newNoteName.isEmpty()) {
            System.out.println("Note document name cannot be empty.");
            newNoteName = scnr.nextLine();
        }

        SQLite.createNote("notes.db", newNoteName);
        SQLite.listNoteNames("notes.db");
    }

    // prompts user for note selection by ID

    public static void selectNote() {
        Scanner scnr = new Scanner(System.in);
        SQLite.listNoteNames("notes.db");

        System.out.println("Select note ID or 'return' to previous menu.");
        String selectId = scnr.nextLine();
        if (selectId.equalsIgnoreCase("return")) {
            firstOption();
            return;
        }
        SQLite.viewNoteContents("notes.db", selectId);
        editNote(selectId);
    }

    // once note is selected, prompt user for document action

    public static void editNote(String selectId) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Would you like to 'delete', create 'new', or 'return'?");

        String option = checkString(new String[]{"new", "delete", "return"});

        if (option.equalsIgnoreCase("new")) {
            System.out.println("Enter new note:");
            String noteText = scnr.nextLine();
            SQLite.createSubnote("notes.db", selectId, noteText);
        }

        if (option.equalsIgnoreCase("delete")) {
            System.out.println("Which note ID would you like to delete?");
            String deleteId = scnr.nextLine();
            SQLite.deleteSubnote("notes.db", deleteId);
        }
        if (option.equalsIgnoreCase("return")) {
            selectNote();
            return;
        }
    }

    public static void deleteNote() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Which note ID would you like to delete?");
        SQLite.listNoteNames("notes.db");
        String deleteId = scnr.nextLine();
        SQLite.deleteNote("notes.db", deleteId);
    }

}
