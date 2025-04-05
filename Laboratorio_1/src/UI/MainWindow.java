package UI;

import Model.DTO.DoctorDTO;
import Model.DTO.PatientDTO;
import Model.Entity.Speciality;
import Service.AppointmentService;
import Service.DoctorService;
import Service.PatientService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {

    private final DoctorService doctorService = new DoctorService();
    private final PatientService patientService = new PatientService();
    private final AppointmentService appointmentService = new AppointmentService();
    private final List<Speciality> specialitiesList = Speciality.loadSpecialties();

    public MainWindow() {
        setTitle("Zaun Hospital System");
        setSize(550, 770);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(36, 36, 36)); // Fondo general oscuro

        // Panel de encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 25, 25)); // Color de fondo oscuro
        JLabel titleLabel = new JLabel("Sistema de Hospital Zaun", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 3, 10, 10));
        buttonPanel.setBackground(new Color(36, 36, 36));

        // Crear botones
        JPanel row1 = new JPanel(new GridLayout(1, 3, 10, 10));
        row1.setBackground(new Color(36, 36, 36));
        JButton btnAgendarCita = createButton("New appointment");
        JButton btnVerCitas = createButton("Look appointment");
        JButton btnAgregarPaciente = createButton("Add patient");
        row1.add(btnAgendarCita);
        row1.add(btnVerCitas);
        row1.add(btnAgregarPaciente);

        JPanel row2 = new JPanel(new GridLayout(1, 3, 10, 10));
        row2.setBackground(new Color(36, 36, 36));
        JButton btnAgregarDoctor = createButton("Add doctor");
        JButton btnBuscarCitasDoctor = createButton("Search for code");
        JButton btnCancelarCita = createButton("Cancel appointment");
        row2.add(btnAgregarDoctor);
        row2.add(btnBuscarCitasDoctor);
        row2.add(btnCancelarCita);

        JPanel row3 = new JPanel(new GridLayout(1, 1, 10, 10)); // igual a las demás filas
        row3.setBackground(new Color(36, 36, 36));
        JButton btnDecorativo = createButton("Mundo saves lives!");
        row3.add(btnDecorativo);


        buttonPanel.add(row1);
        buttonPanel.add(row2);
        buttonPanel.add(row3);

        add(buttonPanel, BorderLayout.CENTER);

        // Botón de salir
        JButton btnSalir = createButton("Exit", new Color(200, 50, 50));
        btnSalir.setPreferredSize(new Dimension(500, 80));
        add(btnSalir, BorderLayout.SOUTH);

        // Listeners
        btnAgendarCita.addActionListener(e -> showMessage("Funcionalidad para agendar nueva cita próximamente."));
        btnVerCitas.addActionListener(e -> showMessage("Funcionalidad para ver todas las citas próximamente."));
        btnAgregarPaciente.addActionListener(e -> agregarPaciente());
        btnAgregarDoctor.addActionListener(e -> agregarDoctor());
        btnBuscarCitasDoctor.addActionListener(e -> showMessage("Funcionalidad para buscar citas por código próximamente."));
        btnCancelarCita.addActionListener(e -> showMessage("Funcionalidad para cancelar una cita próximamente."));
        btnDecorativo.addActionListener(e -> showMessage("¡MUNDO SALVA VIDAS!"));
        btnSalir.addActionListener(e -> System.exit(0));
    }

    // Botón con color personalizado
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        button.setPreferredSize(new Dimension(170, 60)); // Tamaño uniforme más compacto
        return button;
    }


    // Botón con color por defecto (verde)
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

    private void agregarPaciente() {
        JTextField nombreField = createInputField();
        JTextField apellidoField = createInputField();
        JTextField duiField = createInputField();
        JTextField cumpleField = createInputField();

        Object[] message = {
                "Nombre:", nombreField,
                "Apellido:", apellidoField,
                "DUI:", duiField,
                "Cumpleaños (dd-MM-yyyy):", cumpleField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Agregar Paciente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            patientService.agregarPaciente(new PatientDTO(
                    nombreField.getText(), apellidoField.getText(), duiField.getText(), cumpleField.getText()
            ));
            showMessage("Paciente agregado exitosamente.");
        } else {
            showMessage("Operación cancelada.");
        }
    }

    private void agregarDoctor() {
        JTextField nombreField = createInputField();
        JTextField apellidoField = createInputField();
        JTextField duiField = createInputField();
        JTextField cumpleField = createInputField();
        JTextField reclutamientoField = createInputField();
        JTextField especialidadField = createInputField();

        Object[] message = {
                "Nombre:", nombreField,
                "Apellido:", apellidoField,
                "DUI:", duiField,
                "Cumpleaños (dd-MM-yyyy):", cumpleField,
                "Reclutamiento (dd-MM-yyyy):", reclutamientoField,
                "Especialidad:", especialidadField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Agregar Doctor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            doctorService.agregarDoctor(new DoctorDTO(
                    nombreField.getText(), apellidoField.getText(), duiField.getText(), cumpleField.getText(),
                    reclutamientoField.getText(), especialidadField.getText()
            ));
            showMessage("Doctor agregado exitosamente.");
        } else {
            showMessage("Operación cancelada.");
        }
    }

    private JTextField createInputField() {
        JTextField field = new JTextField();
        field.setBackground(new Color(60, 63, 65));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        return field;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
