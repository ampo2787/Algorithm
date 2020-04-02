import java.util.Scanner;

public class SW_5607 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        long[] dp = new long[1000001];
        long module = 1234567891;
        dp[0] = 1;
        dp[1] = 1;

        for(int i=1;i<dp.length; i++) {
            dp[i] = (dp[i-1] * i) % module;
        }

        for(int tc=1; tc<=T; tc++) {
            int N = sc.nextInt();
            int R = sc.nextInt();
            long domi = (dp[R] * dp[N-R]) % module;
            long index = module - 2;
            long fermatNum = 1;

            while(index > 0){
                if(index % 2 == 1){
                    fermatNum *= domi;
                    fermatNum %= module;
                }
                domi = (domi * domi)%module;
                index /= 2;
            }
            long result = ((dp[N]%module)*(fermatNum%module))%module;

            System.out.println("#" + tc + " " + result);
        }
    }
}
