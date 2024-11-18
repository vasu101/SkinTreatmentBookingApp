public class Appointment {
    private String appointmentID;
    private Patient patient;
    private Dermatologist dermatologist;
    private String date;
    private String time;
    private boolean isPaid;
    private Treatment treatment;

    public Appointment(String appointmentID, Patient patient, Dermatologist dermatologist, String date, String time, Treatment treatment) {
        this.appointmentID = appointmentID;
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.date = date;
        this.time = time;
        this.treatment = treatment;
        this.isPaid = false;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public Patient getPatient() {
        return patient;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Treatment getTreatment(){
        return treatment;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAppointmentDetails() {
        return "Appointment ID: " + appointmentID + ", Patient: " + patient.getName() + ", Date: " + date + ", Time: " + time;
    }

    public String toCSVFormat() {
        return String.join(",",
                appointmentID,
                patient.getPatientID(),
                patient.getName(),
                patient.getNIC(),
                patient.getEmail(),
                patient.getPhone(),
                date,
                time,
                dermatologist.getDermatologistID(),
                dermatologist.getName(),
                treatment.getTreatmentID(),
                treatment.getTreatmentType(),
                String.valueOf(treatment.getPrice())
        );
    }
}
