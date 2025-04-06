package Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Appointment {
    private Doctor doctor;
    private Patient patient;
    private String specialty;
    private LocalDate appointmentDate;
    private boolean attended;
    private boolean broughtCookies;
    private LocalDate date;
    private LocalTime time;

    public Appointment(Doctor doctor, Patient patient, String specialty, LocalDate appointmentDate, LocalDate date, LocalTime time) {
        this.doctor = doctor;
        this.patient = patient;
        this.specialty = specialty;
        this.appointmentDate = appointmentDate;
        this.attended = false;
        this.broughtCookies = false;
        this.date = date;
        this.time = time;
    }


    public abstract LocalTime getAppointmentTime();

    public void setDate(LocalDate date) { this.date = date; }

    public void setTime(LocalTime time) { this.time = time; }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public String getSpecialty() { return this.specialty; }

    public LocalDate getDate() { return date; }

    public LocalTime getTime() { return time; }

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