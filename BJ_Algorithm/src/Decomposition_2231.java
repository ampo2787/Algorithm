import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String N = scanner.nextLine();
        int intN = Integer.parseInt(N);
        // N 의 가장 작은 생성자를 구해라.
        //이 때,  N 은 Decomposition 의 생성자다. 그러면, N 의 생성자 result를 구해라.
        ArrayList<Integer> list = new ArrayList<>();
        String temp;
        int tempSum = 0;

        for(int i=1; i<N.length(); i++) { //자릿수 별로 반복
            for(int j = (int) Math.pow(10, i); j < (int) Math.pow(10, i+1); j++) {
                temp = String.valueOf(j);
                tempSum = j;
                for(int k=0; k<temp.length(); k++) {
                    tempSum += Integer.parseInt(temp.substring(k,k+1));
                }
                if(tempSum == intN) {
                    list.add(j);
                }
            }
        }

        Collections.sort(list);

        if(list.isEmpty())
            System.out.println(0);
        else
            System.out.println(list.get(0));
    }
}
