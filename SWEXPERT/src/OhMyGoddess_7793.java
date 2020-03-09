import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class OhMyGoddess_7793 {
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            sc.nextLine();

            char[][] map = new char[N][M];
            // 수연 : S , 여신 : D , 돌 : X, 악마 : *

            Queue<int[]> suyeon = new LinkedList<>();
            Queue<int[]> devil = new LinkedList<>();

            for(int i=0; i<N; i++) {
                map[i] = sc.nextLine().toCharArray();
                for(int j=0; j<M; j++) {
                    int[] temp = {i,j};

                    switch (map[i][j]) {
                        case '*':
                            devil.add(temp);
                            break;
                        case 'S':
                            suyeon.add(temp);
                            break;
                    }
                }
            }

            int time = 0;
            boolean win = false;

            while(!suyeon.isEmpty()) {
                ArrayList<int[]> tempList = new ArrayList<>();

                while(!devil.isEmpty()) {
                    tempList.add(devil.poll());
                }

                for(int i=0; i<tempList.size(); i++) {
                    int[] temp = tempList.get(i);

                    for(int d=0; d<4; d++) {
                        int ny = temp[0] + dy[d];
                        int nx = temp[1] + dx[d];
                        int[] next = {ny,nx};
                        if(ny < 0 || ny >= N || nx < 0 || nx >=M) {
                            continue;
                        }

                        switch (map[ny][nx]) {
                            case '.':
                            case 'S':
                                map[ny][nx] = '*';
                                devil.add(next);
                                break;
                        }
                    }
                }

                tempList.clear();


                while(!suyeon.isEmpty()) {
                   tempList.add(suyeon.poll());
                }
                time++;

                for(int i=0; i<tempList.size(); i++) {
                    int[] temp = tempList.get(i);

                    for(int d=0; d<4; d++) {
                        int ny = temp[0] + dy[d];
                        int nx = temp[1] + dx[d];
                        int[] next = {ny,nx};

                        if(ny < 0 || ny >= N || nx < 0 || nx >=M) {
                            continue;
                        }

                        switch (map[ny][nx]) {
                            case '.':
                                map[ny][nx] = 'S';
                                suyeon.add(next);
                                break;
                            case 'D':
                                win = true;
                                i = tempList.size();
                                d = 4;
                                suyeon.clear();
                                break;
                        }
                    }
                }
            }

            if(win) {
                System.out.println("#" + test_case + " " + time);
            }
            else {
                System.out.println("#" + test_case + " GAME OVER");
            }

        }
    }
}
