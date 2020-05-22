import java.io.*;

public class IOStreamTask {

    public static void main(String[] args) {
        write(-1812394, 'ы');
        readAndOutput();

    }

    public static void write(int num, char c) {
        try (FileOutputStream fw = new FileOutputStream("src\\in.txt")) {
            int number = num;
            char ch = c;
            fw.write(number >>> 24 & 0xFF);
            fw.write(number >>> 16 & 0xFF);
            fw.write(number >>> 8 & 0xFF);
            fw.write(number >>> 0 & 0xFF);
            fw.write(ch >>> 8 & 0xFF);
            fw.write(ch >>> 0 & 0xFF);
            fw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readAndOutput(){
        try(InputStream fr = new FileInputStream("src\\in.txt")){
            int n1=fr.read();
            int n2=fr.read();
            int n3=fr.read();
            int n4=fr.read();
            int n5=fr.read();
            int n6=fr.read();
            System.out.println("Number : " + ((n1 << 24) + (n2 << 16) + (n3 << 8) + (n4<<0)));
            System.out.println("Char : " + (char)((n5 << 8) + n6 << 0));
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
