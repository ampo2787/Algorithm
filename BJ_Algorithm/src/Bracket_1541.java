import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        ArrayList<Integer> number = new ArrayList<>();
        ArrayList<Character> sign = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(line, "-|+");

        while(tokenizer.hasMoreTokens()) {
            number.add(Integer.parseInt(tokenizer.nextToken()));
        }

        for(int i=0; i<line.length(); i++) {
            if(line.charAt(i) == 45 || line.charAt(i) == 43) {
                sign.add(line.charAt(i));
            }
        }
        int result = number.get(0);
        int sumResult = 0;

        for(int i=0; i<number.size()-1; i++) {
            if(sign.get(i).equals('+')) {
                if(sumResult == 0) {
                    result += number.get(i+1);
                }
                else {
                    sumResult += number.get(i+1);
                }
            }
            else {
                result = result - sumResult;
                sumResult = number.get(i+1);
            }
        }
        result = result - sumResult;
        System.out.println(result);
    }
}
