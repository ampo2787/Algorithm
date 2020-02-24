import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int computerNum = scanner.nextInt();
        int networkNum = scanner.nextInt();

        int computer1, computer2;
        int[][] network = new int[computerNum+1][computerNum+1];

        for(int i=0; i<networkNum; i++) {
            computer1 = scanner.nextInt();
            computer2 = scanner.nextInt();
            network[computer1][computer2] = 1;
            network[computer2][computer1] = 1;
        }

        int[] previous = new int[computerNum+1];
        Queue<Integer> q = new LinkedList();
        q.add(1);
        previous[1] = 1;
        ArrayList<Integer> thisList;

        while(!q.isEmpty()) {
            thisList = new ArrayList<>();

            while(!q.isEmpty()) {
                int item = q.poll();
                thisList.add(item);
                previous[item] = 1;
            }

            for(int i=0; i<thisList.size(); i++) {
                int item = thisList.get(i);
                for(int j=1; j<=computerNum; j++) {
                    if(network[item][j] != 0 && previous[j] != 1) {
                        q.add(j);
                    }
                }
            }
        }
        int virusCom = 0;

        for(int i=2; i<=computerNum; i++) {
            if(previous[i] == 1){
                virusCom++;
            }
        }
        System.out.println(virusCom);
    }
}

