# Vehicle Parking System -


You can check the detailed expalination video on how to test the project through [this link](https://drive.google.com/file/d/14ASxmwuigE1d9QDap5oQMygvVi3x6baL/view?usp=sharing).

### System Requirements and Project Setup:
 - Based on your OS, download & install the *Intellij* from [here](https://www.jetbrains.com/idea/download/#section=windows)
 - Clone the project from *GitHub*. Follow [these](https://www.jetbrains.com/help/idea/manage-projects-hosted-on-github.html#share-on-GitHub) steps.
 - This project runs on _Openjdk-15_ which is open-source reference implementation of version 15 of the Java SE Platform. (Comes bundled with Intellij)
 - Try running the [main](https://github.com/UjjwalPandey/Vehicle_Parking_System/blob/master/Parking_System/src/main/java/parking/Main.java) function. If any Maven related issue pops up at your system then please [refer here](https://www.jetbrains.com/help/idea/convert-a-regular-project-into-a-maven-project.html#develop_with_maven) 
 - You can edit the [input file](https://github.com/UjjwalPandey/Vehicle_Parking_System/blob/master/Parking_System/src/main/resources/input.txt) at `src/main/resources/input.txt`
 - Also, Test cases covering corner cases can be [seen here](https://github.com/UjjwalPandey/Vehicle_Parking_System/blob/master/Parking_System/src/test/java/parking/controller/ParkingLotControllerTest.java). 

### Steps:
 - `Java setup` Download the JDK 15 from this [link](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html).
    - Follow steps from this [link](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html) based on your OS.
 - Download the project from Git and save it to directory `<say, your-directory>`.
 
#### Run
- Then within terminal reach `<your-directory>\Vehicle_Parking_System\Parking_System\src\main\java\`
 - Compile the code using `javac -cp . parking/Main.java`
 - Run `java parking\Main.java`. 
    - Specify the input FilePath: Default is `<Your-directory>\Vehicle_Parking_System\Parking_System\src\main\resources\input.txt`.

#### Testcase
- From terminal reach till `<your-directory>\Vehicle_Parking_System\Parking_System\`
- Install Maven using `mvn install` if not present:
   - [Download](http://maven.apache.org/download.cgi)  the Binary zip archive
   - Install Maven using [these steps](https://www.javatpoint.com/how-to-install-maven).
 - Run Testcases through `mvn clean test -Dtest=parking.controller.ParkingLotControllerTest`


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
4. `processParking:` This function takes the command and  calls appropriate functions.
5. `checkSlotAvailabilityUtility:` Looks for if Parking Lot is initialized/prepared, and the slots are available.
