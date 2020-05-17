import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private String name;
    private boolean gender;
    private int birthYear;
    private short group;

    public Student(String name,boolean gender,int birthYear, short group){
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public boolean isGender() {
        return gender;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public short getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", gender=" + gender +
                ", birthYear=" + birthYear +
                ", group=" + group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return isGender() == student.isGender() &&
                getBirthYear() == student.getBirthYear() &&
                getGroup() == student.getGroup() &&
                Objects.equals(getName(), student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), isGender(), getBirthYear(), getGroup());
    }
}
