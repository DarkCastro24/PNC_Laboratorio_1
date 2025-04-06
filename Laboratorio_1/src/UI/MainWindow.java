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
        setSize(550,770);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(36,36,36));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25,25,25));
        JLabel titleLabel = new JLabel("Sistema de Hospital Zaun",SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI",Font.BOLD,36));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel,BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,3,10,10));
        buttonPanel.setBackground(new Color(36,36,36));

        JPanel row1 = new JPanel(new GridLayout(1,3,10,10));
        row1.setBackground(new Color(36,36,36));
        JButton btnScheduleAppointment = createButton("New appointment");
        JButton btnViewAppointments = createButton("Look appointments");
        JButton btnAddPatient = createButton("Add patient");
        row1.add(btnScheduleAppointment);
        row1.add(btnViewAppointments);
        row1.add(btnAddPatient);

        JPanel row2 = new JPanel(new GridLayout(1,3,10,10));
        row2.setBackground(new Color(36,36,36));
        JButton btnAddDoctor = createButton("Add doctor");
        JButton btnSearchAppointmentsByCode = createButton("Search for code");
        JButton btnCancelAppointment = createButton("Cancel appointment");
        row2.add(btnAddDoctor);
        row2.add(btnSearchAppointmentsByCode);
        row2.add(btnCancelAppointment);

        JPanel row3 = new JPanel(new GridLayout(1,1,10,10));
        row3.setBackground(new Color(36,36,36));
        JButton btnListDoctors = createButton("View doctors");
        row3.add(btnListDoctors);
        JButton btnDecorative = createButton("Mundo saves lives!");
        row3.add(btnDecorative);

        buttonPanel.add(row1);
        buttonPanel.add(row2);
        buttonPanel.add(row3);
        add(buttonPanel,BorderLayout.CENTER);

        JButton btnExit = createButton("Exit",new Color(200,50,50));
        btnExit.setPreferredSize(new Dimension(500,80));
        add(btnExit,BorderLayout.SOUTH);

        btnScheduleAppointment.addActionListener(e->scheduleAppointment());
        btnViewAppointments.addActionListener(e->viewAppointments());
        btnAddPatient.addActionListener(e->addPatient());
        btnAddDoctor.addActionListener(e->addDoctor());
        btnSearchAppointmentsByCode.addActionListener(e->searchAppointmentsByDoctor());
        btnCancelAppointment.addActionListener(e->cancelAppointment());
        btnListDoctors.addActionListener(e->showDoctorsTable());
        btnDecorative.addActionListener(e->showMessage("¡MUNDO SALVA VIDAS!"));
        btnExit.addActionListener(e->System.exit(0));
    }

    private void showDoctorsTable() {
        List<Doctor> doctors = doctorService.getDoctors();
        if(doctors.isEmpty()) {
            showMessage("No hay doctores registrados.");
            return;
        }
        JDialog dialog = new JDialog(this,"Lista de Doctores",true);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(36,36,36));
        dialog.setSize(600,400);
        dialog.setLocationRelativeTo(this);

        String[] columnNames = {"Código","Nombre","Apellido","DUI"};
        DefaultTableModel model = new DefaultTableModel(columnNames,0) {
            @Override public boolean isCellEditable(int row,int column){return false;}
        };
        for(Doctor doc:doctors) {
            Object[] row = {
                    doc.getCodeDoctor(),
                    doc.getName(),
                    doc.getLastName(),
                    doc.getDui()
            };
            model.addRow(row);
        }
        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setBackground(new Color(60,63,65));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(100,100,100));
        table.setSelectionBackground(new Color(0,200,140));
        table.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBackground(new Color(25,25,25));
        headerRenderer.setForeground(Color.WHITE);

        for(int i=0;i<table.getColumnCount();i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            table.getColumnModel().getColumn(i).setCellRenderer(headerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(36,36,36));
        dialog.add(scrollPane,BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    public List<Doctor> getAllDoctors() {
        return doctorService.getDoctors();
    }

    private JButton createButton(String text,Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI",Font.PLAIN,16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
        button.setPreferredSize(new Dimension(170,60));
        return button;
    }

    private JButton createButton(String text) {
        return createButton(text,new Color(0,200,140));
    }

    private void showMessage(String message) {
        UIManager.put("OptionPane.background",new Color(45,45,45));
        UIManager.put("Panel.background",new Color(45,45,45));
        UIManager.put("OptionPane.messageForeground",Color.WHITE);
        UIManager.put("Button.background",new Color(0,200,140));
        UIManager.put("Button.foreground",Color.WHITE);
        JOptionPane.showMessageDialog(this,message);
    }

    private JTextField createInputField() {
        JTextField field = new JTextField();
        field.setBackground(new Color(60,63,65));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));
        return field;
    }

    private void addPatient() {
        JTextField fn = createInputField();
        JTextField ln = createInputField();
        JTextField df = createInputField();
        JTextField bf = createInputField();

        Object[] message = {
                "Nombre:",fn,
                "Apellido:",ln,
                "DUI (Menores dejar vacio):",df,
                "Cumpleaños (dd-MM-yyyy):",bf
        };
        int option = JOptionPane.showConfirmDialog(this,message,"Agregar Paciente",JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION) {
            if(!DateUtils.isValidDateFormat(bf.getText())) {
                showMessage("La fecha ingresada es inválida, use el formato dd-MM-yyyy.");
                return;
            }
            PatientDTO newPatient = new PatientDTO(fn.getText(),ln.getText(),df.getText(),bf.getText());
            patientService.addPatient(newPatient);
            showMessage("Paciente agregado exitosamente.");
        } else {
            showMessage("Operación cancelada.");
        }
    }

    private void addDoctor() {
        JTextField fn = createInputField();
        JTextField ln = createInputField();
        JTextField df = createInputField();
        JTextField bf = createInputField();
        JTextField rf = createInputField();

        String[] arr = new String[specialtiesList.size()];
        for(int i=0;i<specialtiesList.size();i++) {
            arr[i] = specialtiesList.get(i).getName();
        }
        JComboBox<String> sc = new JComboBox<>(arr);
        sc.setBackground(new Color(60,63,65));
        sc.setForeground(Color.WHITE);

        Object[] message = {
                "Nombre:",fn,
                "Apellido:",ln,
                "DUI:",df,
                "Cumpleaños (dd-MM-yyyy):",bf,
                "Reclutamiento (dd-MM-yyyy):",rf,
                "Especialidad:",sc
        };
        int option = JOptionPane.showConfirmDialog(this,message,"Agregar Doctor",JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION) {
            if(!DateUtils.isValidDateFormat(bf.getText())) {
                showMessage("La fecha de cumpleaños es inválida. Formato: dd-MM-yyyy");
                return;
            }
            if(!DateUtils.isValidDateFormat(rf.getText())) {
                showMessage("La fecha de reclutamiento es inválida. Formato: dd-MM-yyyy");
                return;
            }
            DoctorDTO newDoctor = new DoctorDTO(
                    fn.getText(),
                    ln.getText(),
                    df.getText(),
                    bf.getText(),
                    rf.getText(),
                    (String)sc.getSelectedItem()
            );
            String code = doctorService.addDoctor(newDoctor);
            if(code!=null && !code.isEmpty()) {
                showMessage("Doctor agregado exitosamente, el codigo de doctor asignado es: "+code);
            } else {
                showMessage("No se pudo obtener el codigo del doctor.");
            }
        } else {
            showMessage("Operacion cancelada.");
        }
    }

    private void scheduleAppointment() {
        JTextField df = createInputField();
        JTextField dcf = createInputField();
        JTextField da = createInputField();
        JTextField tf = createInputField();

        Object[] message = {
                "DUI del paciente:",df,
                "Codigo del doctor:",dcf,
                "Fecha de la cita (dd-MM-yyyy):",da,
                "Hora de la cita (HH:mm):",tf
        };
        int option = JOptionPane.showConfirmDialog(this,message,"Agendar Nueva Cita",JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION) {
            Patient p = patientService.getPatientByDui(df.getText());
            if(p==null) {
                showMessage("El paciente no fue encontrado, debes registrarlo primero.");
                return;
            }
            Doctor d = doctorService.getDoctorByCodigo(dcf.getText());
            if(d==null) {
                showMessage("El doctor no fue encontrado. Verifique el codigo o registre al doctor.");
                return;
            }
            if(!DateUtils.isValidDateFormat(da.getText())) {
                showMessage("La fecha ingresada no es valida, el formato correcto es: dd-MM-yyyy");
                return;
            }
            LocalDate ad = DateUtils.parseDate(da.getText());
            if(ad.isBefore(LocalDate.now())) {
                showMessage("No se puede agendar una cita en el pasado.");
                return;
            }
            LocalTime at;
            try {
                at = LocalTime.parse(tf.getText());
            } catch(Exception e) {
                showMessage("La hora no es valida, el formato correcto es: HH:mm.");
                return;
            }
            Appointment na;
            if(ad.equals(LocalDate.now())) {
                na = new TodayAppointment(
                        d,p,d.getSpecialty(),ad,at,ad,at
                );
            } else {
                na = new FutureAppointment(
                        d,p,d.getSpecialty(),ad,at,ad,at
                );
            }
            boolean added = appointmentService.addAppointment(na);
            if(!added) {
                showMessage("No se pudo agendar la cita debido a conflicto de horarios.");
            } else {
                showMessage("Cita agendada exitosamente.");
            }
        } else {
            showMessage("Operacion cancelada.");
        }
    }

    private void viewAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        if(appointments.isEmpty()) {
            showMessage("No hay citas registradas.");
            return;
        }
        showAppointmentsInTable(appointments,"Todas las citas");
    }

    private void searchAppointmentsByDoctor() {
        JTextField cf = createInputField();
        Object[] message = {
                "Ingrese el código del doctor:",cf
        };
        int option = JOptionPane.showConfirmDialog(this,message,"Buscar citas por codigo de doctor",JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION) {
            String doctorCode = cf.getText();
            System.out.println(doctorCode);
            List<Appointment> foundAppointments = appointmentService.searchByDoctorCodeView(doctorCode);
            if(foundAppointments==null || foundAppointments.isEmpty()) {
                showMessage("No se encontraron citas para ese código de doctor.");
            } else {
                showAppointmentsInTable(foundAppointments,"Citas para el Doctor "+doctorCode);
            }
        } else {
            showMessage("Operación cancelada.");
        }
    }

    private void cancelAppointment() {
        JTextField da = createInputField();
        JTextField tf = createInputField();
        JTextField df = createInputField();

        Object[] message = {
                "Fecha de la cita (dd-MM-yyyy):",da,
                "Hora de la cita (HH:mm):",tf,
                "DUI del paciente:",df
        };
        int option = JOptionPane.showConfirmDialog(this,message,"Cancelar Cita",JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION) {
            if(!DateUtils.isValidDateFormat(da.getText())) {
                showMessage("Fecha inválida. Formato: dd-MM-yyyy");
                return;
            }
            LocalDate cd = DateUtils.parseDate(da.getText());
            LocalTime ct;
            try {
                ct = LocalTime.parse(tf.getText());
            } catch(Exception ex) {
                showMessage("Hora inválida. Formato: HH:mm");
                return;
            }
            String pd = df.getText();
            boolean cancelled = appointmentService.cancelAppointment(cd,ct,pd);
            if(!cancelled) {
                showMessage("Error al cancelar. No se encontró la cita.");
            } else {
                showMessage("Cita cancelada correctamente.");
            }
        } else {
            showMessage("Operación cancelada.");
        }
    }

    private void showAppointmentsInTable(List<Appointment> appointments,String title) {
        JDialog dialog = new JDialog(this,title,true);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(36,36,36));
        dialog.setSize(700,400);
        dialog.setLocationRelativeTo(this);

        String[] columnNames = {
                "Doctor",
                "Paciente",
                "DUI Paciente",
                "Especialidad",
                "Fecha",
                "Hora",
                "Trajo galletas?"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames,0) {
            @Override public boolean isCellEditable(int row,int column){return false;}
        };
        for(Appointment ap:appointments) {
            String dn = ap.getDoctor()==null ? "" : (ap.getDoctor().getName()+" "+ap.getDoctor().getLastName());
            String pn = ap.getPatient()==null ? "" : (ap.getPatient().getName()+" "+ap.getPatient().getLastName());
            String dui = ap.getPatient()==null ? "" : ap.getPatient().getDui();
            String sp = ap.getSpecialty()==null ? "" : ap.getSpecialty();
            String dd = ap.getDate()==null ? "" : ap.getDate().toString();
            String tt = ap.getTime()==null ? "" : ap.getTime().toString();
            model.addRow(new Object[]{
                    dn,pn,dui,sp,dd,tt,"Si 🍪"
            });
        }
        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setBackground(new Color(60,63,65));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(100,100,100));
        table.setSelectionBackground(new Color(0,200,140));
        table.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBackground(new Color(25,25,25));
        headerRenderer.setForeground(Color.WHITE);

        for(int i=0;i<table.getColumnCount();i++){
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i=0;i<table.getColumnCount();i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(36,36,36));
        dialog.add(scrollPane,BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
