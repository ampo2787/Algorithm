import java.util.*;

public class Laboratory3_17142 {
    static int N;
    static int M;
    static int[][] map;
    static ArrayList<int[]> virusList;

    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,-1,1};

    static int minResult = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt(); //활성화할 바이러스 개수.

        map = new int[N][N];

        virusList = new ArrayList<>();

        boolean emptyIsNone = true;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == 2) {
                    int[] temp = {i,j};
                    virusList.add(temp);
                }
                else if(map[i][j] == 0) {
                    emptyIsNone = false;
                }
            }
        }

        if(emptyIsNone) {
            System.out.println(0);
            System.exit(0);
        }

        //virusList 개수 중 M개를 뽑는다.
        combination(virusList.size(),0 , M, new boolean[virusList.size()]);
        if(minResult == Integer.MAX_VALUE) {
            minResult = -1;
        }

        System.out.println(minResult);
    }

    public static void combination(int n, int index, int r, boolean[] visit) {
        if(r == 0) {
            Queue<int[]> queue = new LinkedList<>();
            int[][] time = new int[N][N];
            boolean[][] virusVisit = new boolean[N][N];

            for(int i=0; i<N; i++) {
                Arrays.fill(time[i], -1);
                for(int j=0; j<N; j++) {
                    if(map[i][j] == 1) {
                        time[i][j] = -2;
                    }
                    else if(map[i][j] == 2) {
                        time[i][j] = 0;
                    }
                }
            }
            // 벽은 -2, 활성화 바이러스 0, 빈 칸 -1, 전파된 활성화 바이러스 = 시간.

            for(int i=0; i<n; i++) {
                if(visit[i]) {
                    int[] activeVirus = virusList.get(i);
                    time[activeVirus[0]][activeVirus[1]] = 0;
                    virusVisit[activeVirus[0]][activeVirus[1]] = true;
                    queue.add(activeVirus);
                }
            }

            int thisTime = 0;

            while(!queue.isEmpty()) {
                ArrayList<int[]> thisList = new ArrayList<>();

                while(!queue.isEmpty()) {
                    thisList.add(queue.poll());
                }
                thisTime++;

                for(int i = 0; i < thisList.size(); i++) {
                    int[] thisVirus = thisList.get(i);
                    for(int j = 0; j < 4; j++) {
                        int ny = thisVirus[0] + dy[j];
                        int nx = thisVirus[1] + dx[j];

                        if(ny < 0 || ny >= N || nx < 0 || nx >= N) {
                            continue;
                        }

                        if(!virusVisit[ny][nx] && (time[ny][nx] == -1 || time[ny][nx] == 0)) {
                            time[ny][nx] = 0;
                            virusVisit[ny][nx] = true;
                            int[] nextVirus = {ny,nx};
                            queue.add(nextVirus);
                        }
                    }
                }

                boolean allVirus = true;
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        if(time[i][j] == -1) {
                            allVirus = false;
                        }
                    }
                }

                if (allVirus) {
                    minResult = Math.min(minResult, thisTime);
                }
            }



            return;
        }

        for(int i=index; i<n; i++) {
            if(!visit[i]) {
                visit[i] = true;
                combination(n, i+1, r-1, visit);
                visit[i] = false;
            }
        }
    }
}
