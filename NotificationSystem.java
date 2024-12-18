package project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;

public class NotificationSystem implements Serializable {
    public static ArrayList<Notification> notifications = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public void createAndSendNotification(int vehicleId) {
        TrafficViolation violation = null;

        // Find the violation by vehicleId
        for (TrafficViolation v : TrafficOfficer.violationArr) {
            if (v.getVehicleId() == vehicleId) {
                violation = v;
                break;
            }
        }

        // Check if the violation was found
        if (violation == null) {
            System.err.println("No violation found for Vehicle ID: " + vehicleId);
            return;
        }

        // Find the owner associated with the vehicle
        Owner target = null;
        for (Owner owner : Owner.owners) {
            for (Vehicle v : owner.arr) {
                if (v.getVehicleId() == violation.getVehicleId()) {
                    target = owner;
                }
            }
            if (target != null) break; // Exit outer loop if owner is found
        }

        // Check if the owner was found
        if (target == null) {
            System.err.println("No owner found for Vehicle ID: " + vehicleId);
            return;
        }

        // Prepare the notification message
        String message = "Dear " + target.getName() + ",\n" +
                "You have a traffic violation of ID: " + violation.getViolationId() + "\n" +
                "Type: " + violation.getViolationType() + "\n" +
                "Fine: $" + violation.getFineAmount() + "\n" +
                "Date: " + violation.getDate();

        // Create the notification
        Notification notification = new Notification(
                "N" + (notifications.size() + 1), // Generate unique ID
                "Violation",
                message,
                target.getEmail(),
                target.id
        );

        // Add the notification to the system and the owner's list
        notifications.add(notification);
        target.addNotification(notification.sendNotification());

        System.out.println("Notification sent successfully to: " + target.getName());
    }

    public void checkNotification() {
        for (Notification notification : notifications) {
            for (Owner owner : Owner.owners) {
                if (owner.id == notification.ownerId)
                    owner.addNotification(notification.sendNotification());
            }
        }
    }

    // Save notifications to a file
    public static void saveNotificationsToFile(String fileName, ArrayList<Notification> notifications) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(notifications);
            System.out.println("Notifications saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving notifications: " + e.getMessage());
        }
    }

    // Load notifications from a file
    @SuppressWarnings("unchecked")
    public static ArrayList<Notification> loadNotificationsFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            notifications = (ArrayList<Notification>) ois.readObject();
            System.out.println("Notifications loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing notifications found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading notifications: " + e.getMessage());
        }
        return notifications;
    }
}
