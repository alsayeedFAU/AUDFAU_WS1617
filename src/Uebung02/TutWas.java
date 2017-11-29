
public class TutWas {

	public static void main(String[] args) {
		long z = 4711_0815_666_42_999L, r;
		int a = 0, i = 0;
		
		do{
			for (r = 0; z > 0; i++){
				r += z%10;
				z /= 10;
				System.out.println("a:"+a+"|i:"+i+"|z:"+z+"|r:"+r+"|");
			}
			z = r;
			a++;
			i = 0;
		}while(z >= 10);

		System.out.println("Ende:  +a:"+a+"|i:"+i+"|z:"+z+"|r:"+r+"|");
	}

}


