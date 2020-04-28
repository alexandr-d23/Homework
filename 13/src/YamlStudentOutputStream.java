import org.ho.yaml.*;
import org.ho.yaml.YamlEncoder;

import java.io.IOException;
import java.io.OutputStream;

public class YamlStudentOutputStream extends OutputStream {
    private OutputStream out;

    public YamlStudentOutputStream(OutputStream out){
        this.out = out;
    }

    public void writeStudent(Student stud){
        Object o = stud;
        Yaml.dump(o,out);

    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }
}
