package Model.Entity;

public class Person {
    protected String name;
    protected String lastName;
    protected String dui;
    protected String birthDate;

    public Person(String name, String lastName, String dui, String birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.dui = dui;
        this.birthDate = birthDate;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDui() {
        return dui;
    }

    public String getBirthDate() {
        return birthDate;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
