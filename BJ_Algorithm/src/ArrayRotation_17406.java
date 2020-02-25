import java.util.ArrayList;
import java.util.Scanner;

public class ArrayRotation_17406 {
    static int[][] A;
    static int N;
    static int M;
    static int K;

    static int[][] rcs;

    static ArrayList<int[]> permutation = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        int[][] A = new int[N][M];

        for(int i=0; i<N;i++) {
            for(int j=0; j<M; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        rcs = new int[K][];
        int[] permutationArr = new int[K];

        for(int i=0; i<K; i++) {
            int[] temp = new int[3];
            temp[0] = sc.nextInt();
            temp[1] = sc.nextInt();
            temp[2] = sc.nextInt();
            rcs[i] = temp;
            permutationArr[i] = i;
        }

        Permutation(0, permutationArr, new boolean[K]);
        int result = Integer.MAX_VALUE;
        for(int i=0; i<permutation.size(); i++) {
            for(int j=0; j<K; j++) {
                rotation(rcs[j][0], rcs[j][1], rcs[j][2]);
            }
            result = Math.min(result, ArrayValue());
        }
        System.out.println(result);
    }


    public static int ArrayValue() {
        int result = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            int sum =0;
            for(int j=0; j<M; j++) {
                result += A[i][j];
            }
            result = Math.min(sum, result);
        }
        return result;
    }

    public static void Permutation(int index,int[] arr, boolean[] visit) {
        if(index == K) {
            permutation.add(arr.clone());
            return;
        }

        for(int i=0; i<K; i++) {
            if(!visit[i]) {
                visit[i] = true;
                arr[index] = i;
                Permutation(index+1, arr, visit);
                visit[i] = false;
            }
        }
    }

    public static void rotation(int r, int c, int s) {
        for(int i = r-s; i < 2*s; i++) {

        }
    }
}
