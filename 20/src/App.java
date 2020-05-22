import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class App extends Thread {
    private URL currentURL;
    private Scanner in;
    Download download;
    public static void main(String[] args){
        App app = new App();
        app.launch();
    }

    private void launch(){
        in = new Scanner(System.in);
        enteringUrl();
    }

    private void enteringUrl(){
        try {
            System.out.println("Enter URL");
            currentURL = new URL(in.nextLine().trim());
        }
        catch (MalformedURLException e){
            System.out.println("Некорректный URL. Попробуйте ещё раз");
            enteringUrl();
        }
        System.out.println("File will be downloaded from: "+currentURL );
        System.out.println("Enter: \n1)Start downloading \n2)Check progress \n3)Stop downloading \n4)Exit");
        menu();
    }

    private void menu(){
        while(true) {
            switch (in.nextLine().trim()) {
                case "1":
                    startDownloading();
                    break;
                case "2":
                    checkProgress();
                    break;
                case "3":
                    stopDownloading();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Unknown command. Try again");
            }
        }
    }

    private void startDownloading(){
        if(download!=null && download.isAlive()){
            System.out.println("File is downloading");
        }
        else {
            download = new Download(currentURL);
            download.setPriority(1);
            download.start();
            System.out.println("Downloading started");
        }
    }

    private void checkProgress(){
        if(download!=null) {
            System.out.println("Progress: "+download.progress()+"%");
            menu();
        }
        else {
            System.out.println("Download didn`t start");
        }
    }

    private void stopDownloading(){
        if(!download.isAlive()){
            System.out.println("Скачивание завершилось");
        }
        else {
            download.interrupt();
        }
            menu();
    }
}
