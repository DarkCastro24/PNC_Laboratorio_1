package Model.Entity;

import Model.DTO.PatientDTO;
import java.util.Calendar;

public class Patient {
    private String nombre;
    private String apellido;
    private String dui;
    private String cumpleaños;

    public Patient(PatientDTO dto) {
        this.nombre = dto.getNombre();
        this.apellido = dto.getApellido();
        this.cumpleaños = dto.getCumpleaños();
        this.dui = calcularDUI(dto.getDui(), dto.getCumpleaños());
    }

    private String calcularDUI(String dui, String cumpleanios) {
        int anioNacimiento = Integer.parseInt(cumpleanios.split("-")[2]);
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        return (anioActual - anioNacimiento < 18) ? "00000000-0" : dui;
    }

    public String getDui() {
        return dui;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getInfo() {
        return "Datos del paciente: " + nombre + " " + apellido + ", DUI: " + dui + ", Cumpleaños: " + cumpleaños;
    }
}