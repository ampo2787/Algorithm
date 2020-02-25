import java.util.ArrayList;
import java.util.Scanner;

public class ChessReColor_1018 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt(); // 8 <= N,M <= 50 , 세로 N, 가로 M
        scanner.nextLine();
        ArrayList<String>  Board = new ArrayList<>();

        for(int i=0; i<N; i++) {
            Board.add(scanner.nextLine());
        }

        boolean ThisIsWhite = true;

        int min = Integer.MAX_VALUE;

        for(int a=0;a < N-7; a++) {
            for(int b=0; b < M-7; b++) { //모든 경우의 수
                int StartWhite = 0;
                int StartBlack = 0;
                for(int x=a; x<a+8; x++){
                    String line = Board.get(x);
                    for(int y=b; y<b+8; y++){ //8*8 검
                        if(line.charAt(y) == 'W'){ //흰색일 때.
                            if(ThisIsWhite){
                                StartBlack++;
                            }
                            else{
                                StartWhite++;
                            }
                        }
                        else { //검은색일 때
                            if(ThisIsWhite){
                                StartWhite++;
                            }
                            else{
                                StartBlack++;
                            }
                        }
                        ThisIsWhite = !ThisIsWhite;
                    }
                    ThisIsWhite = !ThisIsWhite;
                }

                if(StartWhite < min) {
                    min = StartWhite;
                }
                if(StartBlack < min) {
                    min = StartBlack;
                }
            }
        }
        System.out.println(min);
    }
}
