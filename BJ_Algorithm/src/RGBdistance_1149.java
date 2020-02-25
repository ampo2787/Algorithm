import java.util.ArrayList;
import java.util.Scanner;

public class RGBdistance_1149 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // N <= 1000
        ArrayList<int[]> money = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            int[] temp = new int[3];
            for(int j=0; j<3; j++) {
                temp[j] = scanner.nextInt();
            }
            money.add(temp);
        }
        int[][] dp = new int[N][3];

        /*
        아이디어 : 마지막 집이 0빨강,1초록,2파랑일 때의 비용 점화식으로 나타낸다.
        빨 초 파
        빨파 빨초 초빨 초파 파빨 파초
        빨파빨 빨파초 빨초빨 빨초

        dp[N][0] = dp[N-1][1] + dp[n-1][2]
        dp[N][1] = dp[N-1][0] + dp[n-1][2]
        dp[N][2] = dp[N-1][0] + dp[n-1][1]
         */

        for(int i=0; i<3; i++) {
            dp[0][i] = money.get(0)[i];
        }
        int get0,get1,get2;
        for(int i=1; i<N; i++) {
            get0 = money.get(i)[0];
            get1 = money.get(i)[1];
            get2 = money.get(i)[2];

            if(dp[i-1][0] > dp[i-1][1]) {
                dp[i][2] = dp[i-1][1] + get2;
            }
            else {
                dp[i][2] = dp[i-1][0] + get2;
            }
            if(dp[i-1][1] > dp[i-1][2]) {
                dp[i][0] = dp[i-1][2] + get0;
            }
            else {
                dp[i][0] = dp[i-1][1] + get0;
            }
            if(dp[i-1][2] > dp[i-1][0]) {
                dp[i][1] = dp[i-1][0] + get1;
            }
            else {
                dp[i][1] = dp[i-1][2] + get1;
            }
        }
        int min = Integer.MAX_VALUE;

        for(int i=0; i<3; i++) {
            if(min > dp[N-1][i]) {
                min = dp[N-1][i];
            }
        }
        System.out.print(min);
    }
}
