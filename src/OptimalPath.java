import java.util.HashMap;

public class OptimalPath {
    private int[][] down;
    private int[][] right;
    private int m;
    private int n;
    private int[][] map;
    public OptimalPath(int numN, int numM, int[][] downArr, int[][] rightArr){
        down = downArr;
        right = rightArr;
        m = numM;
        n = numN;
        map = new int[n + 1][m + 1];
    }
    public int getLengthOfPath(){
        map[0][0] = 0;
        for(int i = 1; i <= n;i++){
            map[i][0] = map[i-1][0] + down[i][0];
        }
        for(int j = 1; j <= m; j++){
            map[0][j] = map[0][j-1] + right[0][j];
        }
        for(int i = 1; i <= n;i++){
            for(int j = 1; j <= m; j++){
                map[i][j] = Integer.max(map[i-1][j] + down[i][j], map[i][j-1] + right[i][j]);
            }
        }
        return(map[n][m]);
    }
}
