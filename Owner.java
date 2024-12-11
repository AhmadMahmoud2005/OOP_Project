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
 public Vehicle arr[]=new Vehicle[3];

public Owner(){




}

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




    Scanner scanner=new Scanner(System.in);
    public ArrayList<Owner>owner=new ArrayList<>();




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
owner.add(owner1);
    }




       else if(check) {
           boolean ch=false;
           do {
               System.out.println("please,enter your username");
               String name=scanner.next();
               System.out.println("please,enter your password");
               String password=scanner.next();
               for(int i=0;i<owner.size();i++){
                   if(owner.get(i).name.equals(name)&&owner.get(i).password.equals(password)){
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





    public  void check(){

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

}
