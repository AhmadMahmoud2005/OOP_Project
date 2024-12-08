package project;

import java.util.ArrayList;

public class Vehicle {

    private int vehicleId;
    private String type;
    private String licensePlate;

    private int ownerId;
 //   Owner owner=new Owner();
  public  static ArrayList<Vehicle>vehicleArr=new ArrayList<>();
public Vehicle(int vehicleId,String type,String licensePlate,int ownerId){
    this.vehicleId=vehicleId;
    this.type=type;
    this.licensePlate=licensePlate;
this.ownerId=ownerId;
vehicleArr.add(this);
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


    public String toString() {
        return
                 + vehicleId +
                        "     " + type + "    " +
                         licensePlate + "     " +
                         ownerId+"\n"
                ;
    }
}
