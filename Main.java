package project;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

public  static void main(String[]args) {
    Vehicle vehicle = new Vehicle(1, "type", "sss", 55);
    File file = new File("C:\\Users\\Lenovo\\IdeaProjects\\Project\\src\\project\\File1");
    File file2 = new File("C:\\Users\\Lenovo\\IdeaProjects\\Project\\src\\project\\File2");
    File file3 = new File("C:\\Users\\Lenovo\\IdeaProjects\\Project\\src\\project\\File3");

Admin.readTrafficLightsFromFile(String.valueOf(file));
TrafficOfficer.readViolationsFromFile(String.valueOf(file2));
Vehicle.readVehiclesFromFile(String.valueOf(file3));



    Scanner scanner=new Scanner(System.in);
    System.out.println("WELCOME TO OUR PROJECT");
    System.out.println("press:");
    System.out.println("1:for owner");
    System.out.println("2:for admin");
    System.out.println("3:for trafficOfficer");
    int number=scanner.nextInt();

    switch (number){

        case 1:
            Owner owner=new Owner();
            owner.logIn();
            System.out.println("press:");
            System.out.println("1:to viewViolations");
            System.out.println("2:to pay ");
            System.out.println("3:to check");
            int number2= scanner.nextInt();
            if(number2==1)
                owner.viewViolations();
            else if (number2==2) {
                owner.payViolation();
            } else if (number2==3) {
                owner.check();
            }


        case 2:
            Admin admin=new Admin();

            System.out.println("Press: ");
            System.out.println("1 to Add Traffic Lights: ");
            System.out.println("2 to Update Traffic Lights: ");
            System.out.println("3 to Delete Traffic Lights: ");
            System.out.println("4 to View Violations: ");
            System.out.println("5 to Generate Traffic Reports: ");

            int choice=scanner.nextInt();

            switch(choice)
            {
                case 1:
                    admin.addTrafficLight();
                    break;
                case 2:
                    admin.updateTraffic();
                    break;
                case 3:
                    admin.delete();
                    break;
                case 4:
                    admin.viewViolations();
                    break;
                case 5:
                    admin.generateReport();
                    break;
                default:
                    System.out.println("Invalid choice");
            }



        case 3:
            TrafficOfficer trafficOfficer=new TrafficOfficer();

            System.out.println("Press: ");
            System.out.println("1 to Record Violations: ");
            System.out.println("2 to View All Violations : ");
            System.out.println("3 to send notification to owner : ");

            int number3=scanner.nextInt();

            switch (number3)
            {
                case 1:
                    trafficOfficer.record();
                    break;
                case 2:
                    trafficOfficer.viewViolations();
                    break;
                case 3:
                    Notification notification=new Notification();

                default:
                    System.out.println("Invalid choice");
            }
    }

Admin.saveTrafficLightsToFile(Admin.arr, String.valueOf(file));
TrafficOfficer.saveViolationsToFile(TrafficOfficer.violationArr, String.valueOf(file2));
Vehicle.saveVehiclesToFile(Vehicle.vehicleArr, String.valueOf(file3));



}


}
