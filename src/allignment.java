public class allignment {
    private int[][] s;
    private String[][] backtrack;
    private int[][] blosumChart;
    private int price;
    private String word1;
    private String word2;
    private StringBuilder correctedW1;
    private StringBuilder correctedW2;

    public allignment(String first, String second){
        word1 = first;
        word2 = second;
        correctedW1 = new StringBuilder();
        correctedW2 = new StringBuilder();
        s = new int[first.length() + 1][second.length() + 1];
        backtrack = new String[first.length() + 1][second.length() + 1];
        String blossum = "4 0 -2 -1 -2 0 -2 -1 -1 -1 -1 -2 -1 -1 -1 1 0 0 -3 -2 * " +
                "0 9 -3 -4 -2 -3 -3 -1 -3 -1 -1 -3 -3 -3 -3 -1 -1 -1 -2 -2 * " +
                "-2 -3 6 2 -3 -1 -1 -3 -1 -4 -3 1 -1 0 -2 0 -1 -3 -4 -3 * " +
                "-1 -4 2 5 -3 -2 0 -3 1 -3 -2 0 -1 2 0 0 -1 -2 -3 -2 * " +
                "-2 -2 -3 -3 6 -3 -1 0 -3 0 0 -3 -4 -3 -3 -2 -2 -1 1 3 * " +
                "0 -3 -1 -2 -3 6 -2 -4 -2 -4 -3 0 -2 -2 -2 0 -2 -3 -2 -3 * " +
                "-2 -3 -1 0 -1 -2 8 -3 -1 -3 -2 1 -2 0 0 -1 -2 -3 -2 2 * " +
                "-1 -1 -3 -3 0 -4 -3 4 -3 2 1 -3 -3 -3 -3 -2 -1 3 -3 -1 * " +
                "-1 -3 -1 1 -3 -2 -1 -3 5 -2 -1 0 -1 1 2 0 -1 -2 -3 -2 * " +
                "-1 -1 -4 -3 0 -4 -3 2 -2 4 2 -3 -3 -2 -2 -2 -1 1 -2 -1 * " +
                "-1 -1 -3 -2 0 -3 -2 1 -1 2 5 -2 -2 0 -1 -1 -1 1 -1 -1 * " +
                "-2 -3 1 0 -3 0 1 -3 0 -3 -2 6 -2 0 0 1 0 -3 -4 -2 * " +
                "-1 -3 -1 -1 -4 -2 -2 -3 -1 -3 -2 -2 7 -1 -2 -1 -1 -2 -4 -3 * " +
                "-1 -3 0 2 -3 -2 0 -3 1 -2 0 0 -1 5 1 0 -1 -2 -2 -1 * " +
                "-1 -3 -2 0 -3 -2 0 -3 2 -2 -1 0 -2 1 5 -1 -1 -3 -3 -2 * " +
                "1 -1 0 0 -2 0 -1 -2 0 -2 -1 1 -1 0 -1 4 1 -2 -3 -2 * " +
                "0 -1 -1 -1 -2 -2 -2 -1 -1 -1 -1 0 -1 -1 -1 1 5 0 -2 -2 * " +
                "0 -1 -3 -2 -1 -3 -3 3 -2 1 1 -3 -2 -2 -3 -2 0 4 -3 -1 * " +
                "-3 -2 -4 -3 1 -2 -2 -3 -3 -2 -1 -4 -4 -2 -3 -3 -2 -3 11 2 * " +
                "-2 -2 -3 -2 3 -3 2 -1 -2 -1 -1 -2 -3 -1 -2 -2 -2 -1 2 7";
        String[] str = blossum.split(" ");
        blosumChart = new int[20][20];
        int col = 0;
        int row = 0;
        for(String s: str){
            if(s.equals("*")){
                row++;
                col = 0;
            }
            else {
                blosumChart[row][col] = Integer.parseInt(s);
                col++;
            }
        }
        /*for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                System.out.print(blosumChart[i][j] + "_");
            }
            System.out.println("");
        }*/
        price = 5;
        s[0][0] = 0;
        for(int i = 1; i <= first.length(); i++){
            s[i][0] = s[i - 1][0] - price;
            backtrack[i][0] = "|";
        }
        for(int j = 1; j <= second.length(); j++){
            s[0][j] = s[0][j - 1] - price;
            backtrack[0][j] = "-";
        }
        String alph = "ACDEFGHIKLMNPQRSTVWY";
        for(int i = 1; i <= first.length(); i++){
            for(int j = 1; j <= second.length(); j++){
                int diag = s[i - 1][j - 1] + blosumChart[alph.indexOf(first.charAt(i - 1))][alph.indexOf(second.charAt(j - 1))];
                int upp = s[i - 1][j] - price;
                int left = s[i][j - 1] - price;
                if(diag > upp && diag > left){
                    s[i][j] = diag;
                    backtrack[i][j] = "/";
                }
                else if(upp > diag && upp > left){
                    s[i][j] = upp;
                    backtrack[i][j] = "|";
                }
                else if(upp == diag && diag > left){
                    s[i][j] = upp;
                    backtrack[i][j] = "|";
                }
                else{
                    s[i][j] = left;
                    backtrack[i][j] = "-";
                }
            }
        }
    }

    public int getScore(){
        return s[word1.length()][word2.length()];
    }
    public String getCorrectedSet(){
        int i = word1.length();
        int j = word2.length();
        while(i >= 0 && j >= 0){
            if(backtrack[i][j] == null){
                break;
            }
            if(backtrack[i][j].equals("|")){
                correctedW2.append("-");
                correctedW1.append(word1.charAt(i - 1));
                i--;
            }
            else if(backtrack[i][j].equals("/")){
                correctedW1.append(word1.charAt(i - 1));
                correctedW2.append(word2.charAt(j - 1));
                i--;
                j--;
            }
            else{
                correctedW1.append("-");
                correctedW2.append(word2.charAt(j - 1));
                j--;
            }
        }
        correctedW2 = correctedW2.reverse();
        correctedW1 = correctedW1.reverse();
        return correctedW1.toString() + "\n" + correctedW2.toString();
    }
}