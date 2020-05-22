import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Download extends Thread {
    private URL currentUrl;
    Path path;
    URLConnection connection;
    public long currentSize;
    public  long maxSize;

    public Download(URL currentUrl) {
        this.currentUrl = currentUrl;
        path = Paths.get("src\\Downloaded");
    }

    public void run(){
        download();
    }

    private void download(){
        try {
            connection = currentUrl.openConnection();
            maxSize = connection.getContentLength();
            String mime = connection.getContentType().split(";")[0];
            String extension = MimeTypes.getDefaultMimeTypes().forName(mime).getExtension();
            path = Paths.get(path + File.separator + connection.getContent().hashCode() + extension);
          //  try (Files.newOutputStream(path));
            //     BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));) {
            try(BufferedOutputStream bOut = new BufferedOutputStream(new FileOutputStream(path.toString()));
            BufferedInputStream bIn = new BufferedInputStream(connection.getInputStream());) {
                if (!Files.exists(path)) {
                    Files.createFile(path);
                }
                currentSize = 0;
                byte[] bytes = new byte[4096];
                int mark;
                while ((mark = bIn.read(bytes)) != -1) {
                    Thread.sleep(10);
                    bOut.write(bytes,0,mark);
                    bOut.flush();
                    currentSize = Files.size(path);
                }
                /*
                while ((line = br.readLine()) != null) {
                    bw.write(line+"\n");
                    bw.flush();
                    currentSize = Files.size(path);
                }
                 */
                System.out.println("File already downloaded");
            }
    //        }
        }
        catch (IOException | MimeTypeException | InterruptedException e) {
            System.out.println("Downloading interrupted");
            try {
                Files.delete(path);
            } catch (IOException io) {
                System.out.println("File is`nt deleted");
            }
        }
    }

    public int progress(){
            if(maxSize!=0)
            return (int)((double)(100*currentSize/maxSize));
            return 0;
    }


}
