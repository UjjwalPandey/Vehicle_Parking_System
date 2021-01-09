package parking.pojo;

public class Slot {
    int id;
    Vehicle parkedVehicle;
    boolean isVacant;

    public Slot(int id, Vehicle parkedVehicleId, boolean isVacant) {
        this.id = id;
        this.parkedVehicle = parkedVehicleId;
        this.isVacant = isVacant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public boolean isVacant() {
        return isVacant;
    }

    public void setVacant(boolean vacant) {
        isVacant = vacant;
    }


}
