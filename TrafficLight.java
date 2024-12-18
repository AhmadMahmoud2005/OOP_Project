package project;

import java.io.*;
import java.util.*;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Scanner;

public class TrafficLight {
    Scanner scanner=new Scanner(System.in);
    private StringProperty id; // Changed to StringProperty
    private StringProperty location; // Changed to StringProperty
    private StringProperty status; // Changed to StringProperty
    private StringProperty duration; // Changed to StringProperty

    // Constructor
    public TrafficLight(String id, String location, String status, String duration) {
        this.id = new SimpleStringProperty(id); // Initialize StringProperty
        this.location = new SimpleStringProperty(location); // Initialize StringProperty
        this.status = new SimpleStringProperty(status); // Initialize StringProperty
        this.duration = new SimpleStringProperty(duration); // Initialize StringProperty
    }

    // Getter and Setter for id
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id); // Set the value for StringProperty
    }

    public StringProperty idProperty() {
        return id; // Return the StringProperty for binding
    }

    // Getter and Setter for location
    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location); // Set the value for StringProperty
    }

    public StringProperty locationProperty() {
        return location; // Return the StringProperty for binding
    }

    // Getter and Setter for status
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status); // Set the value for StringProperty
    }

    public StringProperty statusProperty() {
        return status; // Return the StringProperty for binding
    }

    // Getter and Setter for duration
    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String duration) {
        this.duration.set(duration); // Set the value for StringProperty
    }

    public StringProperty durationProperty() {
        return duration; // Return the StringProperty for binding
    }

    @Override
    public String toString() {
        return
                getId() +
                "," + getLocation() +
                "," + getStatus() +
                "," + getDuration()+"\n"
                ;
    }

}

