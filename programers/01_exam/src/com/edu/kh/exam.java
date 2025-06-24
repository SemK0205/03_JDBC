package com.edu.kh;

public class exam {
	
	public static void main(String[] args) {
		
		int k = 2;
		int d = 4;
		
		solution(k, d);
		
	}
	
	
	public static void solution(int k, int d) {
		int x;
		int y;
		int dis;
		int result = 0;
		
		for(int i = 0 ; i <= d ; i++){
	        for(int j = 0; j <= d ; j++){
	            x = (int)Math.pow(k*i,2);
	            y = (int) Math.pow(k*j,2);
	            dis = (int)Math.pow(d,2);
	            
	            if(x+y <= dis) result++;
	                
	        }
	    }
		
		
	}
	

}
