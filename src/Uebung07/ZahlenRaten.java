public interface ZahlenRaten {
	public void starteNeuesSpiel(int min, int max);

	public void rate(String z) throws InterruptedException;
}