import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_5653 {
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Cell>> map = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                ArrayList<Cell> column = new ArrayList<>();
                map.add(column);
                for(int j = 0; j<M; j++) {
                    Cell cell = new Cell();
                    cell.y = i;
                    cell.x = j;
                    cell.energy = Integer.parseInt(st.nextToken());
                    column.add(cell);
                }
            }

            for (int k = 0; k < K; k++) {
                ArrayList<ArrayList<Cell>> nextMap = new ArrayList<>();

                for (int i = 0; i < N; i++) {
                    nextMap.add((ArrayList<Cell>) map.get(i).clone());
                }

                for (int i = 0; i < N; i++) {
                    for(int j = 0; j<M; j++) {

                    }
                }
            }

            int result = 0;
            sb.append("#" + tc + " " + result + "\n");
        }
    }

    static class Cell {
        int y;
        int x;
        int energy;
        boolean activated=false;
        boolean dead=false;

        public Cell(){

        }
    }
}
