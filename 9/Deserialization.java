import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collection;

public class Deserialization {
    public void write(Collection<Student> collection) throws IOException {
        try(FileOutputStream fo = new FileOutputStream("C:\\Users\\Alex\\Desktop\\in.txt")){
            ByteBuffer bb=null;
            for(Student s :collection) {
                bb = ByteBuffer.allocate(11+s.getName().length()*2);
                bb.putInt(s.getName().length());
                byte gender=0;
                for(int i=0;i< s.getName().length();i++){
                    bb.putChar(s.getName().charAt(i));
                }
                if(s.isGender())gender=1;
                bb.put(gender);
                bb.putInt(s.getBirthYear());
                bb.putShort(s.getGroup());
                fo.write(bb.array());
                fo.flush();
            }
        }
        catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }
}
