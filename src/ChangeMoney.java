import java.util.ArrayList;

public class ChangeMoney {
    private int[] minCoins;
    private int[] coins;
    private int total;
    public ChangeMoney(int max, int[] tempCoins){
        total = max;
        coins = tempCoins;
        minCoins = new int[max +1];
    }
    public int getMinCoins(){
        for(int i = 0; i < total; i++){
            minCoins[i] = 0;
        }
        for(int change = 1; change <= total; change++){
            minCoins[change] = Integer.MAX_VALUE;
            for(int i = 0; i < coins.length; i++){
                int coin = coins[i];
                if (coin <= change) {
                    if (1 + minCoins[change - coin] < minCoins[change]) {
                        minCoins[change] = 1 + minCoins[change - coin];
                    }
                } // end if
            } // end for
        } // end for
        return minCoins[total];
        }
}
