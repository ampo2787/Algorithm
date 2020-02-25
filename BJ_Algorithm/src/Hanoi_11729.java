import java.util.*;

public class Hanoi_11729 {
    public static int number = 0;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        moveHanoi(N, 1, 2, 3);
        sb.insert(0, number + "\n");
        System.out.println(sb);
    }

    public static void moveHanoi(int num, int start, int by, int end) {
        number++;
        if(num == 1) {
            sb.append(start + " " + end + "\n");
        } else {
            // STEP 1 : num-1개를 A에서 B로 이동
            moveHanoi(num-1, start, end, by);
            // STEP 2 : 1개를 A에서 C로 이동
            sb.append(start + " " + end + "\n");
            // STEP 3 : num-1개를 B에서 C로 이동
            moveHanoi(num-1, by, start, end);
        }
    }

}
