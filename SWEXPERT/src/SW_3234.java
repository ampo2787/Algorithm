import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_3234 {

    private static int[] w;
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testcase; t++) {

            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            w = new int[N];

            for (int i = 0; i < w.length; i++) {
                w[i] = Integer.parseInt(st.nextToken());
            }

            cnt = 0;
            perm(0, 0, 0);

            System.out.println("#" + t + " " + cnt);

        }

    }

    /**
     *
     * @param step 현재 단계
     * @param l    현재 까지 저울에 올려논 왼쪽 추들의 합
     * @param r    현재 까지 저울에 올려논 오른쪽 추들의 합
     */
    private static void perm(int step, int l, int r) {
        if (step == w.length) {
            cnt++;
        } else {
            for (int i = step; i < w.length; i++) {
                // step 과 i 의 위치를 바꿔??

                int temp = w[step];
                w[step] = w[i];
                w[i] = temp;

                // 다음 단계 재귀호출
                perm(step + 1, l + w[step], r); // 저울의 왼쪽에 w[step]추를 올려 놓고 재귀호출
                if (l >= r + w[step]) { // 무게 비교해서 조건 안되면 안감.
                    perm(step + 1, l, r + w[step]); // 저울의 오른쪽에 w[step]추를 올려 놓고 재귀호출
                }

                temp = w[step];
                w[step] = w[i];
                w[i] = temp;

            }
        }

    }

}