import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int[] dy = {-1, 0, 0, 1};
        int[] dx = {0, -1, 1, 0};

        int N = sc.nextInt();
        int[][] map = new int[N][N];
        int[] start = new int[2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        int babySharkSize = 2;
        int babySharkEat = 0;
        int time = 0;
        // 큰 물고기만 못지나감. 같은 물고기는 지나가지만 못먹음, 작은 물고기는 지나갈 수 있고 먹음.
        // 공간에 먹을 것이 없으면 엔드.
        // 1마리라면 그 물고기로.
        // 1마리보다 많으면 가까운 물고기로.
        // 거리는 지나야하는 칸 갯수.
        // 같은 거리의 물고기일 경우 위에, 그래도 같다면 왼쪽에.
        // 이동과 물고기 먹는 것은 동시에.
        // 크기만큼 물고기 수를 먹으면 크기 1 증가.
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(start.clone());

        int distance = -1;
        boolean[][] visit = new boolean[N][N];

        while (!queue.isEmpty()) {
            ArrayList<int[]> nextPlaceList = new ArrayList<>();
            ArrayList<int[]> minList = new ArrayList<>();
            while (!queue.isEmpty()) {
                nextPlaceList.add(queue.poll());
            }
            distance++;

            for (int a = 0; a < nextPlaceList.size(); a++) {
                int[] place = nextPlaceList.get(a);
                visit[place[0]][place[1]] = true;
                for (int i = 0; i < 4; i++) {
                    int ny = place[0] + dy[i];
                    int nx = place[1] + dx[i];
                    int[] nextPlace = {ny, nx};
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                        continue;
                    }
                    if (map[ny][nx] == 0) {
                        if (!visit[ny][nx]) {
                            queue.add(nextPlace);
                            visit[ny][nx] = true;
                        }
                    } else if (map[ny][nx] < babySharkSize) {
                        minList.add(nextPlace);
                        // 먹이 찾음.
                    } else if (map[ny][nx] == babySharkSize) {
                        if (!visit[ny][nx]) {
                            queue.add(nextPlace);
                            visit[ny][nx] = true;
                        }
                    }
                }

            }
            if (minList.size() != 0) {
                int[] nextPlace = {Integer.MAX_VALUE, Integer.MAX_VALUE};

                for (int i = 0; i < minList.size(); i++) {
                    if (minList.get(i)[0] < nextPlace[0]) {
                        nextPlace = minList.get(i);
                    } else if (nextPlace[0] == minList.get(i)[0]) {
                        if (minList.get(i)[1] < nextPlace[1]) {
                            nextPlace = minList.get(i);
                        }
                    }
                }

                int ny = nextPlace[0];
                int nx = nextPlace[1];
                map[start[0]][start[1]] = 0;
                map[ny][nx] = 9;
                start[0] = ny;
                start[1] = nx;
                if(babySharkSize < 7) {
					babySharkEat++;
					if (babySharkEat == babySharkSize) {
						babySharkSize++;
						babySharkEat = 0;
					}
				}
                visit = new boolean[N][N];
                visit[start[0]][start[1]] = true;
                queue.clear();
                queue.add(nextPlace);
                time += distance + 1;
                distance = -1;

            }
        }

        System.out.println(time);

    }

}
