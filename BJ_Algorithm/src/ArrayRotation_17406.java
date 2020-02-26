import java.util.ArrayList;
import java.util.Scanner;

public class ArrayRotation_17406 {
    static int[][] A;
    static int[][] copy;
    static int N;
    static int M;
    static int K;

    static int[][] rcs;

    static ArrayList<int[]> permutation = new ArrayList<>();

    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        A = new int[N+1][M+1];
        copy = new int[N+1][M+1];

        for(int i=1; i<N+1;i++) {
            for(int j=1; j<M+1; j++) {
                A[i][j] = sc.nextInt();
                copy[i][j] =  A[i][j];
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

        result = Integer.MAX_VALUE;

        Permutation(0, permutationArr);


        System.out.println(result);
    }


    public static int ArrayValue() {
        int result = Integer.MAX_VALUE;

        for(int i=1; i<N+1; i++) {
            int sum =0;
            for(int j=1; j<M+1; j++) {
                sum += A[i][j];
            }
            result = Math.min(sum, result);
        }
        return result;
    }

    public static void Permutation(int index,int[] arr) {
        if(index == K) {
            for(int j=0; j<K; j++) {
                rotation(rcs[arr[j]][0] - rcs[arr[j]][2], rcs[arr[j]][1] - rcs[arr[j]][2], rcs[arr[j]][2] * 2);
            }
            result = Math.min(result, ArrayValue());

            for(int j=1; j<N+1; j++) {
                A[j] = copy[j].clone();
            }
            return;
        }

        for(int i=index; i<K; i++) {
            swap(arr,index, i);
            Permutation(index+1, arr);
            swap(arr,index, i);
        }
    }

    public static void swap(int[] arr, int index, int i) {
        int temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
    }

    public static void rotation(int y, int x, int length) {
        if(length <= 1) {
            return;
        }

        int[][] copy = new int[N+1][M+1];

        for(int i=1; i<N+1; i++) {
            copy[i] = A[i].clone();
        }

        //위.
        for(int i= x + length; i > x; i--) {
            copy[y][i] = A[y][i-1];
        }

        //아래.
        for(int i = x; i < x + length; i++) {
            copy[y+length][i] = A[y+length][i+1];
        }

        //오른쪽.
        for(int i=y+length; i > y; i--) {
            copy[i][x+length] = A[i-1][x+length];
        }

        //왼쪽.
        for(int i=y; i < y + length; i++) {
            copy[i][x] = A[i+1][x];
        }

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<M+1; j++) {
                System.out.print(copy[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        length = length - 2;

        for(int i=1; i<N+1; i++) {
            A[i] = copy[i].clone();
        }

        rotation(y+1,x+1,length);
    }
}
