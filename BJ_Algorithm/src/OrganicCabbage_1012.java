import java.util.*;

public class OrganicCabbage_1012 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int[] M = new int[T];
        int[] N = new int[T];
        int[] K = new int[T];
        ArrayList<int[][]> list = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            M[i] = scanner.nextInt();
            N[i] = scanner.nextInt();
            K[i] = scanner.nextInt();
            int[][] thisList = new int[M[i]+1][N[i]+1];

            for (int j = 0; j < K[i]; j++) {
                thisList[scanner.nextInt()][scanner.nextInt()] = 1;
            }

            list.add(thisList);
        }
        int[][] privious;
        int testCaseResult;
        for (int i = 0; i < T; i++) {
            privious = new int[M[i]][N[i]];
            testCaseResult = 0;
            for (int k = 0; k < K[i]; k++) {
                Queue<int[]> q = new LinkedList();
                for (int m = 0; m < M[i]; m++) {
                    for (int n = 0; n < N[i]; n++) {
                        if (list.get(i)[m][n] == 1 && privious[m][n] != 1) {
                            int[] temp = new int[2];
                            temp[0] = m;
                            temp[1] = n;
                            q.add(temp);
                            testCaseResult++;
                            privious[m][n] = 1;
                            break;
                        }
                    }
                    if (!q.isEmpty()) {
                        break;
                    }
                }

                while (!q.isEmpty()) {
                    ArrayList<int[]> thisList = new ArrayList<>();
                    while (!q.isEmpty()) {
                        thisList.add(q.poll());
                    }
                    //동,서,남,북 체크.
                    int x, y;
                    for (int a = 0; a < thisList.size(); a++) {
                        x = thisList.get(a)[0];
                        y = thisList.get(a)[1];
                        if (x != 0) {
                            if (privious[x-1][y] != 1 && list.get(i)[x-1][y] == 1) {
                                int[] temp = new int[2];
                                temp[0] = x-1; temp[1] = y;
                                privious[x-1][y] = 1;
                                q.add(temp);
                            }
                        }

                        if (x != M[i] - 1) {
                            if (privious[x+1][y] != 1 && list.get(i)[x+1][y] == 1) {
                                int[] temp = new int[2];
                                temp[0] = x+1; temp[1] = y;
                                privious[x+1][y] = 1;
                                q.add(temp);
                            }
                        }

                        if (y != 0) {
                            if (privious[x][y-1] != 1 && list.get(i)[x][y-1] == 1) {
                                int[] temp = new int[2];
                                temp[0] = x; temp[1] = y-1;
                                privious[x][y-1] = 1;
                                q.add(temp);
                            }
                        }

                        if (y != N[i] - 1) {
                            if (privious[x][y+1] != 1 && list.get(i)[x][y+1] == 1) {
                                int[] temp = new int[2];
                                temp[0] = x; temp[1] = y+1;
                                privious[x][y+1] = 1;
                                q.add(temp);
                            }
                        }

                    }
                }
            }
            System.out.println(testCaseResult);
        }
    }
}
