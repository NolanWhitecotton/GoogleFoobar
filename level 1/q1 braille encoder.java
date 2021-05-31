package braille;

public class Braille {
    public static String solution(String s) {
        String output = "";
    	for(int i=0;i<s.length();i++){
    	    output += getBraille(s.charAt(i));
    	}
    	return output;
    }
    
    public static String getBraille(char c){
    	if(c==' ') return "000000";
        String[] letters = {"100000","110000","100100","100110","100010","110100","110110","110010","010100","010110","101000","111000","101100","101110","101010","111100","111110","111010","011100","011110","101001","111001","010111","101101","101111","101011"};
        String capital = "000001";
        
        String output = "";
        
        if(c < 97){//if the letter is capital
            output += capital;
            c+=32;
        }
        c-=97;//adjust for ascii offset
        output += letters[c];
        return output;
    }
}