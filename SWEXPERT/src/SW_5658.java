import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SW_5658 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();

        for(int tc=1; tc<=TC; tc++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            sc.nextLine();
            String input = sc.nextLine();
            int[] number = new int[N];
            long[] pow = new long[N/4];

            for(int i=0; i<N/4; i++) {
                pow[i] = (long) Math.pow(16, i);
            }

            for(int i=0; i<N; i++) {
                char c = input.charAt(i);
                switch (c) {
                    case 'A':
                        number[i] = 10;
                        break;
                    case 'B':
                        number[i] = 11;
                        break;
                    case 'C':
                        number[i] = 12;
                        break;
                    case 'D':
                        number[i] = 13;
                        break;
                    case 'E':
                        number[i] = 14;
                        break;
                    case 'F':
                        number[i] = 15;
                        break;
                    default:
                        number[i] = c - '0';
                        break;
                }
            }

            //16진수 숫자 N개.
            ArrayList<Long> list = new ArrayList<>();
            //적힌 숫자를 돌려서 만들수 있는 수 중 K 번째로 큰 수를 10진수로 만든 수 = 비밀번호.
            //3개씩.

            for(int i=0; i<N; i++) {
                long sum = 0;

                for(int j = 0; j < N / 4; j++) {
                    sum += number[(i+j) % N] * pow[N/4 - 1 - j];
                }

                if(!list.contains(sum))
                    list.add(sum);
            }

            Collections.sort(list, Collections.reverseOrder());

            System.out.println("#" + tc + " " + list.get(K-1));
        }
    }
}
