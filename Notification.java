package project;

import java.util.Date;
import java.util.ArrayList;

public class Notification {
    private String notificationID;
    private String type; // Violation, Traffic Light Update
    private String message;
    private Date date;
    private String recipientContact; // Email or Phone Number
    private boolean isSent;

    public Notification(){




    }

    public Notification(String notificationID, String type, String message, String recipientContact) {
        this.notificationID = notificationID;
        this.type = type;
        this.message = message;
        this.date = new Date();
        this.recipientContact = recipientContact;
        this.isSent = false;
    }

    public void sendNotification() {

        System.out.println("Sending notification to: " + recipientContact);
        System.out.println("Message: " + message);
        this.isSent = true;
    }











}
