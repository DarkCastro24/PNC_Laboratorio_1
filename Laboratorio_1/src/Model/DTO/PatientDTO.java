package Model.DTO;

public class PatientDTO {
    private String nombre;
    private String apellido;
    private String dui;
    private String cumpleaños;

    public PatientDTO(String nombre, String apellido, String dui, String cumpleaños) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.cumpleaños = cumpleaños;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDui() { return dui; }
    public String getCumpleaños() { return cumpleaños; }
}