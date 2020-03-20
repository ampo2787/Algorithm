import java.util.*;

public class CityPartition_1647 {
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        // M 개 a - b 연결 비용 c.
        // 마을을 두 개로 분리. 연결된 집은 전부 연결.
        // 길을 최대한 없애고, 유지비 합을 최소로.
        // prim or kruskal 을 이용해 모든 그래프를 연결, 가장 가중치가 높은 길을 제외. 끗.

        int[][] inputList = new int[M][3];
        parent = new int[100001];

        for(int i=1; i<parent.length; i++) {
            parent[i] = i;
        }

        for(int i=0; i<M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int[] temp = {a,b,c};
            inputList[i] = temp;
        }

        Arrays.sort(inputList, new Comparator<int[]>() {
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
        ArrayList<Integer> lineCost = new ArrayList<>();

        for(int i=0; i<inputList.length; i++) {
            int[] item = inputList[i];
            if(!isSameHead(item[0], item[1])) {
                lineCost.add(item[2]);
                union(item[0], item[1]);
            }
        }


        Collections.sort(lineCost);

        int result = 0;

        for(int i=0; i<lineCost.size() - 1; i++) {
            result+=lineCost.get(i);
        }

        System.out.println(result);
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) {
            parent[y] = x;
        }
    }

    public static int find(int x) {
        if(parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static boolean isSameHead(int x, int y) {
        x = find(x);
        y = find(y);
        if(x==y) return true;
        else return false;
    }


}
