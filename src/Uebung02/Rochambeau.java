
public class Rochambeau {

	static int[][] res = {{0, 1, -1, -1, 1},{-1,0,1,-1,1},{1,-1,0,1,-1},{1,1,-1,0,-1},{-1,-1,1,1,0}};
	
	static int check(String gestures) {
		if (gestures == null || gestures == "") {
			return -3;
		}
		if (gestures.length() % 2 == 1) {
			return -2;
		}
		for (int i = 0; i < gestures.length(); i++) {
			if (gestures.charAt(i) != 'A' && gestures.charAt(i) != 'B' && gestures.charAt(i) != 'C'
					&& gestures.charAt(i) != 'D' && gestures.charAt(i) != 'E') {
				return -1;
			}

		}
		return 0;
	}
	
	static int eval(char x, char y){
		return res[x-65][y-65];	
	}
	
	static int[] decide(String gestures){
		if(check(gestures)!=0){
			int[] tmp = {check(gestures)};
			return tmp;
		}
		int[] tmp = {0,0};
		for(int i = 0; i < gestures.length(); i+=2){
			if(eval(gestures.charAt(i), gestures.charAt(i+1)) > 0){
				tmp[0]+=1; 
			}else if(eval(gestures.charAt(i), gestures.charAt(i+1)) < 0){
				tmp[1]+=1; 
			}
		}
		return tmp;
	}
}
