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

    private String calcularDUI(String dui, String cumpleaños) {
        int anioNacimiento = Integer.parseInt(cumpleaños.split("-")[2]);
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        return (anioActual - anioNacimiento < 18) ? "00000000-0" : dui;
    }

    public String getInfo() {
        return "Paciente: " + nombre + " " + apellido + ", DUI: " + dui + ", Cumpleaños: " + cumpleaños;
    }
}