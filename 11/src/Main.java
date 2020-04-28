import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/Text");
        IniInputStream in = new IniInputStream(path);
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
        IniOutputStream out = new IniOutputStream(path);
        out.writeINI(map);
    }
}
