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
        System.out.println("What would you like the new note document to be named?");

        String newNoteName = scnr.nextLine();
        while (newNoteName.isEmpty()) {
            System.out.println("Note document name cannot be empty.");
            newNoteName = scnr.nextLine();
        }

        SQLite.createNote("notes.db", newNoteName);
        SQLite.listNoteNames("notes.db");
    }

    public static void selectNote() {
        Scanner scnr = new Scanner(System.in);
        SQLite.listNoteNames("notes.db");

        System.out.println("Which note document would you like to view?");
        String selectId = scnr.nextLine();
        SQLite.viewNoteContents("notes.db", selectId);
        editNote(selectId);
    }

    public static void editNote(String selectId) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Would you like to 'delete' or create a 'new' note?");

        String[] optionList = {"new", "delete"};

        String option = scnr.nextLine();
        while (!SearchArray(option, optionList) ) {
            System.out.println("Please try again");
            option = scnr.nextLine();
        }

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
    }

    public static void deleteNote() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Which note ID would you like to delete?");
        SQLite.listNoteNames("notes.db");
        String deleteId = scnr.nextLine();
        SQLite.deleteNote("notes.db", deleteId);
    }

}
