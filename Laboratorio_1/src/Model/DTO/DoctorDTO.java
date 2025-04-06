package Model.DTO;

public class DoctorDTO {
    private String name;
    private String lastname;
    private String dui;
    private String birthday;
    private String recruitmentdate;
    private String specialty;

    public DoctorDTO(String name, String lastname, String dui, String birthday, String recruitmentdate, String specialty) {
        this.name = name;
        this.lastname = lastname;
        this.dui = dui;
        this.birthday = birthday;
        this.recruitmentdate = recruitmentdate;
        this.specialty = specialty;
    }

    public String getName() { return name; }
    public String getLastname() { return lastname; }
    public String getDui() { return dui; }
    public String getBirthday() { return birthday; }
    public String getRecruitmentdate() { return recruitmentdate; }
    public String getSpecialty() { return specialty; }
}