package knight;

class Solution {
    static int SIZE = 8;
    static int[][] posmoves = {{1,2},{1,-2},{2,1},{2,-1},{-1,2},{-1,-2},{-2,1},{-2,-1}};//the ways the piece can move
        
    static int curmin = -1; //the current minimum number of moves 
    						//taken to solve, negative when none found
    
    public static int solution(int src, int dest) {
        //convert src to sx and xy 
        int sx = src%SIZE;
        int sy = (src/SIZE);
        
        //convert desdt to ex and ey
        int ex = dest%SIZE;
        int ey = (dest/SIZE);
        
        //call recur
        curmin = 5; //reset curmin since static
        recur(0, sx, sy, ex, ey);
        return curmin;
    }
    
    
    
    public static void recur(int depth, int sx, int sy, int ex, int ey){
        
        //check if at end
    	if(sx==ex && sy==ey) {
    		if(depth < curmin) {
            	curmin = depth;
//            	System.out.println("changing to " + depth);
    		}
    		return;
        }
        
        for(int i=0;i<posmoves.length;i++){
            if(depth+1<curmin) {//if not useless
            	//check if next pos is valid
            	int nx = sx+posmoves[i][0];
            	int ny = sy+posmoves[i][1];
            	if(nx>=0 && nx<SIZE && ny>=0 && ny<SIZE)
            		recur(depth+1, nx , ny, ex, ey);
            }
        }
    }
    
    public static void main(String[] args) {
    	System.out.println(solution(0,1));
    }
}