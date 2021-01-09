package parking.pojo;

public class Slot {
    int id;
    Vehicle parkedVehicle;

    public Slot(int id, Vehicle parkedVehicleId) {
        this.id = id;
        this.parkedVehicle = parkedVehicleId;
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
}
