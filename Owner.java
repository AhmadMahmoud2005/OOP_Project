package project;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Owner extends Staff {
    //private int ownerId;
    //private String name;

    private String email;
    private String phone;
    private String address;
    private String password;
    public ArrayList<Vehicle> arr = new ArrayList<>();
    public ArrayList<String> notifications= new ArrayList<>();

    public Owner(int ownerId,String name,String email,String phone,String address,String password){
        this.id=ownerId;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.address=address;
        this.password=password;
     }




    public void setOwnerId(int ownerId) {
        this.id = ownerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public int getId() {return id;}

    public void addNotification(String notification) {
        notifications.add(notification);
    }

    @Override
    public void viewViolations() {
        System.out.println("enter vehicleId");
        int id=scanner.nextInt();
        for (int i=0;i<TrafficOfficer.violationArr.size();i++){
            if(TrafficOfficer.violationArr.get(i).getVehicleId()==id){
                System.out.println(TrafficOfficer.violationArr.get(i));
            }
        }

    }




    Scanner scanner = new Scanner(System.in);
    public static ArrayList<Owner> owners = new ArrayList<>();




    public void logIn() {
    Boolean check=false;
    System.out.println("Do you have an account");
    check=scanner.nextBoolean();

    if(!check){
        System.out.println("please,enter your username");
        String name=scanner.next();
        System.out.println("please,enter your id");
        int id=scanner.nextInt();
        System.out.println("please,enter your email");
        String email=scanner.next();
        System.out.println("please,enter your password");
        String password=scanner.next();
        System.out.println("please,enter your contactInfo");
        String contact=scanner.next();
        System.out.println("please,enter your address");
        String address=scanner.next();
        System.out.println("successfully");
        Owner owner1=new Owner(id,name,email,phone,address,password);
owners.add(owner1);
    }
       else if(check) {
           boolean ch=false;
           do {
               System.out.println("please,enter your username");
               String name=scanner.next();
               System.out.println("please,enter your password");
               String password=scanner.next();
               for(int i=0;i<owners.size();i++){
                   if(owners.get(i).name.equals(name)&&owners.get(i).password.equals(password)){
                       ch=true;
                       System.out.println("successfully");
                       break;
                   }
               }

           }while(!ch);

        }
    }

    public void payViolation() {
        System.out.println("enter id of violation you want to pay");
int id=scanner.nextInt();
        System.out.println("enter fine amount");
double amount=scanner.nextDouble();
for(int i=0;i<TrafficOfficer.violationArr.size();i++){
    if(TrafficOfficer.violationArr.get(i).getViolationId()==id&&TrafficOfficer.violationArr.get(i).getFineAmount()==amount){
        TrafficOfficer.violationArr.remove(i);
    }


}


    }

    public void check(){

        System.out.println("enter vehicleId");
        int id=scanner.nextInt();
        for(int i=0;i<Vehicle.vehicleArr.size();i++){
            if(Vehicle.vehicleArr.get(i).getVehicleId()==id)
            {
                System.out.println("the details of car:");
                System.out.println(Vehicle.vehicleArr.get(i));
            }
        }
for (int i=0;i<TrafficOfficer.violationArr.size();i++){

    if(TrafficOfficer.violationArr.get(i).getVehicleId()==id){
        System.out.println("the vehicle has violation:");
        System.out.println(TrafficOfficer.violationArr.get(i));
    }


}

    }

    public static void saveOwnersIntoFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Owner owner : owners) {
                // Save each owner as a single line
                writer.println(owner.id + "," +
                        owner.name + "," +
                        owner.email + "," +
                        owner.contactInfo + "," +
                        owner.address + "," +
                        owner.password + "," +
                        formatVehicles(owner.arr));
                Vehicle.vehicleArr.addAll(owner.arr);
            }
            System.out.println("Owners saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    private static String formatVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles == null || vehicles.size() == 0) {
            return "NoVehicles";
        }
        StringBuilder vehiclesData = new StringBuilder();
        for (Vehicle vehicle : vehicles) {
            vehiclesData.append(vehicle.getVehicleId()).append("|")
                    .append(vehicle.getType()).append("|")
                    .append(vehicle.getLicensePlate()).append("|")
                    .append(vehicle.getOwnerId()).append(";");
        }
        // Remove trailing semicolon
        if (vehiclesData.length() > 0) {
            vehiclesData.setLength(vehiclesData.length() - 1);
        }
        return vehiclesData.toString();
    }

    public static ArrayList<Owner> readOwnersFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by commas
                String[] parts = line.split(",", 7); // Split into at most 7 parts
                if (parts.length < 6) {
                    System.err.println("Invalid line: " + line);
                    continue;
                }

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String email = parts[2].trim();
                String contactInfo = parts[3].trim();
                String address = parts[4].trim();
                String password = parts[5].trim();

                // Create the owner object
                Owner owner = new Owner(id, name, email, contactInfo, address, password);

                // Read vehicle data if available
                if (parts.length > 6) {
                    owner.arr = parseVehicles(parts[6].trim());
                } else {
                    owner.arr = new ArrayList<>(); // No vehicles
                }

                owners.add(owner);
            }
            System.out.println("Owners loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }

        return owners;
    }

    private static ArrayList<Vehicle> parseVehicles(String vehiclesData) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        if (vehiclesData.equals("NoVehicles")) {
            return vehicles; // Return empty list
        }

        String[] vehicleParts = vehiclesData.split(";");
        for (String vehicleData : vehicleParts) {
            String[] vehicleDetails = vehicleData.split("\\|");
            if (vehicleDetails.length < 4) {
                System.err.println("Invalid vehicle data: " + vehicleData);
                continue;
            }
            int vehicleId = Integer.parseInt(vehicleDetails[0].trim());
            String type = vehicleDetails[1].trim();
            String licensePlate = vehicleDetails[2].trim();
            int ownerId = Integer.parseInt(vehicleDetails[3].trim());
            vehicles.add(new Vehicle(vehicleId, type, licensePlate, ownerId));
        }

        return vehicles;
    }
}
