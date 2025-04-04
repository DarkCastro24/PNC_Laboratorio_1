import Model.DTO.DoctorDTO;
import Model.DTO.PatientDTO;
import Model.Entity.Speciality;
import Service.AppointmentService;
import Service.DoctorService;
import Service.PatientService;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();
        List<Speciality> specialitiesList = Speciality.loadSpecialties();
        int option;

        do {
            System.out.println("---> Bienvenido al Sistema de Citas Medicas del Hospital Nacional de Zaun <---");
            System.out.println("1- Agendar nueva cita");
            System.out.println("2- Ver todas las citas");
            System.out.println("3- Agregar nuevo paciente");
            System.out.println("4- Agregar nuevo doctor");
            System.out.println("5- Buscar citas por código de doctor");
            System.out.println("6- Cancelar una cita");
            System.out.println("7- Botón decorativo: '¡Mundo salva vidas!'");
            System.out.println("0- Salir");
            System.out.print("Selecciona una opcion: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    // TODO: Mostrar doctores, pacientes y especialidades disponibles
                    // Preguntar si es una cita del día o futura
                    // SI es del día:
                    // Solicitar hora exacta
                    // Crear objeto TodayAppointment
                    // SI es futura:
                    // Solicitar fecha (día)
                    // Generar hora aleatoria entre 8:00 y 16:00
                    // Crear objeto FutureAppointment
                    // Validar disponibilidad
                    // Guardar cita
                    break;


                case 2:
                    // TODO: Mostrar todas las citas y como opcion filtrar por fecha
                    break;

                case 3:
                    // TODO: Solicitar datos del paciente campos: nombre, apellido, DUI, nacimiento
                    // Si es menor de edad, asignar DUI "00000000-0"
                    // Guardar paciente
                    System.out.println("Ingrese nombre del paciente: ");
                    String nombrePaciente = scanner.nextLine();
                    System.out.println("Ingrese apellido del paciente: ");
                    String apellidoPaciente = scanner.nextLine();
                    System.out.println("Ingrese DUI del paciente: ");
                    String duiPaciente = scanner.nextLine();
                    System.out.println("Ingrese cumpleaños del paciente (dd-MM-yyyy): ");
                    String cumpleañosPaciente = scanner.nextLine();
                    patientService.agregarPaciente(new PatientDTO(nombrePaciente, apellidoPaciente, duiPaciente, cumpleañosPaciente));
                    break;

                case 4:
                    // TODO: Solicitar datos del doctor campos: nombre, apellido, DUI, nacimiento, contratación, especialidad
                    // Generar codigo con el formato ZNH-XAX-MD-AX
                    // Guardar doctor
                    System.out.println("Ingrese nombre del doctor: ");
                    String nombreDoctor = scanner.nextLine();
                    System.out.println("Ingrese apellido del doctor: ");
                    String apellidoDoctor = scanner.nextLine();
                    System.out.println("Ingrese DUI del doctor: ");
                    String duiDoctor = scanner.nextLine();
                    System.out.println("Ingrese cumpleaños del doctor (dd-MM-yyyy): ");
                    String cumpleañosDoctor = scanner.nextLine();
                    System.out.println("Ingrese fecha de reclutamiento (dd-MM-yyyy): ");
                    String fechaReclutamientoDoctor = scanner.nextLine();
                    System.out.println("Ingrese especialidad: ");
                    String especialidadDoctor = scanner.nextLine();
                    doctorService.agregarDoctor(new DoctorDTO(nombreDoctor, apellidoDoctor, duiDoctor, cumpleañosDoctor, fechaReclutamientoDoctor, especialidadDoctor));
                    break;

                case 5:
                    // TODO: Solicitar codigo del doctor
                    // Mostrar todas las citas del doctor
                    break;

                case 6:
                    // TODO: Solicitar datos o ID de la cita
                    // Mostrar mensaje manrcandola como cancelada
                    break;

                case 7:
                    System.out.println(" ¡MUNDO SALVA VIDAS! ");
                    break;

                case 0:
                    System.out.println("Saliendo del programa....");
                    break;

                default:
                    System.out.println("La opcion seleccionada no es valida, intenta nuevamente.");
                    break;
            }

        } while (option != 0);

        scanner.close();
    }
}
