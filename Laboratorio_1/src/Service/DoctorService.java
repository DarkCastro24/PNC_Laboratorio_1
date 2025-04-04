package Service;

import Model.DTO.DoctorDTO;
import Model.Entity.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    private List<Doctor> doctores = new ArrayList<>();

    public void agregarDoctor(DoctorDTO dto) {
        Doctor doctor = new Doctor(dto);
        doctores.add(doctor);
        System.out.println("Doctor agregado: " + doctor.getInfo());
    }
}