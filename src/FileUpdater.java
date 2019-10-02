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
        Scanner input = new Scanner(System.in);
        System.out.println("Add contacts first and last name.");
        String newName = input.nextLine();
        System.out.format("What is %s's phone number?", newName);
        String userInput = input.nextLine();
        String formattedContact = "";
        int phoneNumber = 0;
        if(userInput.matches(".*\\d.*")){
            phoneNumber = Integer.parseInt(userInput);
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

}
