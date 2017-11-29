package AUD;

public class OKalkuel {

	public int[][] methA(int[][] img) {
		for (int i = 0; i < img.length; i++) {
			for (int j = 0; j < img[i].length; j++) {
				img[i][j] = j;
			}
		}
		return img;
	}

	public int methB(int n) {
		int r = 0; int tmp = 0;
		for (int i = 1; i * 2 * i < 8 * n; i++) {
			System.out.println("Test: " + i);
			tmp++;
			r = (int) (r * r / i);
			r++;
		}
		System.out.println(tmp);
		return r;
	}

	public int methC(int n) {
		int a = 1;
		int r = 1;
		
		int tmp = 0;
		int tmp1 = 0;
		
		for (int i = 0; i < n; i++) {
			a = a * 3;
			r = r * a;
			tmp++;
		}
		System.out.println(a);
		for (int i = 0; i <= a; i++) {
			r--;
			tmp1++;
		}
		System.out.println("Tmp: "+ tmp + "  Tmp1: "+ tmp1);
		
		return r;
	}

	public int methD(int n) {
		int r = 0;
		int tmp = 0;
		int tmp1 = 0;
		int tmp2 = 0;
		for (int i = 0; i < n; i++) {
			tmp++;
			for (int j = 0; j <= i; j++) {
				tmp1++;
				for (int k = 0; k <= j; k++) {
					tmp2++;
					r += i * j * k;
					System.out.println("Tmp: "+ tmp + "  Tmp1: "+ tmp1 + "  Tmp2: "+ tmp2 );
					return r;
				}
			}
		}
		System.out.println("Tmp: "+ tmp + "  Tmp1: "+ tmp1 + "  Tmp2: "+ tmp2 );
		return r;
	}

	public int methE(int n) {
		int r = 0;
		int tmp = 0;
		for (int i = 1; i < n; i++) {
			tmp++;
			r += n;
			n = n / 3;
		}
		System.out.println("Tmp: "+ tmp );
		return r;
	}

	public int methF(int n) {
		int r = 1;int tmp = 0;
		for (int i = 0; i < n; i++) {
			tmp++;
			r += methF(n - 1);
		}
		System.out.println("r: "+ r );
		return r;
	}

	public static void main(String[] args) {
		OKalkuel o = new OKalkuel();
		o.methF(2);
		o.methF(1);
		o.methF(10);
		
		
	}

}
