import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUpdater {

    // Path to data file
    protected static Path p = Paths.get("src", "data.txt");

    // Scanner input
    protected static Scanner input = new Scanner(System.in);


    // Read datafile return List
    protected static List<String> readData() {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    protected static void addNewContact(){
        List<String> lines = readData();

        System.out.println("Add contacts first and last name.");
        String newName = input.nextLine();

        System.out.format("What is %s's phone number?\n", newName);
        String userInput = input.nextLine();

        String formattedContact = "";
        String phoneNumber = "";

        if((userInput.matches(".*\\d.*")) && (userInput.length() == 7 || userInput.length() == 10)){
            phoneNumber = formatNumber(userInput);
        } else {
            System.out.println("That isn't a phone number");
            UserInterface.menu();
        }

        formattedContact += newName;
        for(int i = 0; i <= (20 - newName.length()); i++){
            formattedContact += " ";
        }

        formattedContact += "| " + phoneNumber;

        lines.add(formattedContact);

        try{
            Files.write(p, lines);
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    // Deletes contact
    protected static void deleteContact() {
        System.out.println("Enter contact's name to be deleted");
        String fullName = input.nextLine();

        List<String> lines = FileUpdater.readData();
        List<String> newList = new ArrayList<String>();

        System.out.println("\nName                 | Phone Number ");
        System.out.println("-----------------------------------");

        for(String l : lines){
            if(l.toLowerCase().contains(fullName.toLowerCase())){
                System.out.println(l);
            }
        }

        System.out.println("\nAre you sure you want to delete this contact(s)?");
        String confirm = input.nextLine();

        if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
            for(String l : lines){
                if(l.toLowerCase().contains(fullName.toLowerCase())){
                    continue;
                }
                newList.add(l);
            }

            try{
                Files.write(p, newList);
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                UserInterface.viewContacts();
            }
        } else {
            System.out.println("Do you want to search for another user to delete? ");

            confirm = input.nextLine();

            if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
                deleteContact();
            }
        }

    }

    private static String formatNumber(String rawNumber){
        if(rawNumber.length() == 7){
            return rawNumber.substring(0, 3) + "-" + rawNumber.substring(3);
        }else {
            return rawNumber.substring(0, 3) + "-" + rawNumber.substring(3, 6) + "-" + rawNumber.substring(6);
        }
    }

}
