package project;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("WELCOME TO OUR PROJECT");
        Owner owner = new Owner();
        //owner.payViolation();
     //   TrafficLight t = new TrafficLight("5", "giza", "red", 5);
        TrafficOfficer t1 = new TrafficOfficer(1, "officer1", "01102641995", "zone1");

   ;
t1.record();
        Admin admin = new Admin();
admin.generateReport();
        //System.out.println(admin.arr);

    }
}