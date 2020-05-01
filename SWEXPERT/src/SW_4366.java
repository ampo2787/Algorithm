import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW_4366 {
    static int[] multiplyTwo = new int[40];
    static int[] multipleyThree = new int[40];
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        char zero = '0';
        char one = '1';
        char two = '2';
        char three = '3';

        multiplyTwo[0] = multipleyThree[0] = 1;

        for(int i=1; i<40; i++) {
            multiplyTwo[i] = multiplyTwo[i-1] * 2;
            multipleyThree[i] = multipleyThree[i-1] * 3;
        }

        for(int tc=1; tc<=TC; tc++) {
            char[] a = br.readLine().toCharArray(); //2진수.
            char[] b = br.readLine().toCharArray(); //3진수.
            result = -1;

            for(int i=0; i<a.length; i++) {
                if(a[i] == zero) {
                    a[i] = one;
                }
                else {
                    a[i] = zero;
                }

                for(int j=0; j<b.length; j++) {
                    for(int k=0; k<2; k++) {
                        b[j]++;
                        if(b[j] == three) {
                            b[j] = zero;
                        }
                        check(a, b);
                        if(result != -1) {
                            break;
                        }
                    }
                    if(result != -1) {
                        break;
                    }
                    b[j]++;
                    if(b[j] == three) {
                        b[j] = zero;
                    }
                }

                if(result != -1) {
                    break;
                }

                if(a[i] == zero) {
                    a[i] = one;
                }
                else {
                    a[i] = zero;
                }
            }
            sb.append("#"+tc+" " + result + "\n");
        }

        System.out.println(sb);
    }

    private static void check(char[] a, char[] b) {
        int resultA = 0;
        int resultB = 0;

        for(int i=a.length-1; i>=0; i--) {
            resultA += (a[i] - '0') * multiplyTwo[a.length - 1 - i];
        }

        for(int i=b.length-1; i>=0; i--) {
            resultB += (b[i] - '0') * multipleyThree[b.length - 1 - i];
        }

        if(resultA == resultB) {
            result = resultA;
        }
    }
}
