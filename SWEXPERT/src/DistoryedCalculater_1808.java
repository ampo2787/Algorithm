import java.util.Scanner;

public class DistoryedCalculater_1808 {
    static boolean[] input;
    static int X;
    static int min;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case ++) {
            input = new boolean[10];

            for(int i=0; i<10; i++) {
                int temp = sc.nextInt();
                if(temp == 1) {
                    input[i] = true;
                }
            }

            X = sc.nextInt();
            // 숫자 버튼, 곱하기 버튼, 계산 버튼.
            min = Integer.MAX_VALUE;

            find(X,0);


            if(min == Integer.MAX_VALUE) {
                min = -1;
            }

            System.out.println("#" + test_case + " " + min);
        }
    }

    public static int find(int x, int cnt) {
        if(isMake(x + "")) {
            if(cnt == 0) {
                min = len(x) + 1;
            }
            return len(x) + 1;
        }

        int result = -1;

        for(int i = 2, end = (int) Math.sqrt(x) + 1; i < end; i++) {
            if(x % i == 0 && isMake(i + "")) {
                int len1 = len(i) + 1;
                int len2 = find(x / i , cnt + 1);

                if(len2 > -1) {
                    result = len1 + len2;

                    if(result < min && x == X) {
                        min = result;
                    }
                }
            }
        }
        return result;
    }

    public static boolean isMake(String x) {
        for (char c: x.toCharArray()) {
            if(!input[c-'0']) {
                return false;
            }
        }
        return true;
    }

    public static int len(int x) {
        return (int) Math.log10(x) + 1;
    }
}
