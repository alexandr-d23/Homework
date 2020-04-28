import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point3 {
    public static void main(String[] args){
        String regex = "((([A-Za-z0-9])+(-([A-Za-z0-9])+)?)+(\\.))*(ru|biz|com|edu|gov|info|net|org)";
        Scanner sc = new Scanner(System.in);
        Pattern emailRegex = Pattern.compile(regex);
        String domain = sc.nextLine();
        Matcher matcher = emailRegex.matcher(domain);
        while(matcher.find()) {
            System.out.println(matcher.group());
        }



    }
}
