import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][] list;
	static boolean[] partList = new boolean[10];
	static ArrayList<int[]> emptyList;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		list = new int[9][9];
		emptyList = new ArrayList();
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				list[i][j] = sc.nextInt();
				if(list[i][j] == 0) {
					int[] temp = {i,j};
					emptyList.add(temp);
				}
			}
		}
		
		dfs(0);
	}
	
	public static void dfs(int index) {
		if(index == emptyList.size()) {
			for(int i=0; i<list.length; i++) {
				for(int j=0; j<list.length; j++) {
					sb.append(list[i][j] + " ");
				}
				sb.append("\n");
			}
			System.out.print(sb);
			System.exit(0);
		}
		else {
			int x = emptyList.get(index)[0];
			int y = emptyList.get(index)[1];
			
			for(int i=1; i<10; i++) {
				if(check(x,y,i) == true) {
					list[x][y] = i;
					dfs(index+1);
					list[x][y] = 0;
				}
			}
		}
	}
	
	public static boolean check(int x, int y, int index) {
		for(int i=0; i<9; i++) {
			if(index == list[x][i]) {
				return false;
			}
			if(index == list[i][y]) {
				return false;
			}
		}
		int tempX, tempY;
		if(x<3) {
			if(y<3) {
				tempX = 0;
				tempY = 0;
			}
			else if(y<6) {
				tempX = 0;
				tempY = 3;
			}
			else {
				tempX = 0;
				tempY = 6;
			}
		}
		else if(x<6) {
			if(y<3) {
				tempX = 3;
				tempY = 0;
			}
			else if(y<6) {
				tempX = 3;
				tempY = 3;
			}
			else {
				tempX = 3;
				tempY = 6;
			}
		}
		else {
			if(y<3) {
				tempX = 6;
				tempY = 0;
			}
			else if(y<6) {
				tempX = 6;
				tempY = 3;
			}
			else {
				tempX = 6;
				tempY = 6;
			}
		}
		for(int i=tempX; i<tempX+3; i++) {
			for(int j=tempY; j<tempY+3; j++) {
				if(index == list[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	

}
