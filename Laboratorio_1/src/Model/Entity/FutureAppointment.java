package Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class FutureAppointment extends Appointment {
    private LocalTime assignedTime;

    public FutureAppointment(Doctor doctor, Patient patient, String specialty, LocalDate appointmentDate, LocalTime assignedTime) {
        super(doctor, patient, specialty, appointmentDate);
        this.assignedTime = assignedTime;
    }

    @Override
    public LocalTime getAppointmentTime() {
        return this.assignedTime;
    }

    @Override
    public String toString() {
        return "Cita futura - " + getAppointmentDate().toString() + " " + assignedTime.toString() + " - " + getSpecialty().toString() + " | " + getDoctor().toString() + " con " + getPatient().toString();
    }
}
