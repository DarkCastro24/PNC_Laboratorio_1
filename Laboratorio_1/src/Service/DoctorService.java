package Service;

import Model.DTO.DoctorDTO;
import Model.Entity.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    private List<Doctor> doctors = new ArrayList<>();

    public void agregarDoctor(DoctorDTO nuevoDoctorDTO) {
        Doctor d = new Doctor(
                nuevoDoctorDTO.getNombre(),
                nuevoDoctorDTO.getApellido(),
                nuevoDoctorDTO.getDui(),
                nuevoDoctorDTO.getCumpleanios(),
                nuevoDoctorDTO.getFechaReclutamiento(),
                nuevoDoctorDTO.getEspecialidad(),
                generarCodigoDoctor()
        );
        doctors.add(d);
        System.out.println("Doctor agregado: " + d.getInfo());
    }

    public Doctor getDoctorByCodigo(String codigo) {
        for (Doctor d : doctors) {
            if (d.getCodigoDoctor().equals(codigo)) {
                return d;
            }
        }
        return null;
    }

    private String generarCodigoDoctor() {
        return "DOC-" + (doctors.size() + 1);
    }
}