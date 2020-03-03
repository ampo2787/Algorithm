import java.util.Scanner;

public class ChocoAndRaisins_9282 {
    static int n;
    static int m;
    static int[][] a;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();

        for(int test_case=1; test_case<=TC; test_case++) {
            n = sc.nextInt();
            m = sc.nextInt();
            a = new int[n][m];
            result = Integer.MAX_VALUE;

            for(int i=0;i<n; i++) {
                for(int j=0; j<m; j++) {
                    a[i][j] = sc.nextInt();
                }
            }

            result = dfs(0, n - 1, 0, m - 1);
            System.out.println("#" + test_case + " " + result);
        }
    }

    public static int dfs(int y1, int y2, int x1, int x2) {
        int pay = 0;

        for(int i=y1; i<=y2; i++) {
            for(int j=x1; j<=x2; j++) {
                pay += a[i][j];
            }
        }

        if(y2== y1 && x2 == x1) {
            return 0;
        }

        if((y2 == y1 && x2 - 1 == x1) || (x2 == x1 && y2 - 1 == y1)) { //크기가 2일 때.
            return pay;
        }

        int minPayThisTime = Integer.MAX_VALUE;

        boolean isY = true;
        int dividePoint = y1;
        //y1 ~ divide[0] , divide[0]+1 ~ y2 이런식.

        for(int i = y1; i < y2; i++) { // 가로 커팅.
            int tempPay1 = 0;
            int tempPay2 = 0;
            for (int m = y1; m <= i; m++) {
                for (int n = x1; n <= x2; n++) {
                    tempPay1 += a[m][n];
                }
            }

            for (int m = i + 1; m <= y2; m++) {
                for (int n = x1; n <= x2; n++) {
                    tempPay2 += a[m][n];
                }
            }

            if (tempPay1 < tempPay2) {
                if(tempPay2 < minPayThisTime) {
                    minPayThisTime = tempPay2;
                    dividePoint = i;
                }
            }
            else {
                if(tempPay1 < minPayThisTime) {
                    minPayThisTime = tempPay1;
                    dividePoint = i;
                }
            }
        }

        for(int i = x1; i < x2; i++) { // 세로 커팅.
            int tempPay1 = 0;
            int tempPay2 = 0;
            for (int m = y1; m <= y2; m++) {
                for (int n = x1; n <= i; n++) {
                    tempPay1 += a[m][n];
                }
            }

            for (int m = y1; m <= y2; m++) {
                for (int n = i+1; n <= x2; n++) {
                    tempPay2 += a[m][n];
                }
            }

            if (tempPay1 < tempPay2) {
                if(tempPay2 < minPayThisTime) {
                    minPayThisTime = tempPay2;
                    isY = false;
                    dividePoint = i;
                }
            }
            else {
                if(tempPay1 < minPayThisTime) {
                    minPayThisTime = tempPay1;
                    isY = false;
                    dividePoint = i;
                }
            }
        }

        if(isY) {
            pay += dfs(y1,dividePoint,x1,x2);
            pay += dfs(dividePoint + 1,y2,x1,x2);
        }
        else {
            pay += dfs(y1,y2,x1,dividePoint);
            pay += dfs(y1,y2,dividePoint+1,x2);
        }

        return pay;
    }

}
