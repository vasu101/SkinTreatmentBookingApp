import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {

    private static final String FILE_PATH = "appointments.csv";
    private List<Appointment> appointments;

    public AppointmentManager() {
        this.appointments = new ArrayList<>();
        loadAppointmentsFromFile();
    }

    // Method to add an appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        saveAppointmentToFile(appointment);
    }

    // Method to load appointments from a file (on startup)
    private void loadAppointmentsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                // Ensure the details array has the expected number of fields
                if (details.length < 13) {
                    System.err.println("Invalid appointment data: " + line); // Log the invalid line
                    continue;
                }

                // Create Patient
                Patient patient = new Patient(details[1], details[2], details[3], details[4], details[5]);
                // Create Dermatologist
                Dermatologist dermatologist = new Dermatologist(details[8], details[9], new String[]{"Monday", "Wednesday"}); // Modify if necessary
                // Create Treatment
                Treatment treatment = new Treatment(details[10], details[11], Double.parseDouble(details[12]));
                // Create Appointment
                Appointment appointment = new Appointment(details[0], patient, dermatologist, details[6], details[7], treatment);
                appointments.add(appointment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing appointment
    public boolean updateAppointment(String appointmentID, String newDate, String newTime) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointmentID)) {
                appointment.setDate(newDate);
                appointment.setTime(newTime);
                saveAppointmentsToFile();
                return true;
            }
        }
        return false;
    }
    // Method to save the entire list of appointments to the file
    private void saveAppointmentsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Appointment appointment : appointments) {
                bw.write(appointment.toCSVFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save a new appointment to the file
    private void saveAppointmentToFile(Appointment appointment) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(appointment.getAppointmentID() + "," + appointment.getPatient().getPatientID() + ","
                    + appointment.getPatient().getName() + "," + appointment.getPatient().getNIC() + ","
                    + appointment.getPatient().getEmail() + "," + appointment.getPatient().getPhone() + ","
                    + appointment.getDate() + "," + appointment.getTime() + ","
                    + appointment.getDermatologist().getDermatologistID() + ","
                    + appointment.getDermatologist().getName() + ","
                    + appointment.getTreatment().getTreatmentID() + ","
                    + appointment.getTreatment().getTreatmentType() + ","
                    + appointment.getTreatment().getPrice());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get all appointments
    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Method to get an appointment by ID
    public Appointment getAppointmentById(String appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointmentID)) {
                return appointment;
            }
        }
        return null;
    }
}
