package project;

public class Vehicle {

    private int vehicleId;
    private String type;
    private String licensePlate;

    Owner owner=new Owner();
public Vehicle(int vehicleId,String type,String licensePlate){
    this.vehicleId=vehicleId;
    this.type=type;
    this.licensePlate=licensePlate;

}

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getType() {
        return type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
