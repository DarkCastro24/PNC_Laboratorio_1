package Model.Entity;

import Model.DTO.DoctorDTO;
import Utils.CodeUtils;

public class Doctor {
    private String name;
    private String lastname;
    private String dui;
    private String birthday;
    private String recruitmentdate;
    private String specialty;
    private String codeDoctor;

    public Doctor(DoctorDTO dto) {
        this.name = dto.getName();
        this.lastname = dto.getLastname();
        this.dui = dto.getDui();
        this.birthday = dto.getBirthday();
        this.recruitmentdate = dto.getRecruitmentdate();
        this.specialty = dto.getSpecialty();
        this.codeDoctor = CodeUtils.generarCodigoDoctor();
    }

    public String getInfo() {
        return "Doctor: " + name + " " + lastname + ", DUI: " + dui + ", Especialidad: " + specialty + ", Código: " + codeDoctor;
    }
}