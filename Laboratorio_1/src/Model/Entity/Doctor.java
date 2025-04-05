package Model.Entity;

import Model.DTO.DoctorDTO;
import Utils.CodeUtils;

public class Doctor extends Person {
    private String recruitmentdate;
    private String specialty;
    private String codeDoctor;

    public Doctor(DoctorDTO dto) {
        super(dto.getName(), dto.getLastname(), dto.getDui(), dto.getBirthday());
        this.recruitmentdate = dto.getRecruitmentdate();
        this.specialty = dto.getSpecialty();
        this.codeDoctor = CodeUtils.generarCodigoDoctor();
    }

    public String getInfo() {
        return "Doctor: " + name + " " + lastName + ", DUI: " + dui +
                ", Especialidad: " + specialty + ", Código: " + codeDoctor;
    }
}
