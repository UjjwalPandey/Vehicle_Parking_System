# Vehicle Parking System -



### System Requirements and Project Setup:
 - Based on your OS, download & install the *Intellij* from [here](https://www.jetbrains.com/idea/download/#section=windows)
 - Clone the project from *GitHub*. Follow [these](https://www.jetbrains.com/help/idea/manage-projects-hosted-on-github.html#share-on-GitHub) steps.
 - Try running the [main](https://github.com/UjjwalPandey/Vehicle_Parking_System/blob/master/Parking_System/src/main/java/parking/Main.java) function. If any Maven related issue pops up at your system then please [refer here](https://www.jetbrains.com/help/idea/convert-a-regular-project-into-a-maven-project.html#develop_with_maven) 
 - You can edit the [input file](https://github.com/UjjwalPandey/Vehicle_Parking_System/blob/master/Parking_System/src/main/resources/input.txt) at `src/main/resources/input.txt`
 - Also, Test cases covering corner cases can be [seen here](https://github.com/UjjwalPandey/Vehicle_Parking_System/blob/master/Parking_System/src/test/java/parking/controller/ParkingLotControllerTest.java). 

### Classes & Objects
 - `Vehicle:` Registration ID & Driver's age.
 - `Slot:` Slot Number & Parked vehicle object.


### Data Structures Used:
Here as there is no requirement of storing history, thus I opted to use Data Structures instead of Database. 
Also, as the input format is such that there is almost <ins>_no such direct use of TICKET_</ins> thus instead of making a sepearate Class, I have used a **_MAP which links Vehicle with the Slot._**

 - `MIN HEAP for storing the slot number:` Helps in finding the nearest available slot from the entry gate.
 - `HASHMAP to map (age, List [Vehicle Registration ID]):` For an easy lookup of Registration ID linked with the driver of particular age. 
 - `HASHMAP to map (Vehicle Registration ID, Slot object):` For an easy lookup of the Slot for given Registration ID. 
 - `Slots Array:` To check if the slot is occupied or vacant in O(1) time. Also, helps in fetching the `nth` slot. 

### Controller File:
This file has mainly 5 functions:
1. `initialize:` Initializes Parking Lot.
2. `allotTicket:` Module handling ticket/slot allocation.
3. `checkOut:` Module handling the vehicle check-out process.
4. `processInput:` This function takes the command and  calls appropriate functions.
5. `checkSlotAvailabilityUtility:` Looks for if Parking Lot is initialized/prepared, and the slots are available.
