import Model.DTO.DoctorDTO;
import Model.DTO.PatientDTO;
import Model.Entity.*;
import Service.AppointmentService;
import Service.DoctorService;
import Service.PatientService;
import Utils.DateUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();
        List<Speciality> specialitiesList = Speciality.loadSpecialties();
        int option;

        do {
            System.out.println("---> Bienvenido al Sistema de Citas Médicas del Hospital Nacional de Zaun <---");
            System.out.println("1- Agregar nuevo paciente");
            System.out.println("2- Agregar nuevo doctor");
            System.out.println("3- Agendar nueva cita");
            System.out.println("4- Ver todas las citas");
            System.out.println("5- Buscar citas por codigo de doctor");
            System.out.println("6- Cancelar una cita");
            System.out.println("7- Boton decorativo: '¡Mundo salva vidas!'");
            System.out.println("0- Salir");
            System.out.print("Selecciona una opcion: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1:
                    System.out.println("Ingrese nombre del paciente: ");
                    String nombrePaciente = scanner.nextLine();
                    System.out.println("Ingrese apellido del paciente: ");
                    String apellidoPaciente = scanner.nextLine();
                    System.out.println("Ingrese DUI del paciente: ");
                    String duiPaciente = scanner.nextLine();
                    System.out.println("Ingrese cumpleaños del paciente en formato (dd-MM-yyyy): ");
                    String cumpleaniosPaciente = scanner.nextLine();
                    if (!DateUtils.isValidDateFormat(cumpleaniosPaciente)) {
                        System.out.println("La fecha ingresada es inválida, ocupe el formato dd-MM-yyyy.");
                        break;
                    }
                    PatientDTO nuevoPaciente = new PatientDTO(
                            nombrePaciente,
                            apellidoPaciente,
                            duiPaciente,
                            cumpleaniosPaciente
                    );
                    patientService.agregarPaciente(nuevoPaciente);
                    break;

                case 2:
                    System.out.println("Ingrese nombre del doctor: ");
                    String nombreDoctor = scanner.nextLine();
                    System.out.println("Ingrese apellido del doctor: ");
                    String apellidoDoctor = scanner.nextLine();
                    System.out.println("Ingrese DUI del doctor: ");
                    String duiDoctor = scanner.nextLine();
                    System.out.println("Ingrese cumpleaños del doctor en formato (dd-MM-yyyy): ");
                    String cumpleaniosDoctor = scanner.nextLine();

                    if (!DateUtils.isValidDateFormat(cumpleaniosDoctor)) {
                        System.out.println("La fecha ingresada no es valida ocupa el formato: dd-MM-yyyy");
                        break;
                    }

                    System.out.println("Ingrese fecha de reclutamiento en formato (dd-MM-yyyy): ");
                    String fechaReclutamientoDoctor = scanner.nextLine();

                    if (!DateUtils.isValidDateFormat(fechaReclutamientoDoctor)) {
                        System.out.println("La fecha ingresada no es valida ocupa el formato: dd-MM-yyyy");
                        break;
                    }

                    System.out.println("Seleccione la especialidad del doctor:");
                    for (int i = 0; i < specialitiesList.size(); i++) {
                        System.out.println((i + 1) + ". " + specialitiesList.get(i).getName());
                    }

                    System.out.print("Ingrese el número correspondiente a la especialidad: ");
                    int seleccion = scanner.nextInt();
                    scanner.nextLine();

                    if (seleccion < 1 || seleccion > specialitiesList.size()) {
                        System.out.println("La opcion seleccionada no es valida.");
                        break;
                    }

                    String especialidadDoctor = specialitiesList.get(seleccion - 1).getName();
                    DoctorDTO nuevoDoctor = new DoctorDTO(
                            nombreDoctor,
                            apellidoDoctor,
                            duiDoctor,
                            cumpleaniosDoctor,
                            fechaReclutamientoDoctor,
                            especialidadDoctor
                    );
                    doctorService.agregarDoctor(nuevoDoctor);
                    break;

                case 3:
                    System.out.print("Ingrese DUI del paciente: ");
                    String duiCita = scanner.nextLine();
                    Patient patient = patientService.getPatientByDui(duiCita);
                    if (patient == null) {
                        System.out.println("El paciente no fue encontrado, asegurate que el paciente este registrado.");
                        break;
                    }
                    System.out.print("Ingrese el codigo del doctor: ");
                    String codigoDoctor = scanner.nextLine();
                    Doctor doctor = doctorService.getDoctorByCodigo(codigoDoctor);

                    if (doctor == null) {
                        System.out.println("El doctor no fue encontrado, asegurate que el doctor este registrado.");
                        break;
                    } else {
                        System.out.println("Doctor encontrado: " + doctor.getName() + " " + doctor.getLastName());
                    }

                    System.out.print("Ingrese fecha de la cita a cancelar en formato dd-MM-yyyy: ");
                    String fechaCita = scanner.nextLine();

                    if (!DateUtils.isValidDateFormat(fechaCita)) {
                        System.out.println("La fecha ingresada no es valida ocupa el formato: dd-MM-yyyy");
                        break;
                    }

                    LocalDate appointmentDate = DateUtils.parseDate(fechaCita);

                    if (appointmentDate.isBefore(LocalDate.now())) {
                        System.out.println("No puedes agendar una cita en el pasado.");
                        break;
                    }
                    System.out.print("Ingrese hora de la cita en el formato (HH:mm): ");
                    String horaCita = scanner.nextLine();
                    LocalTime appointmentTime;
                    try {
                        appointmentTime = LocalTime.parse(horaCita);
                    } catch (Exception e) {
                        System.out.println("La hora no es valida ocupe el formato: HH:mm");
                        break;
                    }
                    Appointment newAppointment;
                    if (appointmentDate.equals(LocalDate.now())) {
                        newAppointment = new TodayAppointment(doctor, patient, doctor.getSpecialty(), appointmentDate, appointmentTime);
                    } else {
                        newAppointment = new FutureAppointment(doctor, patient, doctor.getSpecialty(), appointmentDate, appointmentTime);
                    }
                    if (!appointmentService.addAppointment(newAppointment)) {
                        System.out.println("No se pudo agendar la cita debido a conflicto de horarios.");
                    }
                    break;

                case 4:
                    appointmentService.listAppointments();
                    break;

                case 5:
                    System.out.print("Ingrese el codigo del doctor: ");
                    String doctorCode = scanner.nextLine();
                    appointmentService.searchByDoctorCode(doctorCode);
                    break;

                case 6:
                    System.out.print("Ingrese fecha de la cita a cancelar en formato dd-MM-yyyy: ");
                    String fechaCancelar = scanner.nextLine();
                    if (!DateUtils.isValidDateFormat(fechaCancelar)) {
                        System.out.println("Fecha invalida, el formato correcto es: dd-MM-yyyy");
                        break;
                    }
                    LocalDate cancelDate = DateUtils.parseDate(fechaCancelar);
                    System.out.print("Ingrese hora de la cita a cancelar (HH:mm): ");
                    String horaCancelar = scanner.nextLine();
                    LocalTime cancelTime;
                    try {
                        cancelTime = LocalTime.parse(horaCancelar);
                    } catch (Exception e) {
                        System.out.println("Hora invalida, ingresa el formato correcto: HH:mm");
                        break;
                    }
                    System.out.print("Ingrese DUI del paciente: ");
                    String duiCancelar = scanner.nextLine();
                    boolean cancelled = appointmentService.cancelAppointment(cancelDate, cancelTime, duiCancelar);
                    if (!cancelled) {
                        System.out.println("Error al cancelar, la cita fue encontrada.");
                    }
                    break;

                case 7:
                    System.out.println("✨ ¡MUNDO SALVA VIDAS! ✨");
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("La opcion seleccionada no es valida, intenta nuevamente.");
                    break;
            }
        } while (option != 0);
        scanner.close();
    }
}
