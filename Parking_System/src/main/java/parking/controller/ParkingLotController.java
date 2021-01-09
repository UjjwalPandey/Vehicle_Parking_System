package parking.controller;

import parking.pojo.Slot;
import parking.pojo.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ParkingLotController {
    private final PriorityQueue<Integer> availableSlotsMinHeap = new PriorityQueue<>();
    private final HashMap<Integer, ArrayList<String>> age_Mapped_RegistrationIdList = new HashMap<>();
    private final HashMap<String, Slot> registrationId_Mapped_Slots = new HashMap<>();
    private Slot[] slots;

    /**
     * Initializes Parking Lot.
     * MinHeaps are prepared to fetch nearest available slot in O(1) time.
     *
     * @param N - Number of slots within the Parking Lot.
     */
    void initialize(int N){
        slots = new Slot[N+1];
        for(int i=1; i<=N; i++){
            availableSlotsMinHeap.add(i);
        }
    }

    /**
     * Looks for if Parking Lot is initialized/prepared and the slots are available.
     *
     * @return String: Empty if slots are available. Else returns the Error message.
     */
    private String checkSlotAvailabilityUtility(){
        String response = "";
        if(availableSlotsMinHeap.size() == 0){
            if(registrationId_Mapped_Slots.size() == 0){
                response = "Error: Please prepare the Parking Lot before working on slots";
            }else{
                response = "Error: Parking Full!";
            }
        }
        return  response;
    }

    /**
     * Module handling ticket/slot allocation.
     * Steps:
     *      Check for slot availability -> Look for nearest available slot -> Create vehicle and slot object ->
     *      Then Update: Slots array, age-registrationIdList Map, registrationId-Slot Map
     *
     * @param vehicleRegistrationId - Registration ID of the vehicle for which ticket is alloted
     * @param driverAge - Age of driver
     * @return  Success/Failure message of ticket allocation.
     */
    private String allotTicket(String vehicleRegistrationId, int driverAge) {
        String availabilityCheck = checkSlotAvailabilityUtility();
        if (!availabilityCheck.equals("")) return availabilityCheck;

        if (slots[availableSlotsMinHeap.peek()] != null) {
            return "Error: Slot number \"" + availableSlotsMinHeap.peek() + "\" already occupied!";
        }
        if (registrationId_Mapped_Slots.containsKey(vehicleRegistrationId)) {
            return "Error: Car with vehicle registration number \"" + vehicleRegistrationId + "\" already present";
        }

        int nearestSlotNumber = availableSlotsMinHeap.poll();
        Vehicle vehicleObject = new Vehicle(vehicleRegistrationId, driverAge);
        Slot slotToAllot = new Slot(nearestSlotNumber, vehicleObject);
        slots[nearestSlotNumber] = slotToAllot;
        ArrayList<String> vehicleRegistrationIdListForAge = age_Mapped_RegistrationIdList.getOrDefault(driverAge, new ArrayList<>());
        vehicleRegistrationIdListForAge.add(vehicleRegistrationId);
        age_Mapped_RegistrationIdList.put(driverAge, vehicleRegistrationIdListForAge);

        registrationId_Mapped_Slots.put(vehicleRegistrationId, slotToAllot);
        return "Car with vehicle registration number \"" + vehicleRegistrationId + "\" has been parked at slot number " + nearestSlotNumber;
    }

    /**
     * Module handling the vehicle check-out process.
     * Steps:
     *      Check if Slot not empty -> Fetch slot object from slots array -> Update: Slots array, age-registrationIdList Map, registrationId-Slot Map ->
     *      Add the slotNumber back in minHeap.
     * @param slotNumber - slot to be emptied
     * @return Success/Error message for checkout process
     */
    private String checkOut(int slotNumber) {
        if(slots[slotNumber] == null){
            return "Error: Slot number "+slotNumber+" already empty!";
        }
        Slot checkOutSlot = slots[slotNumber];
        Vehicle checkOutVehicle = checkOutSlot.getParkedVehicle();

        ArrayList<String> vehicleRegistrationIdListForAge = age_Mapped_RegistrationIdList.get(checkOutVehicle.getDriverAge());
        vehicleRegistrationIdListForAge.remove(checkOutVehicle.getRegistrationId());
        age_Mapped_RegistrationIdList.put(checkOutVehicle.getDriverAge(), vehicleRegistrationIdListForAge);

        registrationId_Mapped_Slots.remove(checkOutVehicle.getRegistrationId());

        slots[slotNumber] = null;
        availableSlotsMinHeap.add(slotNumber);
        return "Slot number " + slotNumber + " vacated, the car with vehicle registration number \"" + checkOutVehicle.getRegistrationId() +
                "\" left the space, the driver of the car was of age " + checkOutVehicle.getDriverAge();
    }

    /**
     * Master controller function: Renders command and calls appropriate functions.
     *
     * @param inputCommand - Command phrase.
     * @return Response Output.
     */
    public String processInput(String inputCommand){
        String[] command = inputCommand.split(" ");
        StringBuilder response = new StringBuilder();
        if(command.length == 0) return response.toString();
        switch (command[0]) {
            case "Create_parking_lot" -> {
                try {
                    int N = Integer.parseInt(command[1]);
                    initialize(N);
                    response.append("Created parking of ").append(N).append(" slots");
                }catch (Exception NumberFormatException){
                    return "Error: NumberFormatException. Please correct the number of slots";
                }
            }
            case "Park" -> {
                try {
                    String vehicleRegistrationId = command[1];
                    int driverAge = Integer.parseInt(command[3]);
                    response.append(allotTicket(vehicleRegistrationId, driverAge));
                }catch (Exception NumberFormatException){
                    response.append("Error: NumberFormatException. Please correct the driver's age.");
                }
            }
            case "Leave" -> {
                try {
                    int slotNumber = Integer.parseInt(command[1]);
                    response.append(checkOut(slotNumber));
                }catch (Exception NumberFormatException){
                    return "Error: NumberFormatException. Please correct the slot number";
                }
            }
            // SEARCH Commands ahead
            case "Slot_numbers_for_driver_of_age" -> {
                try {
                    int queryDriverAge = Integer.parseInt(command[1]);
                    if (!age_Mapped_RegistrationIdList.containsKey(queryDriverAge)) {
                        response.append("No driver with age \"").append(queryDriverAge).append("\" not present");
                        break;
                    }
                    ArrayList<String> vehicleIDForAge = age_Mapped_RegistrationIdList.get(queryDriverAge);
                    for (String vehicleId : vehicleIDForAge) {
                        response.append(registrationId_Mapped_Slots.get(vehicleId).getId()).append(",");
                    }
                    if (response.charAt(response.length() - 1) == ',') {
                        response.deleteCharAt(response.length() - 1);
                    }
                }catch (Exception NumberFormatException){
                    return "Error: NumberFormatException. Please correct the driver's age.";
                }
            }
            case "Slot_number_for_car_with_number" -> {
                String queryRegistrationId = command[1];
                if (!registrationId_Mapped_Slots.containsKey(queryRegistrationId)) {
                    response.append("Car with vehicle registration number \"").append(queryRegistrationId).append("\" not present");
                    break;
                }
                response.append(registrationId_Mapped_Slots.get(queryRegistrationId).getId());
            }
            case "Vehicle_registration_number_for_driver_of_age" -> {
                try {
                    int driverAge = Integer.parseInt(command[1]);
                    if (!age_Mapped_RegistrationIdList.containsKey(driverAge)) {
                        break;
                    }
                    ArrayList<String> vehicleIDList = age_Mapped_RegistrationIdList.get(driverAge);
                    for (String vehicleId : vehicleIDList) {
                        response.append(vehicleId).append(",");
                    }
                    if (response.charAt(response.length() - 1) == ',') {
                        response.deleteCharAt(response.length() - 1);
                    }
                }catch (Exception NumberFormatException){
                    return "Error: NumberFormatException. Please correct the driver's age.";
                }
            }
            default -> response.append("Invalid command");
        }
        return response.toString();
    }
}
