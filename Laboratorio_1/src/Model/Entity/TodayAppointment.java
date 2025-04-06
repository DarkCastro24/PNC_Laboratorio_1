package Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class TodayAppointment extends Appointment {
    private LocalTime appointmentTime;

    public TodayAppointment(Doctor doctor, Patient patient, String specialty, LocalDate appointmentDate, LocalTime appointmentTime, LocalDate date,LocalTime time) {
        super(doctor, patient, specialty, appointmentDate,date,time);
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