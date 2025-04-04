package Model.DTO;

public class DoctorDTO {
    private String nombre;
    private String apellido;
    private String dui;
    private String cumpleaños;
    private String fechaReclutamiento;
    private String especialidad;

    public DoctorDTO(String nombre, String apellido, String dui, String cumpleaños, String fechaReclutamiento, String especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.cumpleaños = cumpleaños;
        this.fechaReclutamiento = fechaReclutamiento;
        this.especialidad = especialidad;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDui() { return dui; }
    public String getCumpleaños() { return cumpleaños; }
    public String getFechaReclutamiento() { return fechaReclutamiento; }
    public String getEspecialidad() { return especialidad; }
}