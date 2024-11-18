import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewAppointments {

    private static AppointmentManager appointmentManager = new AppointmentManager();
    private static JTable table;

    public static void createViewAppointmentsUI() {
        JFrame frame = new JFrame("View Appointments");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel searchLabel = new JLabel("Search by Patient Name, Appointment ID, or Contact Number:");
        searchLabel.setBounds(10, 20, 400, 25);
        panel.add(searchLabel);

        JTextField searchText = new JTextField(20);
        searchText.setBounds(400, 20, 200, 25);
        panel.add(searchText);

        JLabel dateLabel = new JLabel("Filter by Date:");
        dateLabel.setBounds(10, 55, 100, 25);
        panel.add(dateLabel);

        JTextField dateFilterText = new JTextField(10);
        dateFilterText.setBounds(110, 55, 120, 25);
        panel.add(dateFilterText);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(610, 20, 90, 25);
        panel.add(searchButton);

        JButton updateButton = new JButton("Update Appointment");
        updateButton.setBounds(320, 400, 140, 25);
        panel.add(updateButton);

        // Table to display appointments
        String[] columnNames = {"Appointment ID", "Patient Name", "Patient Number", "Patient Email", "Date", "Time", "Dermatologist"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model); // Initialize the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 90, 760, 300);
        panel.add(scrollPane);

        // Load appointments into table
        loadAppointments(model, "", "");

        // Action listener for search button
        searchButton.addActionListener(e -> {
            String query = searchText.getText().trim().toLowerCase();
            String dateFilter = dateFilterText.getText().trim();
            loadAppointments(model, query, dateFilter);
        });

        // Action listener for the update button
        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select an appointment to update.");
            } else {
                String appointmentID = model.getValueAt(selectedRow, 0).toString();
                Appointment selectedAppointment = appointmentManager.getAppointmentById(appointmentID);

                if (selectedAppointment != null) {
                    UpdateAppointment.createUpdateAppointmentUI(selectedAppointment);
                } else {
                    JOptionPane.showMessageDialog(panel, "Error: Appointment not found.");
                }
            }
        });
    }

    // Method to load appointments into the table
    private static void loadAppointments(DefaultTableModel model, String query, String dateFilter) {
        model.setRowCount(0);
        List<Appointment> appointments = appointmentManager.getAppointments();

        if (appointments == null) return;

        for (Appointment appt : appointments) {
            String patientName = appt.getPatient().getName().toLowerCase();
            String appointmentID = appt.getAppointmentID().toLowerCase();
            String patientNumber = appt.getPatient().getPhone();
            String patientEmail = appt.getPatient().getEmail();
            String appointmentDate = appt.getDate();

            boolean matchesQuery = query.isEmpty() || patientName.contains(query) || appointmentID.contains(query) || patientNumber.contains(query);
            boolean matchesDate = dateFilter.isEmpty() || appointmentDate.equals(dateFilter);

            if (matchesQuery && matchesDate) {
                Object[] rowData = {
                        appt.getAppointmentID(),
                        appt.getPatient().getName(),
                        appt.getPatient().getPhone(),
                        appt.getPatient().getEmail(),
                        appt.getDate(),
                        appt.getTime(),
                        appt.getDermatologist().getName()
                };
                model.addRow(rowData);
            }
        }
    }

    public static void refreshTable() {
        if (table != null) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            loadAppointments(model, "", "");
        }
    }
}
