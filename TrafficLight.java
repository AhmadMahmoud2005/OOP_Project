package project;

import java.io.*;
import java.util.*;
public class TrafficLight {
    Scanner scanner=new Scanner(System.in);
    private String id;
    private String location;
    private String status;
    private String duration;


    public TrafficLight(String id, String location, String status, String duration) {
        this.id = id;
        this.location = location;
        this.status = status;
        this.duration = duration;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getDuration() {
        return duration;
    }


    @Override
    public String toString() {
        return
                "id=" + id +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", duration=" + duration+"\n"
                ;
    }

}

