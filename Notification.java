package project;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

public class Notification implements Serializable {
    private String notificationID;
    private String type; // Violation, Traffic Light Update
    private String message;
    private Date date;
    private String recipientContact; // Email or Phone Number
    public int ownerId;
    private boolean isSent;

    public Notification(){




    }

    public Notification(String notificationID, String type, String message, String recipientContact, int ownerId) {
        this.notificationID = notificationID;
        this.type = type;
        this.message = message;
        this.date = new Date();
        this.recipientContact = recipientContact;
        this.ownerId = ownerId;

        this.isSent = false;
    }

    public String sendNotification() {
        this.isSent = true;
        return "Sending notification to: " + recipientContact +
                "\nMessage: " + message;
    }











}
