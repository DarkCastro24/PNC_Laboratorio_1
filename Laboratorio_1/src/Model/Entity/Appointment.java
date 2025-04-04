package Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Appointment {
    private Doctor doctor;
    private Patient patient;
    private Speciality specialty;
    private LocalDate appointmentDate;
    private boolean attended;
    private boolean broughtCookies;

    public Appointment(Doctor doctor, Patient patient, Speciality specialty, LocalDate appointmentDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.specialty = specialty;
        this.appointmentDate = appointmentDate;
        this.attended = false;
        this.broughtCookies = false;
    }

    public abstract LocalTime getAppointmentTime();

    public Doctor getDoctor() {
        return this.doctor;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Speciality getSpecialty() {
        return this.specialty;
    }

    public LocalDate getAppointmentDate() {
        return this.appointmentDate;
    }

    public boolean isAttended() {
        return this.attended;
    }

    public boolean isBroughtCookies() {
        return this.broughtCookies;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public void setBroughtCookies(boolean broughtCookies) {
        this.broughtCookies = broughtCookies;
    }
}