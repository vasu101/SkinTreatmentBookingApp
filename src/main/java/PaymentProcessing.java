import javax.swing.*;

public class PaymentProcessing {

    private static AppointmentManager appointmentManager = new AppointmentManager();

    public static void createPaymentUI() {
        JFrame frame = new JFrame("Process Payment");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Appointment ID Label and TextField
        JLabel appointmentLabel = new JLabel("Appointment ID:");
        appointmentLabel.setBounds(10, 20, 120, 25);
        panel.add(appointmentLabel);

        JTextField appointmentText = new JTextField(20);
        appointmentText.setBounds(150, 20, 165, 25);
        panel.add(appointmentText);

        // Process Payment Button
        JButton processPaymentButton = new JButton("Process Payment");
        processPaymentButton.setBounds(10, 80, 200, 25);
        panel.add(processPaymentButton);

        processPaymentButton.addActionListener(e -> {
            String appointmentID = appointmentText.getText();

            if (appointmentID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an Appointment ID.");
                return;
            }

            Appointment appointment = appointmentManager.getAppointmentById(appointmentID);

            if (appointment != null) {
                Treatment treatment = appointment.getTreatment();

                Invoice invoice = new Invoice("INV" + System.currentTimeMillis(), appointment, treatment);

                JOptionPane.showMessageDialog(null, invoice.generateInvoice());
            } else {
                JOptionPane.showMessageDialog(null, "Appointment not found!");
            }
        });

    }
}
