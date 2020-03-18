import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class IntervalSum_5604 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        long[][] dp = new long[16][11];

        dp[1][1] = 45;
        dp[1][10] = 45; //한 자리 수 전체 덧셈.

        for(int i=2; i<16; i++) {
            for(int j=2; j<10; j++) {
                dp[i][j] = dp[i-1][10] * 10 + j * ;
            }
        }

        for(int tc=1; tc<=T; tc++) {
            char[] A = new char[16];
            Arrays.fill(A, '0');
            char[] temp = sc.next().toCharArray();
            int index = 15;
            for(int i=temp.length-1; i>= 0; i--) {
                A[index--] = temp[i];
            }
            int ALength = temp.length;

            char[] B = new char[16];
            Arrays.fill(B, '0');
            index = 15;
            temp = sc.next().toCharArray();

            for(int i=temp.length-1; i>= 0; i--) {
                B[index--] = temp[i];
            }
            int BLength = temp.length;

            long result = 0;

            for(int i=ALength+1; i<BLength; i++) {
                result += dp[i];
            }

            for(int i=AL)

            System.out.println("#" + tc + " " + result);
        }

    }
}
