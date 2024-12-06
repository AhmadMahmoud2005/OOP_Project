package project;

import java.io.*;
import java.util.*;

public class TrafficOfficer {

    private int id;
    private String name;
    private String contactInfo;
    private String assignedZone;
    public static int[] zoneIdd = new int[5];
    public static String[] typearr = new String[5];

    public static ArrayList<TrafficViolation> ViolationsArr = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private int count=0;
    public TrafficOfficer(int id, String name, String contactInfo, String assignedZone) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.assignedZone = assignedZone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAssignedZone() {
        return assignedZone;
    }

    public void setAssignedZone(String assignedZone) {

        this.assignedZone = assignedZone;
    }
   // int count2=0,count1=0;
    String check;
    public void record(){
      do {
          System.out.println("enter violationId: ");
          int id1=scanner.nextInt();
          System.out.println("enter vehicleId: ");
          int id2=scanner.nextInt();
          System.out.println("enter zoneId: ");
          int id3=scanner.nextInt();
          System.out.println("enter trafficOfficerId: ");
          int id4=scanner.nextInt();

          System.out.println("enter violationType: ");
          String type=scanner.next();
          System.out.println("enter amount: ");
          double amount=scanner.nextDouble();
          System.out.println("enter date: ");
          String date=scanner.next();
          TrafficViolation violation=new TrafficViolation(id1,id2,id3,id4,type,date,amount);
          ViolationsArr.add(violation);
          System.out.println("Do you want to add another violation: ");
          check = scanner.next();
      } while (check.equals("yes"));


    }

public void viewViolations(){
       System.out.println("enter trafficOfficerId: ");
      int id=scanner.nextInt();
     for(int i=0;i< ViolationsArr.size();i++){
         if(ViolationsArr.get(i).getTrafficOfficerId()==id){
             System.out.println(ViolationsArr.get(i));
         }

     }


}

    public static int findMostFrequent(int[]arr) {
        int mostFrequent = arr[0]; 
        // هنخلي اول عنصر هو اكثر عنصر تكرار
        int maxCount = 0;

        for (int i = 0; i < arr.length; i++) {
            int count = 0;

            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                mostFrequent = arr[i];
            }
        }

        return mostFrequent;
    }

    public static String findMostFrequent(String[] arr) {
        String mostFrequent = arr[0];
        int maxCount = 0;

        for (int i = 0; i < arr.length; i++) {
            int count = 0;

            for (int j = 0; j < arr.length; j++) {
                if (arr[i].equals(arr[j])) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                mostFrequent = arr[i];
            }
        }

        return mostFrequent; 
    }
}

