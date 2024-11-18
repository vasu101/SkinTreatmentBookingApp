import javax.swing.*;
import java.awt.*;

public class ClinicApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aurora Skin Care Clinic");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        setupMainMenu(panel);
        panel.setBackground(new Color(204, 204, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



        frame.setVisible(true);
    }

    private static void setupMainMenu(JPanel panel) {
        panel.setLayout(new GridLayout(4, 1));

        JButton bookAppointmentButton = new JButton("Book Appointment");
        panel.add(bookAppointmentButton);

        JButton viewAppointmentsButton = new JButton("View Appointments");
        panel.add(viewAppointmentsButton);


        JButton processPaymentButton = new JButton("Process Payment");
        panel.add(processPaymentButton);

        JButton generateInvoiceButton = new JButton("Generate Invoice");
        panel.add(generateInvoiceButton);

        bookAppointmentButton.addActionListener(e -> AppointmentBooking.createBookingUI());
        viewAppointmentsButton.addActionListener(e -> ViewAppointments.createViewAppointmentsUI());
        processPaymentButton.addActionListener(e -> PaymentProcessing.createPaymentUI());
        generateInvoiceButton.addActionListener(e -> InvoiceGeneration.createInvoiceUI());
    }
}
