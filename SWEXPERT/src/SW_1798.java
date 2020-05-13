import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_1798 {
    static int N, M;
    static int[][] moveTime;
    static int airport;
    static ArrayList<Integer> hotelList;
    static ArrayList<int[]> placeList;
    static int max;
    static ArrayList<Integer> resultList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            max = 0;
            resultList = new ArrayList<>();
            moveTime = new int[N + 1][N + 1];

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = i + 1; j < N + 1; j++) {
                    int thisTime = Integer.parseInt(st.nextToken());
                    moveTime[i][j] = thisTime;
                    moveTime[j][i] = thisTime;
                }
            }


            airport = 0;
            hotelList = new ArrayList<>();
            placeList = new ArrayList<>();

            for (int i = 1; i < N + 1; i++) {
                st = new StringTokenizer(br.readLine());

                char info = st.nextToken().charAt(0);

                if(info == 'A') {
                    airport = i;
                }

                else if(info == 'H') {
                    hotelList.add(i);
                }
                else if (info == 'P') {
                    int[] placeInfo = new int[3];
                    placeInfo[0] = i;
                    placeInfo[1] = Integer.parseInt(st.nextToken()); // 소요시간.
                    placeInfo[2] = Integer.parseInt(st.nextToken()); // 만족도.
                    placeList.add(placeInfo);
                }
            }
            // 여기까지 입력.
            // 가장 높은 만족도가 목표일 때.
            // 공항에서 출발해 M 일동안 이동한 경로와 만족도를 구해라.
            // 관광포인트 P 는 단 한번만 방문할 수 있다.
            // 하루 이동 + 놀이에 최대 9시간 ( 540분 ) 까지 쓸 수 있다. 그 후엔 호텔에 가야 함.
            // 호텔은 중복 가능하다.
            // 마지막 날도 9시간 쓰지만, 그 후에 공항에 가야 한다. 공항은 한 곳이다.
            // 모든 경로는 직접 연결되어 있다. 우회하지 않는다.

            // 첫날 : 공항 -> 관광 -> 호텔.
            // 중간 : 호텔 -> 관광 -> 호텔.
            // 마지막 : 호텔 -> 관광 -> 공항.

            dfs(0, 540, 0, new boolean[placeList.size()] ,new ArrayList<>(), airport);
            // 마지막 날 별도 처리.
            sb.append("#" + tc + " " + max);

            for(int i=0; i<resultList.size(); i++) {
                sb.append(" " + resultList.get(i));
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void dfs(int day, int remainTime, int good, boolean[] visit, ArrayList<Integer> moveList, int startIdx) {
        for(int i=0; i < placeList.size(); i++) {
            int[] thisPlace = placeList.get(i);
            // 0 : 위치. 1 : 소요시간. 2 : 만족도.
            if(!visit[i]) {
                // 이동 + 관광 가능.
                int thisTime = + thisPlace[1] + moveTime[thisPlace[0]][startIdx];
                if(remainTime >= thisTime) {
                    visit[i] = true;
                    moveList.add(thisPlace[0]);
                    dfs(day, remainTime - thisTime, good + thisPlace[2], visit, moveList, thisPlace[0]);
                    moveList.remove((Object) thisPlace[0]);
                    visit[i] = false;
                }
            }
        }

        // 관광을 더 갈 수 있는 곳이 없다.
        if(day == M - 1) {
            //마지막 날은 공항으로 가야 함.
            if(remainTime > moveTime[startIdx][airport]) {
                if(max < good) {
                    moveList.add(airport);
                    resultList.clear();
                    for (int i = 0; i < moveList.size(); i++) {
                        resultList.add(moveList.get(i));
                    }
                    max = good;
                    moveList.remove((Object) airport);
                }
            }
            return;
        }
        else {
            // 호텔을 찾는다.

            for (int i = 0; i < hotelList.size(); i++) {
                int thisHotel = hotelList.get(i);

                if(remainTime >= moveTime[startIdx][thisHotel]) {
                    moveList.add(thisHotel);
                    dfs(day + 1, 540, good, visit, moveList, thisHotel);
                    moveList.remove(moveList.size() - 1);
                }
            }
        }

        // 호텔도 갈 시간이 안되면, 잘못 온 것이므로 끝낸다.
    }
}
