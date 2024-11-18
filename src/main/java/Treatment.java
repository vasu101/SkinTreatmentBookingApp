public class Treatment {
    private String treatmentID;
    private String treatmentType;
    private double price;

    public Treatment(String treatmentID, String treatmentType, double price) {
        this.treatmentID = treatmentID;
        this.treatmentType = treatmentType;
        this.price = price;
    }

    public String getTreatmentID() {
        return treatmentID;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public double getPrice() {
        return price;
    }

    public String getTreatmentDetails() {
        return "Treatment: " + treatmentType + ", Price: " + price;
    }
}
