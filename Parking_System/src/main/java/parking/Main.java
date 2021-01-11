package parking;

import parking.controller.ParkingLotController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLotController parkingLotController = new ParkingLotController();
        try {
            System.out.println("Please specify the file input path: ");
            Scanner inputPath = new Scanner(System.in);
            File myObj = new File(inputPath.nextLine());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String inputCommand = myReader.nextLine().trim();
//                System.out.println("Input: "+inputCommand);
                if(inputCommand.isEmpty()) continue;
                String response = parkingLotController.processParking(inputCommand);
                System.out.println(response);
//                System.out.println("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input File Not Found.");
        }
    }
}
