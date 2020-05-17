import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;

public class Serialization {

    public Collection<Student> read() throws IOException {
        List<Student> list = new ArrayList<>();
        try(FileInputStream fi = new FileInputStream("C:\\Users\\Alex\\Desktop\\in.txt")){
            int sLength;
            ByteBuffer bb=ByteBuffer.allocate(4);
            for(int i=0;i<4;i++){
                bb.put((byte)fi.read());
            }
            sLength=bb.getInt(0);
            char[] array;
            boolean gender = false;
            int birthYear;
            short group;
            String name;
            while(sLength != -1){
                gender=false;
                array=new char[sLength];
                bb=ByteBuffer.allocate(11 + sLength*2);
                for(int i = 0; i < 11+sLength*2; i++){
                    bb.put((byte)fi.read());
                }
                bb.rewind();
                for(int i=0;i< sLength;i++){
                    array[i]=bb.getChar();
                }
                name = new String(array);
                if(bb.get()==1)gender=true;
                birthYear=bb.getInt();
                group = bb.getShort();
                sLength=bb.getInt();
                list.add(new Student(name, gender, birthYear,group));
            }
            return list;
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
