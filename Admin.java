package project;

import javafx.util.Pair;

import java.util.*;
import java.io.*;
public class Admin {
    Scanner scanner = new Scanner(System.in);

    public static ArrayList<TrafficLight> arr = new ArrayList<>();

    public void addTrafficLight() {

        String check;
        // int index = 0;

        do {

            System.out.println("enter trafficId");
            String id = scanner.next();
            System.out.println("enter location");
            String location = scanner.next();
            System.out.println("enter status");
            String status = scanner.next();
            System.out.println("enter duration");
            String duration = scanner.next();
            TrafficLight t = new TrafficLight(id, location, status, duration);
            arr.add(t);
            System.out.println("Do you want to add another trafficLight");
            check = scanner.next();
        } while (check.equals("yes"));

    }


    public void updateTraffic() {
        int index = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter id of traffic you want to update");
        String id = scanner.next();

        System.out.println("enter new id \n");
        String id2 = scanner.next();
        System.out.println("enter new status\n");
        String status = scanner.next();
        System.out.println("enter new duration\n");
        String duration = scanner.next();
        System.out.println("enter new location");
        String location = scanner.next();
        TrafficLight t2 = new TrafficLight(id2, location, status, duration);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        arr.set(index, t2);


    }


    public void delete() {
        System.out.println("enter id of trafficLight you want to delete ");
        String id = scanner.next();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getId().equals(id)) {
                arr.remove(i);
            }

        }

    }

    public void viewViolations() {

        System.out.println("Do you want to view violations by vehicle or zone");
        String choice = scanner.next();
        if (choice.equals("vehicle")) {
            System.out.println("enter vehicleId\n");
            int id = scanner.nextInt();
            for (int i = 0; i < TrafficOfficer.violationArr.size(); i++)
                if (TrafficOfficer.violationArr.get(i).getTrafficOfficerId() == id) {
                    System.out.println(TrafficOfficer.violationArr.get(i));
                }

        } else if (choice.equals("zone")) {
            System.out.println("enter zoneId\n");
            int zoneId = scanner.nextInt();
            for (int i = 0; i < TrafficOfficer.violationArr.size(); i++)
                if (TrafficOfficer.violationArr.get(i).getTrafficOfficerId() == zoneId) {
                    System.out.println(TrafficOfficer.violationArr.get(i));
                }


        }
    }


    static public Pair<String, String> generateReport() {

        int mostFrequent = TrafficOfficer.violationArr.get(0).getZoneId();
        int maxCount = 0;

        // تكرار كل عنصر في القائمة
        for (int i = 0; i < TrafficOfficer.violationArr.size(); i++) {
            int currentElement = TrafficOfficer.violationArr.get(i).getZoneId();
            int count = 0;

            // حساب عدد مرات تكرار العنصر
            for (int j = 0; j < TrafficOfficer.violationArr.size(); j++) {
                if (TrafficOfficer.violationArr.get(j).getZoneId() == currentElement) {
                    count++;
                }
            }

            // تحديث العنصر الأكثر تكرارًا إذا لزم الأمر
            if (count > maxCount) {
                mostFrequent = currentElement;
                maxCount = count;
            }
        }

        String mostFrequent2 = TrafficOfficer.violationArr.get(0).getViolationType();
        int maxCount2 = 0;

        // تكرار كل عنصر في القائمة
        for (int i = 0; i < TrafficOfficer.violationArr.size(); i++) {
            String currentElement = TrafficOfficer.violationArr.get(i).getViolationType();
            int count = 0;

            // حساب عدد مرات تكرار العنصر
            for (int j = 0; j < TrafficOfficer.violationArr.size(); j++) {
                if (TrafficOfficer.violationArr.get(j).getViolationType().equals(currentElement)) {
                    count++;
                }
            }

            // تحديث العنصر الأكثر تكرارًا إذا لزم الأمر
            if (count > maxCount) {
                mostFrequent2 = currentElement;
                maxCount2 = count;
            }
        }


        System.out.println("the highest density zone is with id :" + mostFrequent);

        System.out.println("the most frequent type of violation :" + mostFrequent2);

        return new Pair<>(Integer.toString(mostFrequent), mostFrequent2);
    }


    public static void saveTrafficLightsToFile(ArrayList<TrafficLight> trafficLights, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            for (TrafficLight light : trafficLights) {
                writer.println(light.toString());
            }
            System.out.println("Traffic lights saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error while saving to file: " + e.getMessage());
        }

    }

    // عشان اقرأ من الملف
    public static ArrayList<TrafficLight> readTrafficLightsFromFile(String fileName) {
//        ArrayList<TrafficLight> trafficLights = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    String id = parts[0];
                    String location = parts[1];
                    String status = parts[2];
                    String duration = parts[3];

                    TrafficLight light = new TrafficLight(id, location, status, duration);
                    arr.add(light);
                }
            }
            System.out.println("Traffic lights loaded successfully from " + fileName);
        } catch (IOException e) {
            System.err.println("Error while reading from file: " + e.getMessage());
        }

        return arr;
    }


}









