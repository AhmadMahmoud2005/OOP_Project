package project;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.*;
public class Vehicle {

    private int vehicleId;
    private String type;
    private String licensePlate;

    private int ownerId;
 //   Owner owner=new Owner();
  public  static ArrayList<Vehicle>vehicleArr=new ArrayList<>();
  public Vehicle(){


  }
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





    public static void saveVehiclesToFile(ArrayList<Vehicle> vehicles, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Vehicle vehicle : vehicles) {
                writer.println(vehicle.toString());
            }
            System.out.println("Data saved successfully  " );
        } catch (IOException e) {
            System.err.println("Error while saving to file: " + e.getMessage());
        }
    }


    public static ArrayList<Vehicle> readVehiclesFromFile(String fileName) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    int vehicleId = Integer.parseInt(parts[0]);
                    String type = parts[1];
                    String licensePlate = parts[2];
                    int ownerId = Integer.parseInt(parts[3]);

                    Vehicle vehicle = new Vehicle(vehicleId, type, licensePlate, ownerId);
                    vehicles.add(vehicle);
                }
            }
            System.out.println("Data loaded successfully  " );
        } catch (IOException e) {
            System.err.println("Error while reading from file: " + e.getMessage());
        }

        return vehicles;
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
