import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        ArrayList<Integer> N = new ArrayList<>();
        int max = 0;

        for(int i=0; i<T; i++) {
            int temp = scanner.nextInt();
            if (temp > max) {
                max = temp;
            }
            N.add(temp);
        }

        long[] tempP = {1,1,1,2,2,3,4,5,7,9};
        long[] P = new long[101];

        for(int i=0; i<tempP.length; i++) {
            P[i+1] = tempP[i];
        }

        for(int i=11; i<=max; i++) {
            P[i] = P[i-5] + P[i-1];
        }

        for(int i=0; i<T; i++) {
            int temp = N.get(i);
            System.out.println(P[temp]);
        }
    }
}
