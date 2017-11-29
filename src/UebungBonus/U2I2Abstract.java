import java.util.*;
import org.w3c.dom.*;

public abstract class U2I2Abstract {
	protected final U2I2Config u2i2Config;
	protected final Document[] xmlUnivIS;
	protected final Document htmlI2;
	protected final Map<String, String> knownRooms = new HashMap<>();
	protected final Map<String, U2I2Config.Person> knownPersons = new HashMap<>();
	protected final List<List<List<List<U2I2Config.Lecture>>>> timeTableData = new ArrayList<>();

	public U2I2Abstract(U2I2Config u2i2Config, Document[] xmlUnivIS, Document htmlI2) {
		this.u2i2Config = u2i2Config;
		this.xmlUnivIS = xmlUnivIS;
		this.htmlI2 = htmlI2;
		filterParentLectures();
		collectKnownRooms();
		collectKnownPersons();
		collectTimetableData();
		generateTimeTableHTML(initializeHTML());
	}

	public final Map<String, String> getKnownRooms() {
		return knownRooms;
	}

	public final Map<String, U2I2Config.Person> getKnownPersons() {
		return knownPersons;
	}

	public final List<List<List<List<U2I2Config.Lecture>>>> getTimeTableData() {
		return timeTableData;
	}

	// ############################## Step 0: GENERAL HELPERS ##############################
	protected static final List<Element> getSubTags(Element parent, String name) {
		List<Element> result = new LinkedList<>();
		for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
			if (child instanceof Element && (name.equals(child.getNodeName()) || name.equals(child.getLocalName())))
				result.add((Element) child);
		}
		return result;
	}

	protected final Element createSubTag(Node parentNode, String tagName) {
		Element element = htmlI2.createElement(tagName);
		parentNode.appendChild(element);
		return element;
	}

	protected final void addText(Node parentNode, String text) {
		parentNode.appendChild(htmlI2.createTextNode(text));
	}

	private final Element initializeHTML() {
		Element html = createSubTag(htmlI2, "html");
		Element head = createSubTag(html, "head");
		for (String styleLinkString : new String[] { "https://www2.cs.fau.de/websys/css/websys.css", "https://www2.cs.fau.de/websys/css/menu_nojs.css", "https://www2.cs.fau.de/websys/css/print.css", "https://www2.cs.fau.de/aud/aud.css" }) {
			Element styleLink = createSubTag(head, "link");
			styleLink.setAttribute("href", styleLinkString);
			styleLink.setAttribute("rel", "stylesheet");
			styleLink.setAttribute("media", "screen");
			styleLink.setAttribute("type", "text/css");
		}
		Element divInsideBody = createSubTag(createSubTag(html, "body"), "div");
		divInsideBody.setAttribute("id", "content");
		Element caption = createSubTag(divInsideBody, "h2");
		addText(caption, "Termine & Ort:");
		return createSubTag(divInsideBody, "table");
	}

	private final void filterParentLectures() {
		for (int lId = 0; lId < xmlUnivIS.length; lId++) {
			Element univis = xmlUnivIS[lId].getDocumentElement();
			Element parent = null;
			do {
				parent = null;
				List<Element> univis_lectures = getSubTags(univis, "Lecture");
				searchParent: for (Element univis_lecture : univis_lectures) {
					List<Element> univis_lecture_parentlvs = getSubTags(univis_lecture, "parent-lv");
					for (Element univis_lecture_parentlv : univis_lecture_parentlvs) {
						List<Element> univis_lecture_parent_univisrefs = getSubTags(univis_lecture_parentlv, "UnivISRef");
						for (Element univis_lecture_parent_univisref : univis_lecture_parent_univisrefs) {
							parent = univis_lecture_parent_univisref;
							univis_lecture_parentlv.removeChild(parent);
							break searchParent;
						}
					}
				}
				if (parent != null) {
					univis_lectures = getSubTags(univis, "Lecture");
					for (Element univis_lecture : univis_lectures) {
						if (parent.getAttribute("key").equals(univis_lecture.getAttribute("key"))) {
							univis.removeChild(univis_lecture);
						}
					}
				}
			} while (parent != null);
		}
	}

	// ############################## Step 1: collect known rooms ##############################
	protected abstract void collectKnownRooms();

	// ############################## Step 2: collect known persons ##############################
	protected abstract void collectKnownPersons();

	// ############################## Step 3: collect lectures ##############################
	protected final void collectTimetableData() {
		for (int time = 0; time < u2i2Config.getTimeLabels().length; time++) {
			List<List<List<U2I2Config.Lecture>>> row = new ArrayList<>();
			timeTableData.add(row);
			for (int day = 0; day < u2i2Config.getDayLabels().length; day++) {
				List<List<U2I2Config.Lecture>> cell = new ArrayList<>();
				row.add(cell);
				for (int lType = 0; lType < xmlUnivIS.length; lType++) {
					List<U2I2Config.Lecture> lectureSubCell = new ArrayList<>();
					cell.add(lectureSubCell);
				}
			}
		}
		for (int lId = 0; lId < xmlUnivIS.length; lId++) {
			Element univis = xmlUnivIS[lId].getDocumentElement();
			List<Element> univis_lectures = getSubTags(univis, "Lecture");
			for (Element univis_lecture : univis_lectures) {
				U2I2Config.Person lTutor = null;
				List<Element> univis_lecture_dozss = getSubTags(univis_lecture, "dozs");
				for (Element univis_lecture_dozs : univis_lecture_dozss) {
					List<Element> univis_lecture_dozs_dozs = getSubTags(univis_lecture_dozs, "doz");
					for (Element univis_lecture_dozs_doz : univis_lecture_dozs_dozs) {
						List<Element> univis_lecture_dozs_doz_univisrefs = getSubTags(univis_lecture_dozs_doz, "UnivISRef");
						for (Element univis_lecture_dozs_doz_univisref : univis_lecture_dozs_doz_univisrefs) {
							if ("Person".equals(univis_lecture_dozs_doz_univisref.getAttribute("type"))) {
								lTutor = knownPersons.get(univis_lecture_dozs_doz_univisref.getAttribute("key"));
							}
						}
					}
				}
				String lUrl = null;
				List<Element> univis_lecture_urldescriptions = getSubTags(univis_lecture, "url_description");
				for (Element univis_lecture_urldescription : univis_lecture_urldescriptions) {
					lUrl = univis_lecture_urldescription.getTextContent();
				}
				List<Element> univis_lecture_termss = getSubTags(univis_lecture, "terms");
				for (Element univis_lecture_terms : univis_lecture_termss) {
					List<Element> univis_lecture_terms_terms = getSubTags(univis_lecture_terms, "term");
					for (Element univis_lecture_terms_term : univis_lecture_terms_terms) {
						String lDay = null, lStart = null, lRoom = null;
						List<Element> univis_lecture_terms_term_starttimes = getSubTags(univis_lecture_terms_term, "starttime");
						for (Element univis_lecture_terms_term_starttime : univis_lecture_terms_term_starttimes) {
							lStart = univis_lecture_terms_term_starttime.getTextContent();
							lStart = lStart.substring(0, lStart.indexOf(":")); // we need the hour only
						}
						List<Element> univis_lecture_terms_term_repeats = getSubTags(univis_lecture_terms_term, "repeat");
						for (Element univis_lecture_terms_term_repeat : univis_lecture_terms_term_repeats) {
							lDay = univis_lecture_terms_term_repeat.getTextContent();
							if (lDay.startsWith("w")) { // weekly lecture
								lDay = lDay.substring(lDay.length() - 1, lDay.length()); // we need the day of week only
							} else {
								lDay = null;
							}
						}
						List<Element> univis_lecture_terms_term_rooms = getSubTags(univis_lecture_terms_term, "room");
						for (Element univis_lecture_terms_term_room : univis_lecture_terms_term_rooms) {
							List<Element> univis_lecture_terms_term_room_univisrefs = getSubTags(univis_lecture_terms_term_room, "UnivISRef");
							for (Element univis_lecture_terms_term_room_univisref : univis_lecture_terms_term_room_univisrefs) {
								if ("Room".equals(univis_lecture_terms_term_room_univisref.getAttribute("type"))) {
									lRoom = knownRooms.get(univis_lecture_terms_term_room_univisref.getAttribute("key"));
								}
							}
						}
						lStart = (lStart == null || lStart.isEmpty()) ? null : lStart;
						lDay = (lDay == null || lDay.isEmpty()) ? null : lDay;
						lRoom = (lRoom == null || lRoom.isEmpty()) ? null : lRoom;
						lUrl = (lUrl == null || lUrl.isEmpty()) ? null : lUrl;
						if (lDay != null && lStart != null && lRoom != null) {
							int time = (Integer.parseInt(lStart) - u2i2Config.getFirstTime()) / 2;
							int day = Integer.parseInt(lDay) - 1 - u2i2Config.getFirstDay();
							if (!(0 <= time && time < u2i2Config.getTimeLabels().length && 0 <= day && day < u2i2Config.getDayLabels().length)) {
								System.err.println("Found lecture lying outside of valid timetable range - ignoring...");
							} else {
								U2I2Config.Lecture lecture = new U2I2Config.Lecture(lId, lRoom, lTutor, lUrl);
								timeTableData.get(time).get(day).get(lId).add(lecture);
							}
						}
					}
				}
			}
		}
	}

	// ################################## Step 4: generate time table ##############################
	protected abstract void generateTimeTableHTML(Element table);
}