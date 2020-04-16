import java.util.Scanner;
import java.io.FileInputStream;

public class Jungol_2097 {
    static int N, M;
    static int min_time = 10000, time_cost = 0, tmp = 0, tmp2 = 0, min_path_cnt, path_cnt = 0;
    static int[] path, min_path, selectedRow, sortCol;
    static int[][] i_arr;

    public static void main(String args[]) throws Exception{

        // 2097 지하철
        Scanner scan = new Scanner(System.in);

        // 지하철역의 수
        N = scan.nextInt(); // Max 100, Min 3

        // 원하는 목적역의 번호
        M = scan.nextInt();

        i_arr = new int[N+1][N+1];
        path = new int[N+1];
        min_path = new int[N+1];
        selectedRow = new int[N+1];

        for (int i=1; i<=N; i++){
            for (int j=1; j<=N; j++){
                i_arr[i][j] = scan.nextInt();
            }
        }

        scan.close();

        // 최단 경로 탐색
        selectedRow[1] = 1;
        path_cnt++;
        path[path_cnt] = 1;
        searchePath(1);

        // 목적 역까지 가는데 걸리는 최소 시간과 최단경로
        System.out.printf("%d \n",min_time);

        for (int i=1; i<=min_path_cnt; i++){
            System.out.printf("%d ",min_path[i]);
        }

    }

    public static void searchePath(int row){

        int sort_cnt = 0;

        if (row == M){
            if (time_cost < min_time){
                min_time = time_cost;

                min_path_cnt = path_cnt;

                for (int i=1; i<=path_cnt; i++){
                    min_path[i] = path[i];
                }
            }

            return;
        }

        if (time_cost > min_time){
            return;
        }

        //
        path_cnt++;
        path[path_cnt] = M;
        time_cost = time_cost + i_arr[row][M];

        searchePath(M);

        path[path_cnt] = 0;
        path_cnt--;
        time_cost = time_cost - i_arr[row][M];

        //
        sort_cnt = sortRow(row);
        int[] sortingCol = new int[sort_cnt+1];

        tmp2 = 0;

        for (int i=1; i<=N; i++){

            if ((selectedRow[sortCol[i]] != 1) && (sortCol[i] != M)){
                sortingCol[tmp2+1] = sortCol[i];
                tmp2++;
            }

        }

        for (int i=1; i<=sort_cnt; i++){

            selectedRow[sortingCol[i]] = 1;
            path_cnt++;
            path[path_cnt] = sortingCol[i];
            time_cost = time_cost + i_arr[row][sortingCol[i]];
            searchePath(sortingCol[i]);

            selectedRow[sortingCol[i]] = 0;
            path[path_cnt] = 0;
            path_cnt--;
            time_cost = time_cost - i_arr[row][sortingCol[i]];

        }

    }

    public static int sortRow(int row){

        int cnt = 0;
        int[][] t_arr = new int[N+1][N+1];

        sortCol = new int[N+1];

        for (int i=1; i<=N; i++){
            sortCol[i] = i;
            t_arr[row][i] = i_arr[row][i];
        }

        for (int i=2; i<=N; i++){
            for (int j=i-1; j>0; j--){
                if (t_arr[row][j+1] < t_arr[row][j]){
                    tmp = t_arr[row][j+1];
                    t_arr[row][j+1] = t_arr[row][j];
                    t_arr[row][j] = tmp;

                    tmp = sortCol[j+1];
                    sortCol[j+1] = sortCol[j];
                    sortCol[j] = tmp;
                }
            }
        }

        for (int i=1; i<=N; i++){

            if ((selectedRow[sortCol[i]] != 1) && (sortCol[i] != M)){
                cnt++;
            }

        }

        return cnt;

    }

}