import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        ArrayList<long[]> list = new ArrayList<>();

        long[] tempList;

        for(int i=0; i<N; i++) {
            tempList = new long[2];
            tempList[0] = scanner.nextLong();
            tempList[1] = scanner.nextLong();
            list.add(tempList);
        }

        Collections.sort(list, new Comparator<long[]>() {
            @Override
            public int compare(long[] t1, long[] t2) {
                if(t2[1] <= t1[1]) {
                    if(t2[1] == t1[1]) {
                        if(t2[0] < t1[0]) {
                            return 1;
                        }
                        else {
                            return -1;
                        }
                    }
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });

        int assignNum = 1;
        int lastTimeIndex = 0;
        long minLastTime = list.get(0)[1];

        for(int i=1; i<N; i++) {
            if(list.get(i)[1] >= minLastTime) {
                if(list.get(lastTimeIndex)[1] <= list.get(i)[0]) {
                        minLastTime = list.get(i)[1];
                        lastTimeIndex = i;
                        assignNum++;
                }
            }
        }
        System.out.println(assignNum);
    }

}

