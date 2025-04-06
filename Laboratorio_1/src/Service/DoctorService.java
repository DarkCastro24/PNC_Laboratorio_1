package Service;

import Model.DTO.DoctorDTO;
import Model.Entity.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    private List<Doctor> doctors = new ArrayList<>();

    public String addDoctor(DoctorDTO dto) {
        Doctor doctor = new Doctor(dto);
        doctors.add(doctor);
        System.out.println("Doctor agregado: " + doctor.getInfo());
        return doctor.getCodeDoctor();
    }

    public Doctor getDoctorByCodigo(String codigo) {
        for (Doctor d : doctors) {
            if (d.getCodeDoctor().equals(codigo)) {
                return d;
            }
        }
        return null;
    }

    private String generarCodigoDoctor() {
        return "DOC-" + (doctors.size() + 1);
    }
}