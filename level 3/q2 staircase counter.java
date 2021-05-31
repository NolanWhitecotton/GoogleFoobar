public class Main {
    public static void main(String[] args){
        System.out.println("debug: " + solution(3));
        System.out.println("debug: " + solution(4));
        System.out.println("debug: " + solution(5));
        System.out.println("debug: " + solution(200));
        //in:  3 4 5 200       ...
        //out: 1 1 2 487067745 ...
    }

    public static int numPossRec(int curHeight, int bricksLeft, int stepCount){
        //if staircase is done
        if(bricksLeft == 0){
            if(stepCount != 1) {//if is valid staircase
                return 1;
            }
            return 0;
        }

        //if there are not enough bricks to make a new stair
        if(curHeight+1 > bricksLeft){
            return 0;
        }

        //if only one stair can be added
        if(bricksLeft < curHeight*2 && bricksLeft > curHeight) {
            return 1;
        }

        //if an ambiguous amount of stairs can be added
        int count = 0;
        for(int numBricksToAdd=1; numBricksToAdd<=bricksLeft; numBricksToAdd++){//for every possible number of bricks to add
            int newTopStairHeight = curHeight+numBricksToAdd;
            int x = numPossRec(newTopStairHeight, bricksLeft - newTopStairHeight, stepCount + 1);
            count += x;
        }

        return count;
    }

    public static int solution(int n) {
        return numPossRec(0,n,0);
    }
}
