package Model.Entity;

import Model.DTO.PatientDTO;
import java.util.Calendar;

public class Patient extends Person {

    public Patient(PatientDTO dto) {
        super(dto.getName(), dto.getLastname(),
                calcularDUI(dto.getDui(), dto.getBirthday()),
                dto.getBirthday());
    }

    private static String calcularDUI(String dui, String cumpleaños) {
        int anioNacimiento = Integer.parseInt(cumpleaños.split("-")[2]);
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        return (anioActual - anioNacimiento < 18) ? "00000000-0" : dui;
    }

    public String getInfo() {
        return "Paciente: " + name + " " + lastName + ", DUI: " + dui + ", Cumpleaños: " + birthDate;
    }
}
