package programers;

import java.util.Scanner;

public class exam {
	Scanner sc = new Scanner(System.in);
	String const = sc.nextLine();
	
	boolean solution(const char* s) {
	    bool answer = true;
	    
	    if(const.CharAt(0) == ')' || const.CharAt(const.length()-1) == '(')
	    {
	        answer = false;
	    }
	    
	    return answer;
	}

}
