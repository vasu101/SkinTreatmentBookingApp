public class Patient {
    private String patientID;
    private String name;
    private String NIC;
    private String email;
    private String phone;

    public Patient(String patientID, String name, String NIC, String email, String phone) {
        this.patientID = patientID;
        this.name = name;
        this.NIC = NIC;
        this.email = email;
        this.phone = phone;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }

    public String getNIC() {
        return NIC;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPatientDetails() {
        return "ID: " + patientID + ", Name: " + name + ", Email: " + email + ", Phone: " + phone;
    }
}
