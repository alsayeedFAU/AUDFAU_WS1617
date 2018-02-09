public class VeggieWahn {
 

	public static long essen(int v, int g) {
		long[][]speicher = new long[v+1][];
		for (int i = 0; i < speicher.length ; i ++) {
			speicher[i] = new long[g+1];
		}
		return 2*essenHelper(v,g,speicher);	
}
	
	public static long essenHelper(int v, int g, long[][] speicher){
		if (speicher[v][g]!= 0){
			return speicher[v][g];
		}
		
		long result;
		if (v==g){
			result = 1;
		}else if(g==1||v==1){
			result =1;
		}
		else{
			result = essenHelper(v-1, g-1, speicher)+v*essenHelper(v, g-1, speicher);
		}
		speicher[v][g] = result;
		return result;
		
}
}