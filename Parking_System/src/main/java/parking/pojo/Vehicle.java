package parking.pojo;

public class Vehicle {
    String registrationId;
    int driverAge;

    public Vehicle(String registrationId, int driverAge) {
        this.registrationId = registrationId;
        this.driverAge = driverAge;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationId='" + registrationId + '\'' +
                ", driverAge=" + driverAge +
                '}';
    }
}

