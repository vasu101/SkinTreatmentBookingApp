import org.jdatepicker.impl.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.*;

import java.util.Properties;

public class UpdateAppointment {

public static void createUpdateAppointmentUI(Appointment appointment) {
    JFrame frame = new JFrame("Update Appointment");
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
    frame.add(panel);

    JTextField appointmentIdField = new JTextField(appointment.getAppointmentID());
    appointmentIdField.setEditable(false);
    panel.add(new JLabel("Appointment ID:"));
    panel.add(appointmentIdField);

    // Date Picker Setup
    UtilDateModel model = new UtilDateModel();
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");

    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

    panel.add(new JLabel("New Appointment Date:"));
    panel.add(datePicker);

    String[] timeSlots = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00"};
    JComboBox<String> timeComboBox = new JComboBox<>(timeSlots);
    panel.add(new JLabel("New Time:"));
    panel.add(timeComboBox);

    JButton updateButton = new JButton("Update Appointment");
    panel.add(updateButton);

    updateButton.addActionListener(e -> {
        Date selectedDate = (Date) datePicker.getModel().getValue();
        String newDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
        String newTime = (String) timeComboBox.getSelectedItem();

        AppointmentManager appointmentManager = new AppointmentManager();
        boolean isUpdated = appointmentManager.updateAppointment(appointment.getAppointmentID(), newDate, newTime);

        if (isUpdated) {
            JOptionPane.showMessageDialog(frame, "Appointment Updated Successfully!");
            ViewAppointments.refreshTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Update Failed. Appointment not found.");
        }
        frame.dispose();
    });

    frame.setVisible(true);
}

}
