import javax.swing.*;
import java.awt.*;

public class InvoiceGeneration {

    private static AppointmentManager appointmentManager = new AppointmentManager();

    public static void createInvoiceUI() {
        JFrame frame = new JFrame("Generate Invoice");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel appointmentLabel = new JLabel("Appointment ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(appointmentLabel, gbc);

        JTextField appointmentText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(appointmentText, gbc);

        JButton generateInvoiceButton = new JButton("Generate Invoice");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(generateInvoiceButton, gbc);

        generateInvoiceButton.addActionListener(e -> {
            String appointmentID = appointmentText.getText();

            if (appointmentID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an Appointment ID.");
                return;
            }

            Appointment appointment = appointmentManager.getAppointmentById(appointmentID);

            if (appointment != null) {
                Treatment treatment = appointment.getTreatment();
                Invoice invoice = new Invoice("INV" + System.currentTimeMillis(), appointment, treatment);

                String invoiceDetails = invoice.generateInvoice();
                JOptionPane.showMessageDialog(null, invoiceDetails);
            } else {
                JOptionPane.showMessageDialog(null, "Appointment not found!");
            }
        });
    }
}
