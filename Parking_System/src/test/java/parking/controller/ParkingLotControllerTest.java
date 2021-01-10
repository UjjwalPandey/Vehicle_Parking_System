package parking.controller;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotControllerTest {
    ParkingLotController parkingLotController = new ParkingLotController();

    @Test
    public void testMinimumSlots(){
        Assert.assertEquals("Error: Please prepare the Parking Lot before working on slots", parkingLotController.processParking("Park KA-01-HH-1234 driver_age 21"));
        Assert.assertEquals("Parking Lot must have at least 1 slot.", parkingLotController.processParking("Create_parking_lot 0"));
    }

    @Test
    public void testFullParking(){
        Assert.assertEquals( "Created parking of 1 slots", parkingLotController.processParking("Create_parking_lot 1"));
        Assert.assertEquals( "Car with vehicle registration number \"UP-01-TG-5341\" has been parked at slot number 1", parkingLotController.processParking("Park UP-01-TG-5341 driver_age 28"));
        Assert.assertEquals( "Error: Parking Full!", parkingLotController.processParking("Park UP-01-TG-5341 driver_age 28"));
    }

    @Test
    public void testInputErrors(){
        Assert.assertEquals("Error: Slot number 5 is out of range!", parkingLotController.processParking("Leave 5"));
        Assert.assertEquals("Error: NumberFormatException. Please correct the slot number", parkingLotController.processParking("Leave 1i"));
        Assert.assertEquals("Error: NumberFormatException. Please correct the driver's age.", parkingLotController.processParking("Park MP-01-HH-1234 driver_age 21a"));
    }

    @Test
    public void testAllotTicket(){
        Assert.assertEquals( "Error: Please prepare the Parking Lot before working on slots", parkingLotController.allotTicket("KA-01-HH-1234",21));
        Assert.assertEquals("Created parking of 2 slots", parkingLotController.processParking("Create_parking_lot 2"));
        Assert.assertEquals("Car with vehicle registration number \"KA-01-HH-1234\" has been parked at slot number 1", parkingLotController.allotTicket("KA-01-HH-1234", 21));
        Assert.assertEquals("Error: Car with vehicle registration number \"KA-01-HH-1234\" already present", parkingLotController.allotTicket("KA-01-HH-1234",21));
    }

    @Test
    public void testCheckOut(){
        parkingLotController.processParking("Create_parking_lot 6");
        parkingLotController.processParking("Park KA-01-HH-1234 driver_age 21");
        Assert.assertEquals("Slot number 1 vacated, the car with vehicle registration number \"KA-01-HH-1234\" left the space, the driver of the car was of age 21", parkingLotController.checkOut(1));
        Assert.assertEquals("Error: Slot number 1 already empty!", parkingLotController.checkOut(1));
    }

    @Test
    public void testSlotNumbersForDriverOfAge(){
        Assert.assertEquals( "Error: Please prepare the Parking Lot before working on slots", parkingLotController.processParking("Slot_numbers_for_driver_of_age 21"));
        parkingLotController.processParking("Create_parking_lot 6");
        parkingLotController.processParking("Park KA-01-HH-1234 driver_age 21");
        parkingLotController.processParking("Park MP-01-HH-1234 driver_age 21");
        Assert.assertEquals("Error: NumberFormatException. Please correct the driver's age.", parkingLotController.processParking("Slot_numbers_for_driver_of_age 21s"));
        Assert.assertEquals("1,2", parkingLotController.processParking("Slot_numbers_for_driver_of_age 21"));
        Assert.assertEquals("No driver with age \"22\" not present", parkingLotController.processParking("Slot_numbers_for_driver_of_age 22"));
    }

    @Test
    public void testVehicleRegistrationNumberForDriverOfAge(){
        Assert.assertEquals( "Error: Please prepare the Parking Lot before working on slots", parkingLotController.processParking("Vehicle_registration_number_for_driver_of_age 22"));
        parkingLotController.processParking("Create_parking_lot 6");
        parkingLotController.processParking("Park KA-01-HH-1234 driver_age 21");
        parkingLotController.processParking("Park MP-01-HH-1234 driver_age 21");
        Assert.assertEquals("Error: NumberFormatException. Please correct the driver's age.", parkingLotController.processParking("Vehicle_registration_number_for_driver_of_age 21s"));
        Assert.assertEquals("KA-01-HH-1234,MP-01-HH-1234", parkingLotController.processParking("Vehicle_registration_number_for_driver_of_age 21"));
        Assert.assertEquals("Warning: No vehicle parked by Driver of age 22", parkingLotController.processParking("Vehicle_registration_number_for_driver_of_age 22"));
    }

    @Test
    public void testSlotNumberForCarWithNumber(){
        Assert.assertEquals( "Error: Please prepare the Parking Lot before working on slots", parkingLotController.processParking("Slot_number_for_car_with_number UP-01-HH-1234"));
        parkingLotController.processParking("Create_parking_lot 6");
        parkingLotController.processParking("Park KA-01-HH-1234 driver_age 21");
        parkingLotController.processParking("Park PB-01-HH-1234 driver_age 21");
        Assert.assertEquals("Warning: Car with vehicle registration number \"UP-01-HH-1234\" not present", parkingLotController.processParking("Slot_number_for_car_with_number UP-01-HH-1234"));
        Assert.assertEquals("2", parkingLotController.processParking("Slot_number_for_car_with_number PB-01-HH-1234"));
    }

    @Test
    public void testInvalidCommand(){
        Assert.assertEquals("Invalid command", parkingLotController.processParking("Test Random Command"));
    }
}
