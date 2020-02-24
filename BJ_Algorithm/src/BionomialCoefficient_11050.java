import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        if(K < 0 || K > N) {
            System.out.println(0);
        }
        else {
            int[] factorial = new int[N+1];
            factorial[0] = 1;

            for(int i=1; i<=N; i++) {
                factorial[i] = factorial[i-1] * i;
            }

            int result = factorial[N] / (factorial[K] * factorial[N-K]);

            System.out.println(result);
        }

    }
}
