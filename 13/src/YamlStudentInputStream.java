import java.io.IOException;
import java.io.InputStream;

import org.ho.yaml.Yaml;

public class YamlStudentInputStream extends InputStream {
    private InputStream in;

    public YamlStudentInputStream(InputStream in){
        this.in = in;
    }

    public Student readStudent()throws IOException{
        Student stud = (Student)Yaml.load(in);
        return stud;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }
}
