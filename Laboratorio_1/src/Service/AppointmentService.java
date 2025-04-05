package Service;

import Model.Entity.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppointmentService {

    private final List<Appointment> appointments = new ArrayList<>();

    public boolean addAppointment(Appointment newAppointment) {
        for (Appointment existing : appointments) {
            LocalDate date = existing.getAppointmentDate();
            LocalTime time = existing.getAppointmentTime();
            if (date.equals(newAppointment.getAppointmentDate()) && time.equals(newAppointment.getAppointmentTime()) && existing.getDoctor().getCodigoDoctor().equals(newAppointment.getDoctor().getCodigoDoctor())) {
                System.out.println("El doctor ya cuenta con una cita a esa hora!!!");
                return false;
            }
            if (date.equals(newAppointment.getAppointmentDate()) && time.equals(newAppointment.getAppointmentTime()) && existing.getPatient().getDui().equals(newAppointment.getPatient().getDui())) {
                System.out.println("El paciente ya cuenta con una cita a esa hora!!!");
                return false;
            }
        }
        appointments.add(newAppointment);
        System.out.println("Cita registrada con el codigo: " + newAppointment.getCode());
        return true;
    }

    public void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No hay ninguna cita registrada todavia.");
            return;
        }
        System.out.println("---> Lista de todas las citas: <---");
        for (Appointment a : appointments) {
            System.out.println("----------------------------------");
            System.out.println("Dia de la cita: " + a.getAppointmentDate() + " hora " + a.getAppointmentTime());
            System.out.println("Doctor encargado \n" + a.getDoctor().getInfo());
            System.out.println("Paciente: \n" + a.getPatient().getInfo());
            System.out.println("\nFue atendido?: " + (a.isAttended() ? "Si" : "No"));
            System.out.println("Trajo galletas?: " + (a.isBroughtCookies() ? "Yes 🍪" : "No"));
        }
    }

    public void searchByDoctorCode(String doctorCode) {
        boolean exists = false;
        for (Appointment a : appointments) {
            if (a.getDoctor().getCodigoDoctor().equalsIgnoreCase(doctorCode)) {
                if (!exists) {
                    System.out.println("---> Listado de citas para el doctor con codigo: " + doctorCode + " <---");
                    exists = true;
                }
                System.out.println("Dia de la cita: " + a.getAppointmentDate() + " hora " + a.getAppointmentTime());
                System.out.println(a.getDoctor().getInfo());
                System.out.println(a.getPatient().getInfo());
                System.out.println("Ya fue antendido?: " + (a.isAttended() ? "Si" : "No"));
                System.out.println("Trajo galletas? : " + (a.isBroughtCookies() ? "Si 🍪" : "No"));
            }
        }
        if (!exists) {
            System.out.println("No hay citas registradas del doctor con codigo: " + doctorCode);
        }
    }

    public boolean cancelAppointment(LocalDate date, LocalTime time, String patientDui) {
        Iterator<Appointment> it = appointments.iterator();
        while (it.hasNext()) {
            Appointment a = it.next();
            if (a.getAppointmentDate().equals(date) && a.getAppointmentTime().equals(time) && a.getPatient().getDui().equals(patientDui)) {
                it.remove();
                System.out.println("La cita fue cancelada correctamente");
                return true;
            }
        }
        System.out.println("No se encontro la cita");
        return false;
    }
}