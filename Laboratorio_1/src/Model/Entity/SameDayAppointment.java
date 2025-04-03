package Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class SameDayAppointment extends Appointment {
    private LocalTime appointmentTime;

    public SameDayAppointment(Doctor doctor, Patient patient, Speciality specialty, LocalDate appointmentDate, LocalTime appointmentTime) {
        super(doctor, patient, specialty, appointmentDate);
        this.appointmentTime = appointmentTime;
    }

    @Override
    public LocalTime getAppointmentTime() {
        return this.appointmentTime;
    }

    @Override
    public String toString() {
        return "Cita de hoy - " + getAppointmentDate().toString() + " " + appointmentTime.toString() + " - " + getSpecialty().toString() + " | " + getDoctor().toString() + " con " + getPatient().toString();
    }
}