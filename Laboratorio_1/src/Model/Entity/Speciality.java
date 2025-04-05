package Model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Speciality {
    private String name;

    public Speciality(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<Speciality> loadSpecialties() {
        List<Speciality> list = new ArrayList<>();
        list.add(new Speciality("Cardiologo"));
        list.add(new Speciality("Neurologo"));
        list.add(new Speciality("Pediatriatra"));
        list.add(new Speciality("Dentista"));
        list.add(new Speciality("Medicina General"));
        return list;
    }
}
