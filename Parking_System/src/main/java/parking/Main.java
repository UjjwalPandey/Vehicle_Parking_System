package parking;

import parking.controller.ParkingLotController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLotController parkingLotController = new ParkingLotController();
        try {
            File myObj = new File("Parking_System/src/main/resources/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String inputCommand = myReader.nextLine().trim();
//                System.out.println("Input: "+inputCommand);
                String response = parkingLotController.processInput(inputCommand);
                System.out.println(response);
//                System.out.println("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
