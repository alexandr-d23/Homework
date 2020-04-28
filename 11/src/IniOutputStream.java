import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.*;

public class IniOutputStream extends OutputStream {
    BufferedWriter bw;

    IniOutputStream(Path path) throws IOException {
        FileWriter fr = new FileWriter(path.toString());
        bw = new BufferedWriter(fr);
    }

    public void writeINI(Map<String, Map<String,String>> map) throws IOException{
        Map<String,String> section;
        for(Map.Entry<String,Map<String,String>> entry : map.entrySet()){
            bw.write("["+entry.getKey()+"]\n");
            section = entry.getValue();
            for(Map.Entry<String,String> pair: section.entrySet()){
                bw.write(pair.getKey());
                bw.write(" = ");
                bw.write(pair.getValue() +"\n");
            }
        }

        bw.flush();
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public void write(int b) throws IOException {
        throw new UnsupportedOperationException();
    }
}
