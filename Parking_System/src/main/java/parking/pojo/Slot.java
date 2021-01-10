package parking.pojo;

public class Slot {
    int id;
    Vehicle parkedVehicle;

    public Slot(int id, Vehicle parkedVehicle) {
        this.id = id;
        this.parkedVehicle = parkedVehicle;
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
