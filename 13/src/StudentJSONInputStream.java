import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;


public class StudentJSONInputStream extends InputStream {
    private InputStream in;

    public StudentJSONInputStream(InputStream in){
        this.in = in;
    }

    public Student readStudent() throws IOException {
        JSONParser jParser = new JSONParser();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(in))){
            JSONObject jObj = (JSONObject)jParser.parse(br);
            return new Student((String)jObj.get("name"),(boolean)jObj.get("gender"),(int)(long)jObj.get("birthYear"),(short)(long)jObj.get("group"));
        }
        catch (Exception e){
            throw new IOException(e.getMessage());
        }

    }
    @Override
    public int read() throws IOException {
        return in.read();
    }
}
