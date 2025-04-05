package Model.Entity;

import Model.DTO.PatientDTO;

import java.util.Calendar;

public class Patient {
    private String name;
    private String lastname;
    private String dui;
    private String birthday;

    public Patient(PatientDTO dto) {
        this.name = dto.getName();
        this.lastname = dto.getLastname();
        this.birthday = dto.getBirthday();
        this.dui = calcularDUI(dto.getDui(), dto.getBirthday());
    }

    private String calcularDUI(String dui, String cumpleaños) {
        int anioNacimiento = Integer.parseInt(cumpleaños.split("-")[2]);
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        return (anioActual - anioNacimiento < 18) ? "00000000-0" : dui;
    }

    public String getInfo() {
        return "Paciente: " + name + " " + lastname + ", DUI: " + dui + ", Cumpleaños: " + birthday;
    }
}