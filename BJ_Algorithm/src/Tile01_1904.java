import java.util.Scanner;

public class Tile01_1904 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        long[][] dp = new long[N+1][2];
        dp[1][1] = 1;

        for(int i=2; i<=N; i++) {
            dp[i][0] = dp[i-1][1];
            dp[i][1] = (dp[i-1][0] + dp[i-1][1]) % 15746;
        }

        long sum = 0;

        for(int i=0; i<2; i++) {
            sum = sum + dp[N][i];
        }

        System.out.print(sum % 15746);
    }
}
