import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] P = new int[N];

        for(int i=0; i<N; i++) {
            P[i] = scanner.nextInt();
        }

        Arrays.sort(P);

        int sum = 0;

        for(int i=0; i<N; i++) {
            sum += P[i] * (N-i);
        }

        System.out.println(sum);
    }
}
