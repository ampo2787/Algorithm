import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MST_1197 {
    static int[][] edgeList;
    static int[] parent;
    static int V,E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new int[E][3];

        parent = new int[V + 1];

        for(int i=1; i<V+1; i++) {
            parent[i] = i;
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int[] temp = new int[3];
            temp[0] = Integer.parseInt(st.nextToken());
            temp[1] = Integer.parseInt(st.nextToken());
            temp[2] = Integer.parseInt(st.nextToken());
            edgeList[i] = temp;
        }

        Arrays.sort(edgeList, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                if(t1[2] < t2[2]) {
                    return -1;
                }
                else if(t1[2] == t2[2]) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
        });
        int result = 0;

        for(int i=0; i<edgeList.length; i++) {
            int a = edgeList[i][0];
            int b = edgeList[i][1];
            int c = edgeList[i][2];

            if(!isSameParent(a, b)) {
                union(a,b);
                result += c;
            }
        }

        System.out.println(result);
    }
    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);

        return x == y ? true : false;
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x!=y) {
            parent[y] = x;
        }
    }

    public static int find(int x) {
        if(parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}
