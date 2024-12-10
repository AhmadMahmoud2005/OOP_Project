package project;

import java.util.ArrayList;

public class NotificationSystem {
    private ArrayList<Notification> notifications;

    public NotificationSystem() {
        notifications = new ArrayList<>();
    }

    public void createAndSendNotification(TrafficViolation violation, Owner owner) {
        // Prepare message
        String message = "Dear " + owner.getName() + ",\n" +
                "You have a traffic violation:\n" +
                "Type: " + violation.getViolationType() + "\n" +
                "Fine: $" + violation.getFineAmount() + "\n" +
                "Date: " + violation.getDate();

        // Create notification
        Notification notification = new Notification(
                "N" + (notifications.size() + 1),
                "Violation",
                message,
                owner.getPhone()
        );


        notification.sendNotification();

        // Save notification to the system
        notifications.add(notification);
    }

    public void viewAllNotifications() {
        for (Notification n : notifications) {
            System.out.println(n);
        }
    }
}
