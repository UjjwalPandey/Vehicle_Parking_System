package parking.controller;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotControllerTest {
    ParkingLotController parkingLotController = new ParkingLotController();

    @Test
    public void testInitialize(){
        Assert.assertEquals("Error: Please prepare the Parking Lot before working on slots", parkingLotController.processInput("Park KA-01-HH-1234 driver_age 21"));
        Assert.assertEquals("Parking Lot must have at least 1 slot.", parkingLotController.processInput("Create_parking_lot 0"));
        Assert.assertEquals( "Created parking of 1 slots", parkingLotController.processInput("Create_parking_lot 1"));
        Assert.assertEquals( "Car with vehicle registration number \"UP-01-TG-5341\" has been parked at slot number 1", parkingLotController.processInput("Park UP-01-TG-5341 driver_age 28"));
        Assert.assertEquals( "Error: Parking Full!", parkingLotController.processInput("Park UP-01-TG-5341 driver_age 28"));
    }

    @Test
    public void testAllotTicket(){
        Assert.assertEquals( "Error: Please prepare the Parking Lot before working on slots", parkingLotController.processInput("Park KA-01-HH-1234 driver_age 21"));
        Assert.assertEquals("Created parking of 2 slots", parkingLotController.processInput("Create_parking_lot 2"));
        Assert.assertEquals("Car with vehicle registration number \"KA-01-HH-1234\" has been parked at slot number 1", parkingLotController.processInput("Park KA-01-HH-1234 driver_age 21"));
        Assert.assertEquals("Error: Car with vehicle registration number \"KA-01-HH-1234\" already present", parkingLotController.processInput("Park KA-01-HH-1234 driver_age 21"));
        Assert.assertEquals("Error: NumberFormatException. Please correct the driver's age.", parkingLotController.processInput("Park MP-01-HH-1234 driver_age 21a"));
    }

    @Test
    public void testCheckOut(){
        Assert.assertEquals("Error: Slot number 5 is out of range!", parkingLotController.processInput("Leave 5"));
        Assert.assertEquals("Created parking of 6 slots", parkingLotController.processInput("Create_parking_lot 6"));
        Assert.assertEquals("Car with vehicle registration number \"KA-01-HH-1234\" has been parked at slot number 1", parkingLotController.processInput("Park KA-01-HH-1234 driver_age 21"));
        Assert.assertEquals("Error: NumberFormatException. Please correct the slot number", parkingLotController.processInput("Leave 1i"));
        Assert.assertEquals("Slot number 1 vacated, the car with vehicle registration number \"KA-01-HH-1234\" left the space, the driver of the car was of age 21", parkingLotController.processInput("Leave 1"));
        Assert.assertEquals("Error: Slot number 1 already empty!", parkingLotController.processInput("Leave 1"));
    }

    @Test
    public void testSlotNumbersForDriverOfAge(){
        parkingLotController.processInput("Create_parking_lot 6");
        parkingLotController.processInput("Park KA-01-HH-1234 driver_age 21");
        parkingLotController.processInput("Park MP-01-HH-1234 driver_age 21");
        Assert.assertEquals("Error: NumberFormatException. Please correct the driver's age.", parkingLotController.processInput("Slot_numbers_for_driver_of_age 21s"));
        Assert.assertEquals("1,2", parkingLotController.processInput("Slot_numbers_for_driver_of_age 21"));
        Assert.assertEquals("No driver with age \"22\" not present", parkingLotController.processInput("Slot_numbers_for_driver_of_age 22"));
    }

    @Test
    public void testVehicleRegistrationNumberForDriverOfAge(){
        parkingLotController.processInput("Create_parking_lot 6");
        parkingLotController.processInput("Park KA-01-HH-1234 driver_age 21");
        parkingLotController.processInput("Park MP-01-HH-1234 driver_age 21");
        Assert.assertEquals("Error: NumberFormatException. Please correct the driver's age.", parkingLotController.processInput("Vehicle_registration_number_for_driver_of_age 21s"));
        Assert.assertEquals("KA-01-HH-1234,MP-01-HH-1234", parkingLotController.processInput("Vehicle_registration_number_for_driver_of_age 21"));
        Assert.assertEquals("Warning: No vehicle parked by Driver of age 22", parkingLotController.processInput("Vehicle_registration_number_for_driver_of_age 22"));
    }

    @Test
    public void testSlotNumberForCarWithNumber(){
        parkingLotController.processInput("Create_parking_lot 6");
        parkingLotController.processInput("Park KA-01-HH-1234 driver_age 21");
        parkingLotController.processInput("Park PB-01-HH-1234 driver_age 21");
        Assert.assertEquals("Warning: Car with vehicle registration number \"UP-01-HH-1234\" not present", parkingLotController.processInput("Slot_number_for_car_with_number UP-01-HH-1234"));
        Assert.assertEquals("2", parkingLotController.processInput("Slot_number_for_car_with_number PB-01-HH-1234"));
    }

    @Test
    public void testInvalidCommand(){
        Assert.assertEquals("Invalid command", parkingLotController.processInput("Test Random Command"));
    }
}
