import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SW_6109 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int tc=1; tc<=TC; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();

            int[][] map = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            map = Move(map, direction);

            sb.append("#" + tc + "\n");

            for(int i=0; i<N;i++) {
                for(int j=0; j<N; j++) {
                    sb.append(map[i][j] + " ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    public static int[][] Move(int[][] map, String direction) {
        int[][] result = new int[N][N];

        switch (direction) {
            case "up":
                upMove(map, result);
                break;
            case "down":
                downMove(map, result);
                break;
            case "left":
                leftMove(map, result);
                break;
            case "right":
                rightMove(map, result);
                break;
            default:
                break;
        }

        return result;
    }

    private static void rightMove(int[][] map, int[][] result) {
        Stack<Integer> stack = new Stack<>();
        //stack 사이즈가 0일때 -> 그냥 넣는다.
        //stack 사이즈가 0이 아닐때 -> 하나 꺼내서 나랑 비교 -> 같으면 두배를 넣는다.
        //다르면 둘 다 넣는다.
        boolean check = false;
        for(int i=0; i<N; i++) {
            for(int j=N-1; j>=0; j--) {
                if(map[i][j] != 0) {
                    if(stack.size() == 0 || check) {
                        stack.push(map[i][j]);
                        check = false;
                    }
                    else {
                        int popItem = stack.pop();

                        if(popItem == map[i][j]) {
                            stack.push(map[i][j] * 2);
                            check = true;
                        }
                        else {
                            stack.push(popItem);
                            stack.push(map[i][j]);
                        }
                    }
                }
            }

            check = false;
            int index = N - (stack.size());

            while(!stack.isEmpty()) {
                result[i][index++] = stack.pop();
            }
        }
    }

    private static void leftMove(int[][] map, int[][] result) {
        Stack<Integer> stack = new Stack<>();
        boolean check = false;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] != 0) {
                    if(stack.size() == 0 || check) {
                        stack.push(map[i][j]);
                        check = false;
                    }
                    else {
                        int popItem = stack.pop();

                        if(popItem == map[i][j]) {
                            stack.push(map[i][j] * 2);
                            check = true;
                        }
                        else {
                            stack.push(popItem);
                            stack.push(map[i][j]);
                        }
                    }
                }
            }
            check = false;

            int index = stack.size() - 1;

            while(!stack.isEmpty()) {
                result[i][index--] = stack.pop();
            }
        }
    }

    private static void downMove(int[][] map, int[][] result) {
        Stack<Integer> stack = new Stack<>();
        boolean check = false;
        for(int j=0; j<N; j++) {
            for(int i=N-1; i>=0; i--) {
                if(map[i][j] != 0) {
                    if(stack.size() == 0 || check) {
                        stack.push(map[i][j]);
                        check = false;
                    }
                    else {
                        int popItem = stack.pop();

                        if(popItem == map[i][j]) {
                            stack.push(map[i][j] * 2);
                            check = true;
                        }
                        else {
                            stack.push(popItem);
                            stack.push(map[i][j]);
                        }
                    }
                }
            }
            check = false;

            int index = N - stack.size();

            while(!stack.isEmpty()) {
                result[index++][j] = stack.pop();
            }
        }
    }

    private static void upMove(int[][] map, int[][] result) {
        Stack<Integer> stack = new Stack<>();
        boolean check = false;

        for(int j=0; j<N; j++) {
            for(int i=0; i<N; i++) {
                if(map[i][j] != 0) {
                    if(stack.size() == 0 || check) {
                        stack.push(map[i][j]);
                        check = false;
                    }
                    else {
                        int popItem = stack.pop();

                        if(popItem == map[i][j]) {
                            stack.push(map[i][j] * 2);
                            check = true;
                        }
                        else {
                            stack.push(popItem);
                            stack.push(map[i][j]);
                        }
                    }
                }
            }
            check = false;
            int index = stack.size() - 1;

            while(!stack.isEmpty()) {
                result[index--][j] = stack.pop();
            }
        }
    }
}
