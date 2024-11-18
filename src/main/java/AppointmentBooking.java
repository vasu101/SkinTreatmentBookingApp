import javax.swing.*;
import java.util.List;
import java.util.Properties;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AppointmentBooking {

    private static AppointmentManager appointmentManager = new AppointmentManager();
    private static DermatologistManager dermatologistManager = new DermatologistManager();

    public static void createBookingUI() {
        JFrame frame = new JFrame("Book Appointment");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Patient Name
        JLabel nameLabel = new JLabel("Patient Name:");
        nameLabel.setBounds(10, 20, 120, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(150, 20, 165, 25);
        panel.add(nameText);

        // Patient Email
        JLabel emailLabel = new JLabel("Patient Email:");
        emailLabel.setBounds(10, 60, 120, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(150, 60, 165, 25);
        panel.add(emailText);

        // Patient Contact Number
        JLabel numberLabel = new JLabel("Contact Number:");
        numberLabel.setBounds(10, 100, 120, 25);
        panel.add(numberLabel);

        JTextField numberText = new JTextField(20);
        numberText.setBounds(150, 100, 165, 25);
        panel.add(numberText);

        // Appointment Date
        JLabel dateLabel = new JLabel("Appointment Date:");
        dateLabel.setBounds(10, 140, 120, 25);
        panel.add(dateLabel);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(150, 140, 165, 25);
        panel.add(datePicker);

        // Treatment Type
        JLabel treatmentLabel = new JLabel("Treatment Type:");
        treatmentLabel.setBounds(10, 180, 120, 25);
        panel.add(treatmentLabel);

        String[] treatments = {"Acne Treatment", "Skin Whitening", "Mole Removal", "Laser Treatment"};
        JComboBox<String> treatmentBox = new JComboBox<>(treatments);
        treatmentBox.setBounds(150, 180, 165, 25);
        panel.add(treatmentBox);

        // Dermatologist
        JLabel dermatologistLabel = new JLabel("Dermatologist:");
        dermatologistLabel.setBounds(10, 220, 120, 25);
        panel.add(dermatologistLabel);

        String[] dermatologists = {"Dr. Smith", "Dr. Johnson", "Dr. Lee", "Dr. Patel"};
        JComboBox<String> dermatologistBox = new JComboBox<>(dermatologists);
        dermatologistBox.setBounds(150, 220, 165, 25);
        panel.add(dermatologistBox);

        // Time Slot
        JLabel timeLabel = new JLabel("Time Slot:");
        timeLabel.setBounds(10, 260, 120, 25);
        panel.add(timeLabel);

        JComboBox<String> timeBox = new JComboBox<>();
        timeBox.setBounds(150, 260, 165, 25);
        panel.add(timeBox);

        // Book Appointment Button
        JButton bookButton = new JButton("Book Appointment");
        bookButton.setBounds(10, 300, 200, 25);
        panel.add(bookButton);


        // Update available time slots dynamically
        datePicker.addActionListener(e -> updateAvailableTimeSlots(dermatologistBox, datePicker, timeBox));
        dermatologistBox.addActionListener(e -> updateAvailableTimeSlots(dermatologistBox, datePicker, timeBox));

        // Book appointment on button click
        bookButton.addActionListener(e -> {
            String name = nameText.getText();
            String date = datePicker.getJFormattedTextField().getText();
            String treatmentType = (String) treatmentBox.getSelectedItem();
            String dermatologistName = (String) dermatologistBox.getSelectedItem();
            String time = (String) timeBox.getSelectedItem();
            String email = emailText.getText();
            String number = numberText.getText();

            if (name.isEmpty() || date.isEmpty() || time == null || email.isEmpty() || number.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields and select a time slot.");
                return;
            }

            Dermatologist dermatologist = dermatologistManager.getDermatologistByName(dermatologistName);
            boolean slotBooked = dermatologist.bookTimeSlot(date, time);
            if (!slotBooked) {
                JOptionPane.showMessageDialog(null, "Time slot not available. Please select another slot.");
                return;
            }

            Patient patient = new Patient("P" + System.currentTimeMillis(), name, "NIC001", email, number);
            double treatmentPrice = getTreatmentPrice(treatmentType);
            Treatment treatment = new Treatment("T" + System.currentTimeMillis(), treatmentType, treatmentPrice);

            Appointment appointment = new Appointment("A" + System.currentTimeMillis(), patient, dermatologist, date, time, treatment);
            appointmentManager.addAppointment(appointment);

            JOptionPane.showMessageDialog(null, "Appointment booked successfully!");
        });
    }

    private static void updateAvailableTimeSlots(JComboBox<String> dermatologistBox, JDatePickerImpl datePicker, JComboBox<String> timeBox) {
        String date = datePicker.getJFormattedTextField().getText();
        String dermatologistName = (String) dermatologistBox.getSelectedItem();

        if (date == null || date.isEmpty() || dermatologistName == null || dermatologistName.isEmpty()) {
            return;
        }

        List<String> availableTimes = dermatologistManager.getAvailableTimes(dermatologistName, date);

        timeBox.removeAllItems();
        for (String time : availableTimes) {
            timeBox.addItem(time);
        }
    }

    private static double getTreatmentPrice(String treatmentType) {
        switch (treatmentType) {
            case "Acne Treatment":
                return 2750.00;
            case "Skin Whitening":
                return 7650.00;
            case "Mole Removal":
                return 3850.00;
            case "Laser Treatment":
                return 12500.00;
            default:
                return 0.00;
        }
    }
}
