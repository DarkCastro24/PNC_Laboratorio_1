package Service;

import Model.DTO.PatientDTO;
import Model.Entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientService {
    private List<Patient> pacientes = new ArrayList<>();

    public void agregarPaciente(PatientDTO dto) {
        Patient paciente = new Patient(dto);
        pacientes.add(paciente);
        System.out.println("Paciente agregado: " + paciente.getInfo());
    }

    public Patient getPatientByDui(String dui) {
        for (Patient p : pacientes) {
            if (p.getDui().trim().equals(dui)) {
                System.out.println("Paciente encontrado: " + p.getName() + " " + p.getLastName());
                return p;
            } else {
                System.out.println(p.getDui());
            }
        }
        return null;
    }
}