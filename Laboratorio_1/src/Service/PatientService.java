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
}