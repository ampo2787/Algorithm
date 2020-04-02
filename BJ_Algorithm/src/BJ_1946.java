import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int t = 1; t<= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] score = new int[N][2];

            for(int n=0; n<N; n++) {
                st = new StringTokenizer(br.readLine());
                score[n][0] = Integer.parseInt(st.nextToken());
                score[n][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(score, new Comparator<int[]>() {
                @Override
                public int compare(int[] t1, int[] t2) {
                    if(t1[0] < t2[0]) {
                        return -1;
                    }
                    else if(t1[0] == t2[0]) {
                        if(t1[1] < t2[1]) {
                            return -1;
                        } else if(t1[1] == t2[1]) {
                            return 0;
                        }
                        else {
                            return 1;
                        }
                    } else {
                        return 1;
                    }
                }
            });

            int ans = N; //합격자는 전체 지원자로 초기화
            int in = score[0][1];//서류 1등의 면접 등수로 초기화
            for(int j = 1; j < N; j++) {
                //서류 등수가 높은 순 -> j 이전 데이터 중 가장 높은 면접 등수와만 비교하면 됨
                int cur = score[j][1]; //현재 검사하는 지원자의 면접 등수
                if(in < cur){//저장된 면접 등수보다 낮으면
                    ans--;//감소
                }
                if(cur < in)//높으면
                    in=cur;//max값 업데이트
            }

            System.out.println(ans);
        }
    }
}
