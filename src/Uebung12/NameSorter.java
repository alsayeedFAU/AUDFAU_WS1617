import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NameSorter {

	/**
	 * Returns the character at position <code>pos</code> in <code>str</code>, if it
	 * exists. Otherwise, a character is returned so that <code>str</code> is sorted
	 * before other strings that have <code>str</code> as a prefix, but are longer.
	 */
	public static char getCharOrDefault(String str, int pos) {
		// TODO!
		if (str.length() > pos) {
			return str.charAt(pos);
		}
		return '@';
	}

	/**
	 * Performs one step of Bucket-Sort. Sorts every entry of <code>list</code> into
	 * one of 53 bucket (No character at position, A-Z and a-z), depending on the
	 * character at position <code>charPos</code> in either <code>name</code> or
	 * <code>surname</code> in * the entry (which one is used depends on the value
	 * of <code>useFirstName</code>).
	 */
	public static ArrayList<NameEntry> radixOneStep(ArrayList<NameEntry> list, boolean useFirstName, int charPos) {
		ArrayList<NameEntry>[] b = new ArrayList[54];
		for (int i = 0; i < 54; i++) {
			b[i] = new ArrayList();
		}
		if (useFirstName) {
			for (NameEntry e : list) {
				b[((int) getCharOrDefault(e.firstName, charPos) - 64)].add(e);
			}
		} else {
			for (NameEntry e : list) {
				b[((int) getCharOrDefault(e.surname, charPos) - 64)].add(e);
			}
		}
		ArrayList<NameEntry> tmp = new ArrayList<>();

		for (int i = 0; i < 53; i++) {
			tmp.addAll(b[i]);
		}
		return tmp;
	}

	/**
	 * Sorts the supplied list, first by name, then by surname
	 */
	public static ArrayList<NameEntry> sortEntries(ArrayList<NameEntry> in) {
		ArrayList<NameEntry> name = radixOneStep(in, false, 0);

		ArrayList<NameEntry> firstname = new ArrayList<>();
		for (int i = 0; i < name.size(); i++) {
			ArrayList<NameEntry> tmp = new ArrayList<>();
			if (!firstname.contains(name.get(i))) {
				tmp.add(name.get(i));
				for (int j = i + 1; j < name.size(); j++) {
					if (name.get(j).surname.equals(tmp.get(0).surname)) {
						tmp.add(name.remove(j));
					}
				}
				firstname.addAll(radixOneStep(tmp, true, 0));
			}
		}
		return firstname;
	}

}
