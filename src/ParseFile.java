import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oleg Serzhant on 15.10.2016.
 */
public class ParseFile {
    public static void main(String[] args) throws IOException {

        if (args.length <= 0){
            System.out.println("ParseFile.jar filename");
            System.exit(1);
        }

        String name = args[0];

        FileInputStream in = new FileInputStream(name);

        String string = "";
        char ch;
        while (in.available() > 0) {
            ch = (char) in.read();
            string += ch;
        }
        in.close();

        Pattern email = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Pattern word = Pattern.compile("[A-Za-zа-яА-ЯёЁ]{2,}");
        Pattern number = Pattern.compile("\\-?\\d+(\\.\\d{0,})?");

        System.out.println("Email: " + matchPattern(email, string));
        System.out.println("Word: " + matchPattern(word, string));
        System.out.println("Numbers: " + matchPattern(number, string));
        System.out.println("ABC: " + abc(string));
    }

    public static int matchPattern(Pattern pattern, String string) {

        Matcher matcher = pattern.matcher(string);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static int abc(String string){
        int count = 0;
        boolean sequence;
        String str[] = string.split(" ");

        for (int i = 0; i < str.length; i++) {                          //Перебираем массив слов
            if (str[i].length() < 2) continue;                          //Если меньше 2х символов - пропускаем
            char ch[] = str[i].toCharArray();
            sequence = true;
            for (int j = 0; j < ch.length - 1; j++) {                   //Перебираем слово по символам
                if (ch[j] >= ch[j+1] || (ch[j] < 97) || (ch[j] > 122)){ //Если Первый симлов больше второго или не входит в диапазон символов латинского алфавита
                    sequence = false;                                   //Нам не подходит
                    break;
                }
            }
            if (sequence) count++;
        }
        return count;
    }
}

