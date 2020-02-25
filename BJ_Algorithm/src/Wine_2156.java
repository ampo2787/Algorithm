import java.util.Scanner;

public class Wine_2156 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] wine = new int[n];

        for(int i=0; i<n; i++) {
            wine[i] = sc.nextInt();
        }

        int[] dp = new int[n];
        dp[0] = wine[0];
        if(n==1) {
            System.out.println(wine[0]);
            System.exit(0);
        }
        switch (n) {
            case 2:
                dp[1] = dp[0] + wine[1];
                break;
            case 3:
                dp[1] = dp[0] + wine[1];
                dp[2] = Math.max(dp[0] + wine[2], wine[1] + wine[2]);
                break;
            case 4:
                dp[1] = dp[0] + wine[1];
                dp[2] = Math.max(dp[0] + wine[2], wine[1] + wine[2]);
                dp[3] = Math.max(dp[0] + wine[2] + wine[3], dp[1] + wine[3]);
                break;
            default:
                dp[1] = dp[0] + wine[1];
                dp[2] = Math.max(dp[0] + wine[2], wine[1] + wine[2]);
                dp[3] = Math.max(dp[0] + wine[2] + wine[3], dp[1] + wine[3]);
                for(int i=4; i < n; i++) {
                    dp[i] = dp[i-4] + wine[i-1] + wine[i];
                    dp[i] = Math.max(dp[i], dp[i-3] + wine[i-1] + wine[i]);
                    dp[i] = Math.max(dp[i], dp[i-2] + wine[i]);
                }
        }


        int max = 0;

        for(int i=0; i<n; i++) {
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }
}
