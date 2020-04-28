import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Pattern emailRegex = Pattern.compile("[a-zA-Z0-9]+@([a-zA-Z]+)(\\.([a-zA-Z]+))+");
        String newEmail = sc.nextLine();
        Matcher email = emailRegex.matcher(newEmail);
        while(email.find()) {
            System.out.println(email.group());
            System.out.println(email.group(email.groupCount()));
        }

    }
}
