import java.util.*;

public class BJ_2146 {
    static int N;
    static int[][] inputMap;
    static int[][] map;
    static boolean[][] visit;
    static int min;
    static Queue<int[]> queue;
    static ArrayList<int[]> list;

    final static int[] dy = {1,-1,0,0};
    final static int[] dx = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        inputMap = new int[N][N];
        map = new int[N][N];
        visit = new boolean[N][N];
        min = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                inputMap[i][j] = sc.nextInt();
            }
        }

        // 일단 bfs로 대륙을 찾아서 각기 다른 번호로 표기.
        // 찾은 대륙에서 bfs를 시작하고, 다른 대륙을 찾으면 바로 끝냄.
        // 끝낼 때 거리를 갱신.
        int number = 0;

        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if(inputMap[i][j] == 1 && map[i][j] == 0) {
                    number++;
                    queue = new LinkedList<>();
                    int[] item = {i,j};
                    queue.offer(item);
                    map[i][j] = number;

                    while(!queue.isEmpty()) {
                        // 이 땅과 동일한 대륙인 땅 모두 찾기.
                        int[] place = queue.poll();

                        for(int d=0; d<4; d++) {
                            int ny = dy[d] + place[0];
                            int nx = dx[d] + place[1];

                            if(ny < 0 || ny >= N || nx < 0 || nx >= N) {
                                continue;
                            }

                            if(inputMap[ny][nx] == 1 && map[ny][nx] == 0) {
                                int[] nextPlace = {ny, nx};
                                queue.offer(nextPlace);
                                map[ny][nx] = number;
                            }
                        }
                    }
                }
            }
        }

        // 대륙 별 표기 완료.
        // 가장자리에서 가장 가까운 다른 대륙으로 bfs.

        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0) {
                    visit = new boolean[N][N];
                    queue = new LinkedList<>();
                    int[] start = {i,j};
                    queue.offer(start);
                    visit[i][j] = true;
                    int bridge = -1;
                    int kind = map[i][j];

                    while(!queue.isEmpty()) {
                        list = new ArrayList<>();
                        bridge++;

                        while(!queue.isEmpty()) {
                            list.add(queue.poll());
                        }

                        for(int k=0; k<list.size(); k++) {
                            int[] place = list.get(k);

                            for(int d=0; d<4; d++) {
                                int ny = dy[d] + place[0];
                                int nx = dx[d] + place[1];

                                if(ny < 0 || ny >= N || nx < 0 || nx >= N) {
                                    continue;
                                }

                                if(!visit[ny][nx] && map[ny][nx] == 0) {
                                    int[] nextPlace = {ny, nx};
                                    queue.offer(nextPlace);
                                    visit[ny][nx] = true;
                                    continue;
                                }

                                if(map[ny][nx] != 0 && map[ny][nx] != kind) {
                                    min = Math.min(min, bridge);
                                    queue.clear();
                                    list.clear();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(min);
    }
}
