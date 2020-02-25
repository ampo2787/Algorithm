import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EssenceTriangle_1932 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        ArrayList<Integer>[] list = new ArrayList[N];
        ArrayList<Integer>[] dpList = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
            dpList[i] = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                list[i].add(scanner.nextInt());
            }
        }

        dpList[0].add(list[0].get(0));

        for (int i = 1; i < N; i++) {
            dpList[i].add(dpList[i-1].get(0) + list[i].get(0));
            for (int j = 1; j < i; j++) {
                if(dpList[i-1].get(j) > dpList[i-1].get(j-1)) {
                    dpList[i].add(dpList[i-1].get(j) + list[i].get(j));
                }
                else {
                    dpList[i].add(dpList[i-1].get(j-1) + list[i].get(j));
                }
            }
            dpList[i].add(dpList[i-1].get(dpList[i-1].size()-1) + list[i].get(list[i].size()-1));
        }

        Collections.sort(dpList[dpList.length-1]);
        System.out.println(dpList[dpList.length-1].get(dpList[dpList.length-1].size()-1));
    }
}
