import java.util.Scanner;

public class StairWalk_2579 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] score = new int[N+1];

        for(int i=1; i<N+1; i++) {
            score[i] = scanner.nextInt();
        }

        int[] dp = new int[N+1];
        dp[1] = score[1];

        if(N>=2)
            dp[2] = score[1] + score[2];

        for(int i=3; i < N+1; i++) {
            if(dp[i-2] > dp[i-3] + score[i-1]) {
                dp[i] = dp[i-2] + score[i];
            }
            else {
                dp[i] = dp[i-3] + score[i-1] + score[i];
            }
        }

        System.out.println(dp[N]);
    }
}
