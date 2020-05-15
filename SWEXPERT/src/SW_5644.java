import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_5644 {
    static int[] dy = {0, -1, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, -1};

    static int M, A;
    static int[] aMove, bMove;

    static ArrayList<boolean[][]> BCMapList;
    static ArrayList<Integer> BCPowerList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            aMove = new int[M];
            bMove = new int[M];

            st = new StringTokenizer(br.readLine());

            for(int i=0; i<M; i++) {
                aMove[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            for(int i=0; i<M; i++) {
                bMove[i] = Integer.parseInt(st.nextToken());
            }


            BCMapList = new ArrayList<>();
            BCPowerList = new ArrayList<>();

            for(int i=0; i<A; i++) {
                st = new StringTokenizer(br.readLine());

                boolean[][] bcMap = new boolean[11][11];
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                BCPowerList.add(p);

                int[] item = {y,x};

                Queue<int[]> queue = new LinkedList<>();
                queue.add(item);
                bcMap[y][x] = true;

                while(!queue.isEmpty()) {
                    int[] place = queue.poll();

                    for(int d=1; d<5; d++) {
                        int ny = dy[d] + place[0];
                        int nx = dx[d] + place[1];

                        if(ny < 1 || ny > 10 || nx < 1 || nx > 10) {
                            continue;
                        }

                        if(bcMap[ny][nx]) {
                            continue;
                        }

                        if(Math.abs(ny - y) + Math.abs(nx - x) <= c) {
                            int[] nextItem = {ny, nx};
                            bcMap[ny][nx] = true;
                            queue.add(nextItem);
                        }
                    }

                }
                BCMapList.add(bcMap);
            }

            int[] a = {1,1};
            int[] b = {10,10};



            int resultPower = 0;

            for(int i=-1; i < M; i++) {
                if(i != -1) {
                    a[0] += dy[aMove[i]];
                    a[1] += dx[aMove[i]];
                    b[0] += dy[bMove[i]];
                    b[1] += dx[bMove[i]];
                }

                int maxPower = 0;

                boolean[] aIsIn = new boolean[BCMapList.size()];
                boolean[] bIsIn = new boolean[BCMapList.size()];

                for(int bc = 0; bc < BCMapList.size(); bc++) {
                    boolean[][] thisBCMap = BCMapList.get(bc);

                    if(thisBCMap[a[0]][a[1]]) {
                        aIsIn[bc] = true;
                    }

                    if(thisBCMap[b[0]][b[1]]) {
                        bIsIn[bc] = true;
                    }
                }

                for(int x=0; x<BCMapList.size(); x++) {
                    for(int y=0; y<BCMapList.size(); y++) {
                        int power;
                        if(aIsIn[x] && bIsIn[y]) {
                            if(x == y) {
                                power = BCPowerList.get(x);
                            }
                            else {
                                power = BCPowerList.get(x) + BCPowerList.get(y);
                            }
                            maxPower = Math.max(power, maxPower);
                            continue;
                        }

                        if(aIsIn[x]) {
                            power = BCPowerList.get(x);
                            maxPower = Math.max(power, maxPower);
                            continue;
                        }

                        if(bIsIn[y]) {
                            power = BCPowerList.get(y);
                            maxPower = Math.max(power, maxPower);
                            continue;
                        }
                    }
                }

                resultPower += maxPower;


            }
            sb.append("#" + tc + " " + resultPower + "\n");
        }
        System.out.print(sb);
    }



}
