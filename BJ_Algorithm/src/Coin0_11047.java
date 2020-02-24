import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); //1<=N<=10
        int K = scanner.nextInt(); //1<=K<=100,000,000

        int[] A = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = scanner.nextInt();
        }

        int middleNum=0;
        for(int i=A.length-1; i>=0; i--) {
            if(A[i] < K) {
                middleNum = i;
                break;
            }
        }
        int coinNumber = 0;
        int remainder = K;

        for(int i=middleNum; i>=0; i--) {
            coinNumber += remainder / A[i];
            remainder = remainder % A[i];
        }
        System.out.println(coinNumber);
    }
}
