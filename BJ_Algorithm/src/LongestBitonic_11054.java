import java.util.Arrays;
import java.util.Scanner;

public class LongestBitonic_11054 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n;i++) {
            arr[i] = sc.nextInt();
        }

        //LIS를 만들고 반대로도 만들어서 중간 지점을 찾는다?

        int[] lis = new int[n];
        int[] lds = new int[n];
        Arrays.fill(lis, 1);
        Arrays.fill(lds, 1);

        lis(arr,lis,n);
        lds(arr,lds,n);

        int sum = 0;

        for(int i=0; i<n; i++) {
            sum = Math.max(sum, lis[i] + lds[i]);
        }
        System.out.println(sum-1);
    }

    public static void lis(int[] arr, int[] lis, int n) {
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                if (arr[i] < arr[j]) {
                    if(lis[j] < lis[i] +1 ) {
                        lis[j] = lis[i] + 1;
                    }
                }
            }
        }
    }

    public static void lds(int[] arr, int[] lds, int n) {
        for(int i=n-1; i>=0; i--) {
            for(int j=i-1; j>=0; j--) {
                if (arr[i] < arr[j]) {
                    if(lds[j] < lds[i] +1 ) {
                        lds[j] = lds[i] + 1;
                    }
                }
            }
        }
    }
}
