package Model.DTO;

public class PatientDTO {
    private String name;
    private String lastname;
    private String dui;
    private String birthday;

    public PatientDTO(String name, String lastname, String dui, String birthday) {
        this.name = name;
        this.lastname = lastname;
        this.dui = dui;
        this.birthday = birthday;
    }

    public String getName() { return name; }
    public String getLastname() { return lastname; }
    public String getDui() { return dui; }
    public String getBirthday() { return birthday; }
}