import sun.invoke.empty.Empty;

import java.io.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;

public class IniInputStream extends InputStream {
    BufferedReader br;

    IniInputStream(InputStream in)throws FileNotFoundException{
        br = new BufferedReader(new InputStreamReader(in));
    }

    public Map<String, Map<String,String>> readINI()throws IOException{
        Map<String,Map<String,String>> map = new LinkedHashMap<>();
        Map<String,String> section = new LinkedHashMap<>();
        Map<String,String> copy ;
        String sectName = null;
        String s;
        while((s = br.readLine())!= null){
            if(s.trim().charAt(0)=='['){
                if(sectName!=null) {
                    copy = new LinkedHashMap<>();
                    copy.putAll(section);
                    map.put(sectName, copy);
                    section.clear();
                }
                sectName = s.trim().substring(1, s.length() - 1);
            }
            else{
                section.put(s.split("=")[0].trim(),s.split("=")[1].trim());
            }
        }
        map.put(sectName,section);
        return map;
    }
    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return super.skip(n);
    }

    @Override
    public int available() throws IOException {
        return super.available();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset();
    }

    @Override
    public boolean markSupported() {
        return super.markSupported();
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
