import java.util.Scanner;

public class BJ_9095 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        //dp
        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i=4; i<11; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        for(int t=1; t<=T; t++) {
            int n = sc.nextInt();
            // n 을 1,2,3 의 합으로 나타내는 방법의 수를 구해라.
            sb.append(dp[n] + "\n");
        }
        System.out.println(sb);
    }
}
