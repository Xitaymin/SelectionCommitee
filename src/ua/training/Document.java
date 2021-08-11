package ua.training;

public class Document {
    private final Speciality speciality;
    private final String title;

    public Document(Speciality speciality, String title) {
        this.speciality = speciality;
        this.title = title;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return this.title + " " + this.speciality;
    }
}
