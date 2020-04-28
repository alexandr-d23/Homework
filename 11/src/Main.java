import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        IniInputStream in = new IniInputStream(new FileInputStream("src/Text"));
        Map<String, Map<String,String>> map = in.readINI();
        Map<String,String> section;
        for(Map.Entry<String,Map<String,String>> entry : map.entrySet()){
            System.out.print("["+entry.getKey()+"]\n");
            section = entry.getValue();
            for(Map.Entry<String,String> pair: section.entrySet()){
                System.out.print(pair.getKey());
                System.out.print(" = ");
                System.out.print(pair.getValue()+"\n");
            }
        }
        IniOutputStream out = new IniOutputStream(new FileOutputStream("src/Text"));
        out.writeINI(map);
    }
}
