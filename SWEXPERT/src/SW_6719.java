import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_6719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] M = new int[N];
            double level = 0;

            st = new StringTokenizer(br.readLine());
            for(int n=0; n<N; n++) {
                M[n] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(M);

            for(int i = N - K; i < N; i++) {
                level = (M[i] + level) / 2;
            }

            System.out.println("#" + t + " " + level);
        }
    }
}
