import java.util.Arrays;

public class LaengsteGemeinsameTeilfolge {
	static long[] tmp;

	static long[] lgt(long[] x, long[] y) {
		if (x.length == 0 || y.length == 0) {
			long[] ergebnis = Arrays.copyOf(tmp, tmp.length);
			tmp = null;
			return ergebnis;
		}

		if (x[0] == y[0]) {
			if (tmp == null) {
				tmp = new long[1];
				tmp[0] = x[0];
				//System.out.println("test");
				return lgt(Arrays.copyOfRange(x, 1, x.length), Arrays.copyOfRange(y, 1, y.length));
			} else {
				//System.out.println(Arrays.toString(tmp));
				tmp = Arrays.copyOf(tmp, tmp.length+1);
				//System.out.println(Arrays.toString(tmp));
				tmp[tmp.length-1] = x[0];
				if (x.length > 1 && x[0] == x[1]) {
					return lgt(Arrays.copyOfRange(x, 2, x.length), Arrays.copyOfRange(y, 1, y.length));
				}
				if (y.length > 1 && y[0] == y[1]) {
					return lgt(Arrays.copyOfRange(x, 1, x.length), Arrays.copyOfRange(y, 2, y.length));
				}
				return lgt(Arrays.copyOfRange(x, 1, x.length), Arrays.copyOfRange(y, 1, y.length));
			}
		} else if (y[0] == y[1]) {
			return lgt(x, Arrays.copyOfRange(y, 1, y.length));
		} else {
			return lgt(Arrays.copyOfRange(x, 1, x.length), y);
		}

	}
	
	static long[] lgt(long[][] x) {
		for(long[] z : x){
			if(z == null){
				long[] tmp = {};
				return tmp;
			}
		}
		long[]tmp1 = null;
		
		if(x.length>2){
			tmp1 = lgt(x[0], x[1]);
		for(int i = 2; i < x.length-2;i++){
			tmp1 = lgt(tmp1, x[i]);
		}
		System.out.println(Arrays.toString(tmp1));
		}else{
			return lgt(x[0], x[1]);
			
		}
		return tmp1;
	}

}
