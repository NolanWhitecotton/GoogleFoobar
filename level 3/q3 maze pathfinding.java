import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        int[][] t1 = {{0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 0, 0}, {1, 1, 1, 0}};
        System.out.println(solution(t1));

        int[][] t2 = {{0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}};
        System.out.println(solution(t2));

    }

    //0 represents free space
    //1 represents wall
    //2 represents where you have been already
    public static int solution(int[][] map) {
        return recur(map, 1, false, 0, 0);
    }

    //clones a 2d array
    public static int[][] deepClone(int[][] map){
        int[][] newmap = new int[map.length][map[0].length];

        for(int i=0;i<map.length;i++){
            newmap[i] = map[i].clone();
        }

        return newmap;
    }

    //determines the shortest way to get from (posR, posC) to (w-1, h-1)
    static int knownMin;
    public static int recur(int[][] maze, int depth, boolean brokenUsed, int posR, int posC){
        //set settings for current maze
        int w = maze.length, h = maze[0].length;
        if(posR ==0 && posC == 0){
            knownMin = Integer.MAX_VALUE;
        }

        //check that not moving oob
        if(posR < 0 || posR == w){
            return Integer.MAX_VALUE;
        }
        if(posC < 0 || posC == h){
            return Integer.MAX_VALUE;
        }

        //if its possible to be less moves
        if(depth==knownMin-1){//if is already over min
            return Integer.MAX_VALUE;
        }
        if(knownMin-depth < w-posR + h-posC){//if best case scenario can make it
            return Integer.MAX_VALUE;
        }

        //if maze is complete
        if((posR==(w-1)) && (posC==(h-1))){
            return depth;
        }

        //test all possible moves
        int minPath = Integer.MAX_VALUE;

        //test directions
        int[] newRs = {1,-1,0,0};
        int[] newCs = {0,0,1,-1};

        for(int i=0;i<4;i++){
            //bounds checking
            if(i==0 && posR==w-1) {continue;}
            if(i==1 && posR==0) {continue;}
            if(i==2 && posC==h-1) {continue;}
            if(i==3 && posC==0) {continue;}

            //get next move
            int newR = posR + newRs[i];
            int newC = posC + newCs[i];
            int goingTo = maze[newR][newC];
            int[][] newMaze = deepClone(maze);

            //set pos as visited
            newMaze[posR][posC] = 2;

            //recur if possible
            if (goingTo == 0) {//if block is free
                minPath = Math.min(recur(newMaze, depth + 1, brokenUsed    , newR, newC), minPath);
            } else if (goingTo == 1 && !brokenUsed) {//block is wall and breakable
                minPath = Math.min(recur(newMaze, depth + 1, true, newR, newC), minPath);
            }
        }

        //update knownMin and return
        knownMin = Math.min(minPath, knownMin);
        return minPath;
    }
}
