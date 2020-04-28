import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        //Test Data I/O
        Student stud = new Student("Alex", true,2001, (short)902);
        Student stud1 = null;
        try(StudentDataOutputStream out = new StudentDataOutputStream(new FileOutputStream("src/TestData"));
            StudentDataInputStream in = new StudentDataInputStream(new FileInputStream("src/TestData")) ){
            out.writeStudent(stud);
            stud1 = in.readStudent();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(stud1.toString());

        //Test Object I/O
        stud1=null;
        try(StudentObjectOutputStream objOut = new StudentObjectOutputStream(new FileOutputStream("src/TestObject"));
        StudentObjectInputStream objIn = new StudentObjectInputStream(new FileInputStream("src/TestObject"))){
            objOut.writeStudent(stud);
            stud1 = objIn.readStudent();

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(stud1.toString());

    }
}
