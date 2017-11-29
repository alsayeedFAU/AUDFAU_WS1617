import java.util.Arrays;

public class ClosestPair {

	public static double[] closestPointHelper(ClosestPairHelper cph, double[] p, double[] pd, double[][] ps) {
		// TODO Auto-generated method stub
		cph.traceMe();
		if (ps == null || ps.length == 0) {
			return pd;
		}

		if (pd == null) {
			return closestPointHelper(cph, p, cph.appendDistance(ps[0], Double.MAX_VALUE), cph.skipFirstPoint(ps));
		}

		if (cph.distance(p, pd) >= cph.distance(p, ps[0]) && cph.distance(p, ps[0]) > 0) {
			return closestPointHelper(cph, p, cph.appendDistance(ps[0], cph.distance(p, ps[0])), cph.skipFirstPoint(ps));
		} else {
			return closestPointHelper(cph, p, pd, cph.skipFirstPoint(ps));
		}

	}

	public static double[] closestPoint(ClosestPairHelper cph, double[] p, double[][] ps) {
		// TODO Auto-generated method stub
		cph.traceMe();
		if( ps == null || ps.length == 0){
			System.out.println(1);
			return cph.appendDistance(p, 0);
		}else{
			return closestPointHelper(cph, p, null, ps);
		}
	}

	public static double[] closestPair(ClosestPairHelper cph, double[][] ps) {
		// TODO Auto-generated method stub
		cph.traceMe();
		
		if(ps == null || ps.length == 0){
			return cph.PPD_NO_RESULT;
		}
		double[] tmp = new double[5];
		double[] test = closestPoint(cph, ps[0], cph.skipFirstPoint(ps));

		tmp = cph.appendDistance(cph.appendPoint(ps[0], closestPoint(cph, ps[0], cph.skipFirstPoint(ps))), test[2]);
		
		if(ps.length == 1){
			return cph.appendDistance(cph.appendPoint(ps[0], ps[0]), 0);
		}
		
		double[] erg = closestPair(cph, cph.skipFirstPoint(ps));
		
		if(erg[4] < tmp[4] && erg[4] != 0 && tmp[4] != 0){
			return erg;
		}
		return tmp;
		
	}

}
