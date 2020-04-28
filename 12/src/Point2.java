import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String regex = "((([A-Za-z0-9])+(-([A-Za-z0-9])+)?)+(\\.))*(ru|biz|com|edu|gov|info|net|org)";
        String domain = in.nextLine();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(domain);
        boolean check = matcher.matches();
        System.out.println(check?"Yes":"No");
    }
}
