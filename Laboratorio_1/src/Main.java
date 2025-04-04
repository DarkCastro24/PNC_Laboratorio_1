import Model.Entity.Speciality;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;

        List<Speciality> specialitiesList = Speciality.loadSpecialties();

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

            option = sc.nextInt();
            sc.nextLine();

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
                    break;

                case 4:
                    // TODO: Solicitar datos del doctor campos: nombre, apellido, DUI, nacimiento, contratación, especialidad
                    // Generar codigo con el formato ZNH-XAX-MD-AX
                    // Guardar doctor
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
    }
}
