import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4013 {
    static int[][] magnet;
    static boolean visit[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            int K = Integer.parseInt(br.readLine());
            magnet = new int[5][8];

            for(int i=1; i<5; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<8; j++) {
                    magnet[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());

                int thisMagnet = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());

                visit = new boolean[5];
                visit[thisMagnet] = true;

                if(direction == 1) {
                    clock(thisMagnet);
                }
                else {
                    reverseClock(thisMagnet);
                }
            }

            // 빨간 화살표 : 0, 오른쪽 접한 면 : 2, 왼쪽 접한 면 : 6.
            // 여기에서 점수 계산.
            int result = 0;

            for(int i=1; i<5; i++) {
                result += magnet[i][0] * Math.pow(2, i-1);
            }

            sb.append("#" + tc + " " + result + "\n");
        }

        System.out.print(sb);
    }

    private static void reverseClock(int thisMagnet) {
        int leftMagnet = thisMagnet - 1;
        int rightMagnet = thisMagnet + 1;

        if(leftMagnet >= 1 && !visit[leftMagnet]) {
            if(magnet[leftMagnet][2] != magnet[thisMagnet][6]) {
                visit[leftMagnet] = true;
                clock(leftMagnet);
            }
        }


        if(rightMagnet <= 4 && !visit[rightMagnet]) {
            if(magnet[rightMagnet][6] != magnet[thisMagnet][2]) {
                visit[rightMagnet] = true;
                clock(rightMagnet);
            }
        }

        int temp = magnet[thisMagnet][0];
        for(int i=0; i<7; i++) {
            magnet[thisMagnet][i] = magnet[thisMagnet][i+1];
        }
        magnet[thisMagnet][7] = temp;

    }

    private static void clock(int thisMagnet) {
        int leftMagnet = thisMagnet - 1;
        int rightMagnet = thisMagnet + 1;

        if(leftMagnet >= 1 && !visit[leftMagnet]) {
            if(magnet[leftMagnet][2] != magnet[thisMagnet][6]) {
                visit[leftMagnet] = true;
                reverseClock(leftMagnet);
            }
        }


        if(rightMagnet <= 4 && !visit[rightMagnet]) {
            if(magnet[rightMagnet][6] != magnet[thisMagnet][2]) {
                visit[rightMagnet] = true;
                reverseClock(rightMagnet);
            }
        }

        int temp = magnet[thisMagnet][7];

        for (int i = 7; i > 0; i--) {
            magnet[thisMagnet][i] = magnet[thisMagnet][i-1];
        }
        magnet[thisMagnet][0] = temp;
    }
}
