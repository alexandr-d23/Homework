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
}
