import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();

        String[] map = new String[N];
        int homeNumber = 0;

        for(int i=0; i<N;i++) {
            map[i] = scanner.nextLine();
            for(int j=0; j<N; j++) {
                if(map[i].charAt(j) == '1') {
                    homeNumber++;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        int[][] privious = new int[N][N];
        int priviousSize = 0;
        int mapNumber = 0;
        ArrayList<Integer> mapPerHome = new ArrayList<>();

        while(priviousSize != homeNumber) {
            for(int i=0; i<N;i++) {
                for(int j=0; j<N; j++) {
                   if(map[i].charAt(j) == '1') {
                       if(q.isEmpty()) {
                            int[] temp = {i, j};
                            if(privious[i][j] != 1) {
                                q.add(temp);
                                privious[i][j] = 1;
                                priviousSize++;
                              mapNumber++;
                           }
                       }
                    }
                }
            }
            int thisGroupHomeNumber = 1;

            while(!q.isEmpty()) {
                ArrayList<int[]> thisList = new ArrayList<>();

                while(!q.isEmpty()) {
                    thisList.add(q.poll());
                }

                for(int i=0; i<thisList.size(); i++) {
                    int x = thisList.get(i)[0];
                    int y = thisList.get(i)[1];

                    if(x != N-1) {
                        if(map[x+1].charAt(y) == '1') {
                          int[] next = {x+1, y};
                          if(privious[x+1][y] != 1) {
                              privious[x+1][y] = 1;
                              priviousSize++;
                              thisGroupHomeNumber++;
                              q.add(next);
                          }
                        }
                    }
                    if(x != 0) {
                        if(map[x-1].charAt(y) == '1') {
                            int[] next = {x-1, y};
                            if(privious[x-1][y] != 1) {
                                privious[x-1][y] = 1;
                                priviousSize++;
                                thisGroupHomeNumber++;
                                q.add(next);
                            }
                        }
                    }
                    if(y != 0) {
                        if(map[x].charAt(y-1) == '1') {
                            int[] next = {x, y-1};
                            if(privious[x][y-1] != 1) {
                                privious[x][y-1] = 1;
                                priviousSize++;
                                thisGroupHomeNumber++;
                                q.add(next);
                            }
                        }
                    }
                    if(y != N-1) {
                        if(map[x].charAt(y+1) == '1') {
                            int[] next = {x, y+1};
                            if(privious[x][y+1] != 1) {
                                privious[x][y+1] = 1;
                                priviousSize++;
                                thisGroupHomeNumber++;
                                q.add(next);
                            }
                        }
                    }

                }
            }
            mapPerHome.add(thisGroupHomeNumber);
        }
        Collections.sort(mapPerHome);
        System.out.println(mapNumber);
        for(int i=0; i<mapPerHome.size(); i++) {
            System.out.println(mapPerHome.get(i));
        }
    }
}
