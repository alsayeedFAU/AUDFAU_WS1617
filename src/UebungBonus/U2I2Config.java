public interface U2I2Config {
	public String[] getLectureNames();

	public int getFirstDay();

	public String[] getDayLabels();

	public int getFirstTime();

	public String[] getTimeLabels();

	public int getDayColSpan();

	public String[] getLectureStyles();

	public String getOutputFile();

	/****************************************************************************************************/

	public static final class Person {
		public final String pFirstName;
		public final String pLastName;

		Person(String pFirstName, String pLastName) {
			this.pFirstName = pFirstName;
			this.pLastName = pLastName;
		}
	}

	public static final class Lecture {
		public final int lId;
		public final String lRoom;
		public final Person lTutor;
		public final String lUrl;

		Lecture(int lId, String lRoom, Person lTutor, String lUrl) {
			this.lId = lId;
			this.lRoom = lRoom;
			this.lTutor = lTutor;
			this.lUrl = lUrl;
		}
	}

	/****************************************************************************************************/

	public String[] getInputFileNames();

	public byte[][] getInputBytes();

	public String[] getInputURLs();
}