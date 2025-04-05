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
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 1, 10, 10)); // Organizar botones en una cuadrícula

        // Crear botones para cada opción del menú
        JButton btnAgendarCita = new JButton("1- Agendar nueva cita");
        JButton btnVerCitas = new JButton("2- Ver todas las citas");
        JButton btnAgregarPaciente = new JButton("3- Agregar nuevo paciente");
        JButton btnAgregarDoctor = new JButton("4- Agregar nuevo doctor");
        JButton btnBuscarCitasDoctor = new JButton("5- Buscar citas por código de doctor");
        JButton btnCancelarCita = new JButton("6- Cancelar una cita");
        JButton btnDecorativo = new JButton("7- ¡Mundo salva vidas!");
        JButton btnSalir = new JButton("0- Salir");

        // Agregar ActionListeners a los botones
        btnAgendarCita.addActionListener(
                e -> JOptionPane.showMessageDialog(this, "Funcionalidad para agendar nueva cita próximamente."));
        btnVerCitas.addActionListener(
                e -> JOptionPane.showMessageDialog(this, "Funcionalidad para ver todas las citas próximamente."));
        btnAgregarPaciente.addActionListener(e -> agregarPaciente());
        btnAgregarDoctor.addActionListener(e -> agregarDoctor());
        btnBuscarCitasDoctor.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Funcionalidad para buscar citas por código de doctor próximamente."));
        btnCancelarCita.addActionListener(
                e -> JOptionPane.showMessageDialog(this, "Funcionalidad para cancelar una cita próximamente."));
        btnDecorativo.addActionListener(e -> JOptionPane.showMessageDialog(this, "¡MUNDO SALVA VIDAS!"));
        btnSalir.addActionListener(e -> System.exit(0));

        // Agregar botones a la ventana
        add(btnAgendarCita);
        add(btnVerCitas);
        add(btnAgregarPaciente);
        add(btnAgregarDoctor);
        add(btnBuscarCitasDoctor);
        add(btnCancelarCita);
        add(btnDecorativo);
        add(btnSalir);
    }

    private void agregarPaciente() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre del paciente:");
        String apellido = JOptionPane.showInputDialog(this, "Ingrese apellido del paciente:");
        String dui = JOptionPane.showInputDialog(this, "Ingrese DUI del paciente:");
        String cumpleaños = JOptionPane.showInputDialog(this, "Ingrese cumpleaños del paciente (dd-MM-yyyy):");

        if (nombre != null && apellido != null && dui != null && cumpleaños != null) {
            patientService.agregarPaciente(new PatientDTO(nombre, apellido, dui, cumpleaños));
            JOptionPane.showMessageDialog(this, "Paciente agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
        }
    }

    private void agregarDoctor() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre del doctor:");
        String apellido = JOptionPane.showInputDialog(this, "Ingrese apellido del doctor:");
        String dui = JOptionPane.showInputDialog(this, "Ingrese DUI del doctor:");
        String cumpleaños = JOptionPane.showInputDialog(this, "Ingrese cumpleaños del doctor (dd-MM-yyyy):");
        String fechaReclutamiento = JOptionPane.showInputDialog(this, "Ingrese fecha de reclutamiento (dd-MM-yyyy):");
        String especialidad = JOptionPane.showInputDialog(this, "Ingrese especialidad del doctor:");

        if (nombre != null && apellido != null && dui != null && cumpleaños != null && fechaReclutamiento != null
                && especialidad != null) {
            doctorService
                    .agregarDoctor(new DoctorDTO(nombre, apellido, dui, cumpleaños, fechaReclutamiento, especialidad));
            JOptionPane.showMessageDialog(this, "Doctor agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}