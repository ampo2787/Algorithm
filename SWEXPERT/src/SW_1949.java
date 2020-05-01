import java.io.*;
import java.util.*;

public class SW_1949{
    public static int[] di={-1,1,0,0};//상하좌우
    public static int[] dj={0,0,-1,1};
    public static int[][] map;
    public static boolean[][] visit;
    public static int N,K,max;

    static void dfs(int i,int j,int cnt,boolean use){
        max=Math.max(max, cnt);//가장 긴 등산로 갱신
        visit[i][j]=true;
        for(int d=0; d<4; d++) {
            int ni=i+di[d];
            int nj=j+dj[d];
            if(0<=ni&&ni<N && 0<=nj&&nj<N && !visit[ni][nj]) {
                if(map[ni][nj]<map[i][j]) {
                    dfs(ni,nj,cnt+1,use);
                }else{
                    if(!use) {
                        if(map[ni][nj]-K<map[i][j]) {
                            int tmp=map[ni][nj];
                            map[ni][nj]=map[i][j]-1;
                            dfs(ni,nj,cnt+1,true);
                            map[ni][nj]=tmp;
                        }
                    }
                }
            }
        }
        visit[i][j]=false;
    }
    public static void main(String args[]) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            N=Integer.parseInt(st.nextToken());//지도크기 3<=N<=8
            K=Integer.parseInt(st.nextToken());//공사가능깊이 1<=K<=5

            map=new int[N][N];
            visit=new boolean[N][N];

            int high=0;
            for(int i=0; i<N; i++) {
                st=new StringTokenizer(br.readLine()," ");
                for(int j=0; j<N; j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());//1<=지형의높이<=20
                    high=Math.max(high,map[i][j]);
                }
            }

            List<int[]> top=new ArrayList<int[]>();
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j]==high) top.add(new int[] {i,j});
                }
            }

            max=-1;
            for(int i=0; i<top.size(); i++) {
                int[] ij=top.get(i);
                dfs(ij[0],ij[1],1,false);
            }

            sb.append("#"+tc+" "+max+"\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}