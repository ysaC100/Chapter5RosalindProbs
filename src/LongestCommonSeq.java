public class LongestCommonSeq {
    private int row;
    private int col;
    private int[][] arrNucleotides;
    private String[][] backtrack;
    private String str1;
    private String str2;

    public LongestCommonSeq(int v, int w, String arr1, String arr2){
        row = v;
        col = w;
        str1 = arr1;
        str2 = arr2;
        arrNucleotides = new int[arr1.length() + 1][arr2.length() + 1];
        backtrack = new String[arr1.length() + 1][arr2.length() + 1];
        for(int i = 0; i <= row; i++){
            arrNucleotides[i][0] = 0;
        }
        for(int j = 0; j <= col; j++){
            arrNucleotides[0][j] = 0;
        }
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= col; j++){
                int max = Math.max(arrNucleotides[i][j-1], arrNucleotides[i-1][j]);
                if(arr1.charAt(i - 1) == arr2.charAt(j - 1)){
                    max = Math.max(max, arrNucleotides[i-1][j-1] + 1);
                }
                arrNucleotides[i][j] = max;
                if(arrNucleotides[i][j] == arrNucleotides[i][j-1]){
                    backtrack[i][j] = "-"; //west
                }
                else if(arrNucleotides[i][j] == arrNucleotides[i - 1][j]){
                    backtrack[i][j] = "|"; //north
                }
                else if(arrNucleotides[i][j] == (arrNucleotides[i - 1][j - 1] + 1)){
                    backtrack[i][j] = "/";//northwest
                }
            }
        }
    }

    public String OutputLCS(String[][] back, String v, int i, int j){
        if(i == 0 || j== 0){
            return "";
        }
        if(back[i][j].equals("|")){
            OutputLCS(back, v, i - 1, j);
        }
        else if(back[i][j].equals("-")){
            OutputLCS(back, v, i, j - 1);
        }
        else {
            OutputLCS(back, v, i - 1, j - 1);
            System.out.print(v.charAt(i-1));
            return v.charAt(i - 1) + "";
        }
        return "";
    }
    public String[][] getBacktrack(){
        return backtrack;
    }
}
