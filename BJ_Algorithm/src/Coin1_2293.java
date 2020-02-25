import java.util.Scanner;

public class Coin1_2293 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] value = new int[n];

        for(int i=0; i<n; i++) {
            value[i] = scanner.nextInt();
        }
        int[] dp = new int[k+1];

        dp[0] = 1; //최초 시작점

        for(int i = 0 ; i < n ; i++) {
            for(int j = 1 ; j <= k ; j++) {
                if(j - value[i] >= 0) {
                    dp[j] += dp[j - value[i]];
                }
            }
        }
        System.out.println(dp[k]);
    }
}
