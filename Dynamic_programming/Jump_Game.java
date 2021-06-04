package Dynamic_programming;

public class Jump_Game {
    public boolean canJump(int A[]){
        boolean[] can = new boolean[A.length];
        can[0] = true;
        for(int i = 1; i < A.length; ++i){
            can[i] = false;
            for(int j = 0; j < i; ++j){
                if(can[j] == true && A[j] >= i - j){
                    can[i] = true;
                    break;
                }
            }
        }
        return can[A.length - 1];
    }
}
