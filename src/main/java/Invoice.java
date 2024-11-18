public class Invoice {
    private String invoiceID;
    private Appointment appointment;
    private Treatment treatment;
    private double registrationFee;
    private double treatmentFee;
    private double taxAmount;
    private double totalAmount;

    public Invoice(String invoiceID, Appointment appointment, Treatment treatment) {
        this.invoiceID = invoiceID;
        this.appointment = appointment;
        this.treatment = treatment;
        this.registrationFee = 500.00;
        this.treatmentFee = treatment.getPrice();
        this.taxAmount = calculateTax(treatmentFee);
        this.totalAmount = calculateTotalAmount();
    }

    private double calculateTax(double amount) {
        return Math.round(amount * 0.025 * 100.0) / 100.0;
    }

    public double calculateTotalAmount() {
        return Math.round((registrationFee + treatmentFee + taxAmount) * 100.0) / 100.0;
    }

    public String generateInvoice() {
        return "Invoice ID: " + invoiceID + "\n"
                + "Appointment: " + appointment.getAppointmentID() + "\n"
                + "Patient: " + appointment.getPatient().getName() + "\n"
                + "Treatment: " + treatment.getTreatmentType() + "\n"
                + "Registration Fee: LKR " + registrationFee + "\n"
                + "Treatment Fee: LKR " + treatmentFee + "\n"
                + "Tax (2.5%): LKR " + taxAmount + "\n"
                + "Total Amount: LKR " + totalAmount;
    }
}
