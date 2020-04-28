import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args){

        //Проверка JSON файла
        Student stud = new Student("AlexJSON", true,2001, (short)902);
        Student stud1 = null;
        try(StudentJSONOutputStream out = new StudentJSONOutputStream(new FileOutputStream("src/JsonFile"))){
            out.writeStudent(stud);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        try(StudentJSONInputStream in = new StudentJSONInputStream(new FileInputStream("src/JsonFile"))){
            stud1 = in.readStudent();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(stud1.toString());


        //Проверка YAML файла
        stud = new Student("AlexYaml", true,2021, (short)902);
        stud1 = null;
        try(YamlStudentOutputStream out = new YamlStudentOutputStream(new FileOutputStream("src/YamlFile"))){
            out.writeStudent(stud);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        try(YamlStudentInputStream in = new YamlStudentInputStream(new FileInputStream("src/YamlFile"))){
            stud1 = in.readStudent();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(stud1.toString());

    }
}
