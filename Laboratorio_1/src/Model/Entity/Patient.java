package Model.Entity;

import Model.DTO.PatientDTO;
import java.util.Calendar;

public class Patient extends Person {

    public Patient(PatientDTO dto) {
        super(dto.getName(), dto.getLastname(),
                calculateDUI(dto.getDui(), dto.getBirthday()),
                dto.getBirthday());
    }

    private static String calculateDUI(String dui, String birthday) {
        int anioNacimiento = Integer.parseInt(birthday.split("-")[2]);
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        return (anioActual - anioNacimiento < 18) ? "00000000-0" : dui;
    }

    public String getDui() {
        return dui;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInfo() {
        return "Datos del paciente: " + getName() + " " + getLastName() + ", DUI: " + getDui() + ", Cumpleaños: " + getBirthDate();
    }
}
