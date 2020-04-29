import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_4050 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int tc=1; tc<=TC; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] clothes = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                clothes[i] = Integer.parseInt(st.nextToken());
            }
            // 묶어서 결제해서 가장 할인을 많이 받는 법. 3개당 1개 무료.
            // N을 3으로 나눈 나머지만큼 가장 싼 옷 을 뺀다. 이는 할인이 없다.
            // 세벌 중 가장 저렴한 한 벌 값은 내지 않아도 된다.

            int result = 0;
            Arrays.sort(clothes);
            int solo = N % 3;

            for(int i=0; i<solo; i++) {
                result += clothes[i];
            }

            int number = 0;
            for(int i = N-1; i >= solo; i--) {
                if(number == 2) {
                    number = 0;
                    continue;
                }
                result += clothes[i];
                number++;
            }

            System.out.println("#" + tc + " " + result);
        }
    }
}
