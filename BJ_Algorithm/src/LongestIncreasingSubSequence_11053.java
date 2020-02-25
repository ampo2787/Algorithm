import java.util.Scanner;

public class LongestIncreasingSubSequence_11053 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int arr[] = new int[n + 1];
        int d[] = new int[n + 1];

        d[1] = 1;

        for (int i = 1; i <= n; i++)
            arr[i] = sc.nextInt();
        //1을 초기화, index 2 부터 예전 배열을 보며 체크.
        for (int i = 2; i <= n; i++) {
            //현재 보는 수 1로 초기화.
            d[i] = 1;
            for (int j = 1; j < i; j++) {
                //현재 수보다 작은 수가 있고 dp 값이 작거나 같다면 그 값 + 1, 같은 수일 경우 그 값으로 현재 값 갱신
                //dp 값이 큰 경우는 더 긴 순서가 있다는 뜻이므로 건들지 않음.
                if (arr[i] > arr[j] && d[i] <= d[j])
                    d[i] = d[j] + 1;
                else if (arr[i] == arr[j])
                    d[i] = d[j];
            }
        }

        int max = 0;
        //전체 dp 중 가장 큰 값 출력.
        for (int i = 1; i <= n; i++)
            max = Math.max(d[i], max);

        System.out.println(max);

        sc.close();
    }
}
