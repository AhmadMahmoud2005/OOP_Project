package project;

public class EmergencyVehicle extends Vehicle {

    private int priorityLevel;
    private boolean emergencyState;
    private String destination;


    public EmergencyVehicle(){

    }
    public EmergencyVehicle(int id,String type,String licensePlat,int ownerId,
                            int priorityLevel,String destination){
        super(id,type,licensePlat,ownerId);
       this.priorityLevel=priorityLevel;
       this.emergencyState=false;



    }
    public void siren(){
        if(emergencyState)
            System.out.println("Siren is enabled for vehicle "+getVehicleId());
        else
            System.out.println("Siren is disabled for vehicle "+getVehicleId());


    }
public void updateEmergencyState(boolean status){
        this.emergencyState=status;
}
public void changeTrafficLight(){
        if(this.emergencyState) {
            TrafficLight trafficLight = new TrafficLight();
            trafficLight.setStatus("green");


        }

}

}
