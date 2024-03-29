import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private static Scanner sc = new Scanner(System.in);

    private static boolean menuContinue = true;

    // Main menu, entry point of application
    public static void menu() {

        do {
            System.out.println("\nMain Menu\n" +
                    "\n1. View Contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n");
            System.out.println("Enter an option (1, 2, 3, 4, or 5):");

            // Take in user choice.
            int choice = 0;

            String userInput = sc.nextLine();

            if(userInput.matches(".*\\d.*")){
                choice = Integer.parseInt(userInput);
            } else {
                System.out.println("That isn't a valid choice.");
                menu();
                return;
            }

            // Perform action based on choice
            switch (choice) {
                case 1:
                    viewContacts();
                    break;
                case 2:
                    FileUpdater.addNewContact();
                    break;
                case 3:
                    searchContacts();
                    break;
                case 4:
                    FileUpdater.deleteContact();
                    break;
                case 5:
                    System.out.println("Goodbye !");
                    menuContinue = false;
                    break;
                default:
                    System.out.println("Invalid menu choice: " + choice + " Please try again\n");
                    break;
            }


            // Prompt if you want to continue using the menu
            if (menuContinue) {
                System.out.println("Would you like to continue?");
                String menuContinueResponse = sc.nextLine();

                if (menuContinueResponse.equalsIgnoreCase("no") || menuContinueResponse.equalsIgnoreCase("n")) {
                    System.out.println("Goodbye !");
                    menuContinue = false;
                }
            }

        } while (menuContinue);

    }

    // View contacts in file.
    protected static void viewContacts() {
        List<String> list = FileUpdater.readData();

        System.out.println("\nName                 | Phone Number ");
        System.out.println("-----------------------------------");

        for(String l : list) {
            System.out.println(l);
        }

        System.out.println("");
    }

    protected static void searchContacts(){
        System.out.println("Enter contact's name");
        String fullName = sc.nextLine();
        List<String> lines = FileUpdater.readData();
        System.out.println("\nName                 | Phone Number ");
        System.out.println("-----------------------------------");

        for(String l : lines){
            if(l.toLowerCase().contains(fullName.toLowerCase())){
                System.out.println(l);
            }
        }

    }

}
