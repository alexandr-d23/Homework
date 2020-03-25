import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Student> list = new ArrayList<>();
        Serialization s=new Serialization();
        for(int i=0;i<10;i++){
            list.add(new Student("Student"+i,true,1900,(short)902));
        }
        s.write(list);
        list = (ArrayList)s.read();
        for(Student a:list) System.out.println(a.toString());
    }
}
