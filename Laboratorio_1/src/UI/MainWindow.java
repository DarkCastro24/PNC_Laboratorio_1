package UI;

import Model.DTO.DoctorDTO;
import Model.DTO.PatientDTO;
import Model.Entity.Appointment;
import Model.Entity.Doctor;
import Model.Entity.FutureAppointment;
import Model.Entity.Patient;
import Model.Entity.Speciality;
import Model.Entity.TodayAppointment;
import Service.AppointmentService;
import Service.DoctorService;
import Service.PatientService;
import Utils.DateUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MainWindow extends JFrame {

    private final DoctorService doctorService = new DoctorService();
    private final PatientService patientService = new PatientService();
    private final AppointmentService appointmentService = new AppointmentService();
    private final List<Speciality> specialtiesList = Speciality.loadSpecialties();

    public MainWindow() {
        setTitle("Zaun Hospital System");
        setSize(550, 770);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(36, 36, 36));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 25, 25));
        JLabel titleLabel = new JLabel("Sistema de Hospital Zaun", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 3, 10, 10));
        buttonPanel.setBackground(new Color(36, 36, 36));

        JPanel row1 = new JPanel(new GridLayout(1, 3, 10, 10));
        row1.setBackground(new Color(36, 36, 36));
        JButton btnScheduleAppointment = createButton("New appointment");
        JButton btnViewAppointments = createButton("Look appointment");
        JButton btnAddPatient = createButton("Add patient");
        row1.add(btnScheduleAppointment);
        row1.add(btnViewAppointments);
        row1.add(btnAddPatient);

        JPanel row2 = new JPanel(new GridLayout(1, 3, 10, 10));
        row2.setBackground(new Color(36, 36, 36));
        JButton btnAddDoctor = createButton("Add doctor");
        JButton btnSearchAppointmentsByCode = createButton("Search for code");
        JButton btnCancelAppointment = createButton("Cancel appointment");
        row2.add(btnAddDoctor);
        row2.add(btnSearchAppointmentsByCode);
        row2.add(btnCancelAppointment);

        JPanel row3 = new JPanel(new GridLayout(1, 1, 10, 10));
        row3.setBackground(new Color(36, 36, 36));
        JButton btnDecorative = createButton("Mundo saves lives!");
        row3.add(btnDecorative);

        buttonPanel.add(row1);
        buttonPanel.add(row2);
        buttonPanel.add(row3);
        add(buttonPanel, BorderLayout.CENTER);

        JButton btnExit = createButton("Exit", new Color(200, 50, 50));
        btnExit.setPreferredSize(new Dimension(500, 80));
        add(btnExit, BorderLayout.SOUTH);

        btnScheduleAppointment.addActionListener(e -> scheduleAppointment());
        btnViewAppointments.addActionListener(e -> viewAppointments());
        btnAddPatient.addActionListener(e -> addPatient());
        btnAddDoctor.addActionListener(e -> addDoctor());
        btnSearchAppointmentsByCode.addActionListener(e -> searchAppointmentsByDoctor());
        btnCancelAppointment.addActionListener(e -> cancelAppointment());
        btnDecorative.addActionListener(e -> showMessage("¡MUNDO SALVA VIDAS!"));
        btnExit.addActionListener(e -> System.exit(0));
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        button.setPreferredSize(new Dimension(170, 60));
        return button;
    }

    private JButton createButton(String text) {
        return createButton(text, new Color(0, 200, 140));
    }

    private void showMessage(String message) {
        UIManager.put("OptionPane.background", new Color(45, 45, 45));
        UIManager.put("Panel.background", new Color(45, 45, 45));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(0, 200, 140));
        UIManager.put("Button.foreground", Color.WHITE);
        JOptionPane.showMessageDialog(this, message);
    }

    private JTextField createInputField() {
        JTextField field = new JTextField();
        field.setBackground(new Color(60, 63, 65));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        return field;
    }

    private void addPatient() {
        JTextField firstNameField = createInputField();
        JTextField lastNameField = createInputField();
        JTextField duiField = createInputField();
        JTextField birthdateField = createInputField();
        Object[] message = {
                "Nombre:", firstNameField,
                "Apellido:", lastNameField,
                "DUI:", duiField,
                "Cumpleaños (dd-MM-yyyy):", birthdateField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Agregar Paciente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (!DateUtils.isValidDateFormat(birthdateField.getText())) {
                showMessage("La fecha ingresada es inválida, use el formato dd-MM-yyyy.");
                return;
            }
            PatientDTO newPatient = new PatientDTO(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    duiField.getText(),
                    birthdateField.getText()
            );
            patientService.addPatient(newPatient);
            showMessage("Paciente agregado exitosamente.");
        } else {
            showMessage("Operación cancelada.");
        }
    }

    private void addDoctor() {
        JTextField firstNameField = createInputField();
        JTextField lastNameField = createInputField();
        JTextField duiField = createInputField();
        JTextField birthdateField = createInputField();
        JTextField recruitmentField = createInputField();
        String[] specialtiesArr = new String[specialtiesList.size()];
        for (int i = 0; i < specialtiesList.size(); i++) {
            specialtiesArr[i] = specialtiesList.get(i).getName();
        }
        JComboBox<String> specialtyCombo = new JComboBox<>(specialtiesArr);
        specialtyCombo.setBackground(new Color(60, 63, 65));
        specialtyCombo.setForeground(Color.WHITE);
        Object[] message = {
                "Nombre:", firstNameField,
                "Apellido:", lastNameField,
                "DUI:", duiField,
                "Cumpleaños (dd-MM-yyyy):", birthdateField,
                "Reclutamiento (dd-MM-yyyy):", recruitmentField,
                "Especialidad:", specialtyCombo
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Agregar Doctor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (!DateUtils.isValidDateFormat(birthdateField.getText())) {
                showMessage("La fecha de cumpleaños es inválida. Formato: dd-MM-yyyy");
                return;
            }
            if (!DateUtils.isValidDateFormat(recruitmentField.getText())) {
                showMessage("La fecha de reclutamiento es inválida. Formato: dd-MM-yyyy");
                return;
            }
            DoctorDTO newDoctor = new DoctorDTO(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    duiField.getText(),
                    birthdateField.getText(),
                    recruitmentField.getText(),
                    (String) specialtyCombo.getSelectedItem()
            );
            String code = doctorService.addDoctor(newDoctor);
            if (code != null && !code.isEmpty()) {
                showMessage("Doctor agregado exitosamente. Código: " + code);
            } else {
                showMessage("No se pudo obtener el código del doctor.");
            }
        } else {
            showMessage("Operación cancelada.");
        }
    }

    private void scheduleAppointment() {
        JTextField duiField = createInputField();
        JTextField doctorCodeField = createInputField();
        JTextField dateField = createInputField();
        JTextField timeField = createInputField();
        Object[] message = {
                "DUI del paciente:", duiField,
                "Código del doctor:", doctorCodeField,
                "Fecha de la cita (dd-MM-yyyy):", dateField,
                "Hora de la cita (HH:mm):", timeField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Agendar Nueva Cita", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Patient patient = patientService.getPatientByDui(duiField.getText());
            if (patient == null) {
                showMessage("El paciente no fue encontrado. Regístrelo primero.");
                return;
            }
            Doctor doctor = doctorService.getDoctorByCodigo(doctorCodeField.getText());
            if (doctor == null) {
                showMessage("El doctor no fue encontrado. Verifique el código o registre al doctor.");
                return;
            }
            if (!DateUtils.isValidDateFormat(dateField.getText())) {
                showMessage("La fecha ingresada no es válida. Formato: dd-MM-yyyy");
                return;
            }
            LocalDate appointmentDate = DateUtils.parseDate(dateField.getText());
            if (appointmentDate.isBefore(LocalDate.now())) {
                showMessage("No se puede agendar una cita en el pasado.");
                return;
            }
            LocalTime appointmentTime;
            try {
                appointmentTime = LocalTime.parse(timeField.getText());
            } catch (Exception e) {
                showMessage("La hora no es válida. Formato: HH:mm (24h).");
                return;
            }
            Appointment newAppointment;
            if (appointmentDate.equals(LocalDate.now())) {
                newAppointment = new TodayAppointment(
                        doctor,
                        patient,
                        doctor.getSpecialty(),
                        appointmentDate,
                        appointmentTime
                );
            } else {
                newAppointment = new FutureAppointment(
                        doctor,
                        patient,
                        doctor.getSpecialty(),
                        appointmentDate,
                        appointmentTime
                );
            }
            boolean added = appointmentService.addAppointment(newAppointment);
            if (!added) {
                showMessage("No se pudo agendar la cita debido a conflicto de horarios.");
            } else {
                showMessage("Cita agendada exitosamente.");
            }
        } else {
            showMessage("Operación cancelada.");
        }
    }

    private void viewAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        if (appointments.isEmpty()) {
            showMessage("No hay citas registradas.");
            return;
        }
        showAppointmentsInTable(appointments, "Todas las Citas");
    }

    private void searchAppointmentsByDoctor() {
        JTextField codeField = createInputField();
        Object[] message = {
                "Ingrese el código del doctor:", codeField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Buscar Citas por Código de Doctor",
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String doctorCode = codeField.getText();
            List<Appointment> foundAppointments = appointmentService.searchByDoctorCodeView(doctorCode);
            if (foundAppointments == null || foundAppointments.isEmpty()) {
                showMessage("No se encontraron citas para ese código de doctor.");
            } else {
                showAppointmentsInTable(foundAppointments, "Citas para el Doctor " + doctorCode);
            }
        } else {
            showMessage("Operación cancelada.");
        }
    }

    private void cancelAppointment() {
        JTextField dateField = createInputField();
        JTextField timeField = createInputField();
        JTextField duiField = createInputField();
        Object[] message = {
                "Fecha de la cita (dd-MM-yyyy):", dateField,
                "Hora de la cita (HH:mm):", timeField,
                "DUI del paciente:", duiField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Cancelar Cita", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (!DateUtils.isValidDateFormat(dateField.getText())) {
                showMessage("Fecha inválida. Formato: dd-MM-yyyy");
                return;
            }
            LocalDate cancelDate = DateUtils.parseDate(dateField.getText());
            LocalTime cancelTime;
            try {
                cancelTime = LocalTime.parse(timeField.getText());
            } catch (Exception e) {
                showMessage("Hora inválida. Formato: HH:mm");
                return;
            }
            String patientDui = duiField.getText();
            boolean cancelled = appointmentService.cancelAppointment(cancelDate, cancelTime, patientDui);
            if (!cancelled) {
                showMessage("Error al cancelar. No se encontró la cita.");
            } else {
                showMessage("Cita cancelada correctamente.");
            }
        } else {
            showMessage("Operación cancelada.");
        }
    }

    private void showAppointmentsInTable(List<Appointment> appointments, String title) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(36, 36, 36));
        dialog.setSize(700, 400);
        dialog.setLocationRelativeTo(this);
        String[] columnNames = {"Doctor", "Paciente", "Especialidad", "Fecha", "Hora"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Appointment ap : appointments) {
            Object[] rowData = {
                    ap.getDoctor().getName() + " " + ap.getDoctor().getLastName(),
                    ap.getPatient().getName() + " " + ap.getPatient().getLastName(),
                    ap.getSpecialty(),
                    ap.getDate().toString(),
                    ap.getTime().toString()
            };
            model.addRow(rowData);
        }
        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setBackground(new Color(60, 63, 65));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(100, 100, 100));
        table.setSelectionBackground(new Color(0, 200, 140));
        table.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBackground(new Color(25,25,25));
        headerRenderer.setForeground(Color.WHITE);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(36, 36, 36));
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
