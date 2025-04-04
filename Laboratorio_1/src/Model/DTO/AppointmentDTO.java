package Model.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
    private String doctorName;
    private String patientName;
    private String specialty;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private boolean attended;
    private boolean broughtCookies;

    public AppointmentDTO(String doctorName, String patientName, String specialty, LocalDate appointmentDate, LocalTime appointmentTime,boolean attended, boolean broughtCookies) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.specialty = specialty;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.attended = attended;
        this.broughtCookies = broughtCookies;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public boolean isAttended() {
        return attended;
    }

    public boolean isBroughtCookies() {
        return broughtCookies;
    }
}