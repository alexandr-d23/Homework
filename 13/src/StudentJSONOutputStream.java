import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class StudentJSONOutputStream extends OutputStream implements AutoCloseable {

    private OutputStream out;

    public StudentJSONOutputStream(OutputStream out){
        this.out = out;
    }

    public void writeStudent(Student stud)throws IOException{
        JSONObject jObj = new JSONObject();
        jObj.put("name",stud.getName());
        jObj.put("gender",stud.isGender());
        jObj.put("birthYear",stud.getBirthYear());
        jObj.put("group",stud.getGroup());
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out))){
            bw.write(jObj.toJSONString());
            bw.flush();
        }
        catch(IOException e){
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }
}
