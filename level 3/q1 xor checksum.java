public class Main {
    public static void main(String[] args){
        //debug
        //System.out.println("debug: " + solution(17,4));//2

        //test cases
        System.out.println(solution(0,3));//2
        System.out.println(solution(17,4));//14
    }

    /*
    17 18 19 20 /
    21 22 23 / 24
    25 26 / 27 28
    29 / 30 31 32
    which produces the checksum 17^18^19^20^21^22^23^25^26^29 == 14.
     */
    public static int solution(int start, int length){
        //init variables
        int x = start;

        //loop and xor
        for(int r=0; r<length; r++){//for every line
            for(int c=0; c<length-r; c++) {//for (every person in line) - (the number of lines checked so far)
                if(r+c != 0) {//if not the first one
                    //xor
                    x ^= ((r * length + c) + start);
                }
            }
        }

        return x;
    }
}
