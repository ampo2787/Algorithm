import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BJ_10610 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        char[] input = sc.nextLine().toCharArray();
        Integer[] toInt = new Integer[input.length];
        long sum = 0;
        int i = 0;
        boolean isZero = false;

        for(char a : input) {
            int temp = a - '0';

            sum += temp;
            toInt[i++] = temp;

            if(temp == 0) {
                isZero = true;
            }

        }

        if(isZero) {
            if(sum % 3 == 0) {
                Arrays.sort(toInt, Collections.reverseOrder());

                for(int a : toInt) {
                    sb.append(a);
                }
            }
        }
        if(sb.length() == 0) {
            System.out.println(-1);
        }
        else {
            System.out.println(sb);
        }



    }
}
