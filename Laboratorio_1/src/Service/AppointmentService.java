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
        for(Appointment existing : appointments) {
            LocalDate date = existing.getAppointmentDate();
            LocalTime time = existing.getAppointmentTime();
            newAppointment.setDate(date);
            newAppointment.setTime(time);
            if(date.equals(newAppointment.getAppointmentDate())
                    && time.equals(newAppointment.getAppointmentTime())
                    && existing.getDoctor().getCodeDoctor().equals(newAppointment.getDoctor().getCodeDoctor())) {
                System.out.println("El doctor ya cuenta con una cita a esa hora!!!");
                return false;
            }
            if(date.equals(newAppointment.getAppointmentDate())
                    && time.equals(newAppointment.getAppointmentTime())
                    && existing.getPatient().getDui().equals(newAppointment.getPatient().getDui())) {
                System.out.println("El paciente ya cuenta con una cita a esa hora!!!");
                return false;
            }
        }
        appointments.add(newAppointment);
        System.out.println("Cita registrada para el doctor: " + newAppointment.getDoctor().getCodeDoctor());
        return true;
    }

    public void listAppointments() {
        if(appointments.isEmpty()) {
            System.out.println("No hay ninguna cita registrada todavia.");
            return;
        }
        System.out.println("---> Lista de todas las citas: <---");
        for(Appointment a : appointments) {
            System.out.println("----------------------------------");
            System.out.println("Dia de la cita: " + a.getAppointmentDate() + " hora " + a.getAppointmentTime());
            System.out.println("Doctor encargado \n" + a.getDoctor().getInfo());
            System.out.println("Paciente: \n" + a.getPatient().getInfo());
            System.out.println("Trajo galletas?: " + (!a.isBroughtCookies() ? "Yes 🍪" : "No"));
        }
    }

    public List<Appointment> searchByDoctorCodeView(String doctorCode) {
        List<Appointment> found = new ArrayList<>();
        for(Appointment a : appointments) {
            System.out.println(a.toString());
            if(a.getDoctor().getCodeDoctor().equals(doctorCode)) {
                found.add(a);
                if(found.size()==1) {
                    System.out.println("---> Listado de citas para el doctor con codigo: " + doctorCode + " <---");
                }
                System.out.println("Dia de la cita: " + a.getAppointmentDate() + " hora " + a.getAppointmentTime());
                System.out.println(a.getDoctor().getInfo());
                System.out.println(a.getPatient().getInfo());
                System.out.println("Trajo galletas? : " + (!a.isBroughtCookies() ? "Si 🍪" : "No"));
            }
        }
        if(found.isEmpty()) {
            System.out.println("No hay citas registradas del doctor con codigo: " + doctorCode);
            return found;
        }
        return found;
    }

    public void searchByDoctorCode(String doctorCode) {
        boolean exists = false;
        for(Appointment a : appointments) {
            if(a.getDoctor().getCodeDoctor().equalsIgnoreCase(doctorCode)) {
                if(!exists) {
                    System.out.println("---> Listado de citas para el doctor con codigo: " + doctorCode + " <---");
                    exists=true;
                }
                System.out.println("Dia de la cita: " + a.getAppointmentDate() + " hora " + a.getAppointmentTime());
                System.out.println(a.getDoctor().getInfo());
                System.out.println(a.getPatient().getInfo());
                System.out.println("Trajo galletas? : " + (!a.isBroughtCookies() ? "Si 🍪" : "No"));
            }
        }
        if(!exists) {
            System.out.println("No hay citas registradas del doctor con codigo: " + doctorCode);
        }
    }

    public boolean cancelAppointment(LocalDate date,LocalTime time,String patientDui) {
        Iterator<Appointment> it = appointments.iterator();
        while(it.hasNext()) {
            Appointment a = it.next();
            if(a.getAppointmentDate().equals(date)
                    && a.getAppointmentTime().equals(time)
                    && a.getPatient().getDui().equals(patientDui)) {
                it.remove();
                System.out.println("La cita fue cancelada correctamente");
                return true;
            }
        }
        System.out.println("No se encontro la cita");
        return false;
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }
}
