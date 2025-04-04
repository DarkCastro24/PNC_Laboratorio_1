package Model.Entity;

import Model.DTO.DoctorDTO;
import Utils.CodeUtils;

public class Doctor {
    private String nombre;
    private String apellido;
    private String dui;
    private String cumpleaños;
    private String fechaReclutamiento;
    private String especialidad;
    private String codigoDoctor;

    public Doctor(DoctorDTO dto) {
        this.nombre = dto.getNombre();
        this.apellido = dto.getApellido();
        this.dui = dto.getDui();
        this.cumpleaños = dto.getCumpleaños();
        this.fechaReclutamiento = dto.getFechaReclutamiento();
        this.especialidad = dto.getEspecialidad();
        this.codigoDoctor = CodeUtils.generarCodigoDoctor();
    }

    public String getInfo() {
        return "Doctor: " + nombre + " " + apellido + ", DUI: " + dui + ", Especialidad: " + especialidad + ", Código: " + codigoDoctor;
    }
}