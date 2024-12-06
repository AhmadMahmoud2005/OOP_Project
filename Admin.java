package project;

import java.util.*;
import java.io.*;
public class Admin {
    Scanner scanner=new Scanner(System.in);
    public ArrayList<TrafficLight> arr = new ArrayList<>();

    public void addTrafficLight() {

        String check;
       // int index = 0;

        do {
            System.out.println("enter trafficId: ");
            String id = scanner.next();
            System.out.println("enter location: ");
            String location = scanner.next();
            System.out.println("enter status: ");
            String status = scanner.next();
            System.out.println("enter duration: ");
            String duration = scanner.next();
            TrafficLight t = new TrafficLight(id,location,status,duration);
            arr.add(t);
            System.out.println("Do you want to add another trafficLight: ");
            check = scanner.next();
        } while (check.equals("yes"));

    }


    public void updateTraffic(){
        int index=-1;
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter id of traffic you want to update: ");
        String id=scanner.next();

            System.out.println("enter new id: ");
            String id2=scanner.next();
            System.out.println("enter new status: ");
            String status=scanner.next();
            System.out.println("enter new duration: ");
            String duration= scanner.next();
            System.out.println("enter new location: ");
            String location=scanner.next();
            TrafficLight t2=new TrafficLight(id2,location,status,duration);
        for(int i=0;i<arr.size();i++){
            if(arr.get(i).getId().equals(id))
            {
                index=i;
                break;
            }
        }
        arr.set(index,t2);
    }

    public void delete() {
        System.out.println("enter id of trafficLight you want to delete: ");
        String id = scanner.next();

        for(int i=0;i< arr.size();i++){
            if(arr.get(i).getId().equals(id)){
                arr.remove(i);
            }
        }
    }

    public void viewViolations() {
        /*
        System.out.println("Do you want to view violations by vehicle or zone\n");
        String choice = scanner.next();
        if (choice.equals("vehicle")) {
            System.out.println("enter vehicleId\n");
            String id = scanner.next();


                }


       */
        }









    public void generateReport() {


    }

    }










