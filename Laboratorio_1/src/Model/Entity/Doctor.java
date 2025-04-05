package Model.Entity;

import Model.DTO.DoctorDTO;
import Utils.CodeUtils;

public class Doctor {
    private String nombre;
    private String apellido;
    private String dui;
    private String cumpleanios;
    private String fechaReclutamiento;
    private String especialidad;
    private String codigoDoctor;

    public Doctor(String nombre, String apellido, String dui, String cumpleanios, String fechaReclutamiento, String especialidad, String s) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.cumpleanios = cumpleanios;
        this.fechaReclutamiento = fechaReclutamiento;
        this.especialidad = especialidad;
        this.codigoDoctor = CodeUtils.generateDoctorCode();
    }

    public String getCodigoDoctor() {
        return codigoDoctor;
    }

    public String getInfo() {
        return "Datos del doctor: " + nombre + " " + apellido + ", DUI: " + dui + ", Especialidad: " + especialidad + ", Codigo asignado: " + codigoDoctor;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }


}