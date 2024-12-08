package project;

public class TrafficViolation {
    private int violationId;
    private int vehicleId;
    private int trafficOfficerId;
    private String violationType;
    private String date;
    private double fineAmount;
    private int zoneId;

    // Constructor
    public TrafficViolation(int violationId, int vehicleId,int zoneId,int trafficOfficerId, String violationType, String date, double fineAmount) {
        this.violationId = violationId;
        this.vehicleId = vehicleId;
        this.trafficOfficerId=trafficOfficerId;
        this.zoneId=zoneId;
        this.violationType = violationType;
        this.date = date;
        this.fineAmount = fineAmount;
    }

    // Getters and Setters
    public int getViolationId() {
        return violationId;
    }

    public void setViolationId(int violationId) {
        this.violationId = violationId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public void setTrafficOfficerId(int trafficOfficerId) {
        this.trafficOfficerId = trafficOfficerId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public int getTrafficOfficerId() {
        return trafficOfficerId;
    }

    public int getZoneId() {
        return zoneId;
    }




    @Override
    public String toString() {
        return
                 violationId +"    "+ vehicleId +"    "+ violationType+"     " +trafficOfficerId+ "     " +zoneId+
                      "     "+ date+"     "+fineAmount

                ;
    }
}
