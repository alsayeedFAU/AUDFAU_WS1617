import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class U2I2PublicTest extends U2I2_GeneralTests {
	@Test(timeout = 3333)
	public void test_Step1_ws1415() {
		initTests(new U2I2Config_AuD_WS1415());
		run_Step1_General("Room.nat.dma.zentr.h11", "H11");
		run_Step1_General("Room.tech.IMMD.zentr.021351", "02.135-113");
	}

	@Test(timeout = 3333)
	public void test_Step1_ws1617() {
		initTests(new U2I2Config_AuD_WS1617());
		run_Step1_General("Room.nat.dma.zentr.h11", "H11");
		run_Step1_General("Room.nat.dma.zentr.h12", "H12");
		run_Step1_General("Room.tech.IMMD.zentr.021351", "02.135-113");
		run_Step1_General("Room.tech.FT.FT-TM.srtm", "SR TM");
	}

	@Test(timeout = 3333)
	public void test_Step2_ws1415() {
		initTests(new U2I2Config_AuD_WS1415());
		run_Step2_General("Person.tech.IMMD.IMMD2.ostern", "Norbert", "Oster");
		run_Step2_General("Person.tech.IMMD.popsos.riehle", "Dirk", "Riehle");
	}

	@Test(timeout = 3333)
	public void test_Step2_ws1617() {
		initTests(new U2I2Config_AuD_WS1617());
		run_Step2_General("Person.tech.IMMD.IMMD2.ostern", "Norbert", "Oster");
		run_Step2_General("Person.tech.IMMD.c3sim.pflaum", "Christoph", "Pflaum");
	}

	@Test(timeout = 3333)
	public void test_Step3_ws1415() {
		initTests(new U2I2Config_AuD_WS1415());
		run_Step3_General('A', 4, 0, 1, 0, "H11");
		run_Step3_General('D', 1, 0, 1, 0, "H11");
	}

	@Test(timeout = 3333)
	public void test_Step3_ws1617() {
		initTests(new U2I2Config_AuD_WS1617());
		run_Step3_General('A', 5, 0, 2, 0, "H11");
		run_Step3_General('A', 5, 0, 2, 1, "H12");
		run_Step3_General('B', 3, 0, 2, 0, "H11");
		run_Step3_General('B', 3, 0, 2, 1, "H12");
	}

	@Test(timeout = 3333)
	public void test_Step4a() {
		initTests(new U2I2Config_AuD_WS1415());
		run_Step4a_General();
	}

	@Test(timeout = 3333)
	public void test_Step4b() {
		initTests(new U2I2Config_AuD_WS1415());
		run_Step4b_General();
	}

	@Test(timeout = 3333)
	public void test_Step4c() {
		initTests(new U2I2Config_AuD_WS1415());
		run_Step4c_General();
	}

	@Test(timeout = 3333)
	public void test_Step4d() {
		initTests(new U2I2Config_AuD_WS1415());
		run_Step4d_WS1415();
	}

	@Test(timeout = 3333)
	public void test_Step4e() {
		initTests(new U2I2Config_AuD_WS1415());
		run_Step4e_WS1415();
	}

	protected void run_Step4d_WS1415() {
		System.err.println("CHECKING YOUR TABLE: some of the content");
		Node tbody = null;
		try {
			tbody = htmlI2.getDocumentElement().getLastChild().getLastChild().getLastChild().getLastChild();
		} catch (Throwable t) {
		}
		assertNotNull("I couldn't find the table tbody, i.e. the most important part (!) here: ...</tbody></table></div></body></html>", tbody);
		assertEquals("I couldn't find the table tbody, i.e. the most important part (!) here: ...</tbody></table></div></body></html>", "TBODY", tbody.getNodeName().toUpperCase());
		Node mainLecMonday = null;
		try {
			mainLecMonday = tbody.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNextSibling();
		} catch (Throwable t) {
		}
		assertNotNull("I couldn't find the main lecture on monday.", mainLecMonday);
		assertEquals("Wrong type of node found in the main lecture on monday.", "TD", mainLecMonday.getNodeName().toUpperCase());
		assertEquals("Wrong number of children in <td> of the main lecture on monday.", 1, mainLecMonday.getChildNodes().getLength());
		assertEquals("Wrong number of attributes in <td> of the main lecture on monday.", 2, mainLecMonday.getAttributes().getLength());
		assertNotNull("Missing attribute in <td class=\"???\"> of the main lecture on monday.", mainLecMonday.getAttributes().getNamedItem("class"));
		assertEquals("Wrong attribute value in <td class=\"???\"> of the main lecture on monday.", u2i2Config.getLectureStyles()[0], mainLecMonday.getAttributes().getNamedItem("class").getNodeValue());
		assertNotNull("Missing attribute in <td colspan=\"???\"> of the main lecture on monday.", mainLecMonday.getAttributes().getNamedItem("colspan"));
		assertEquals("Wrong attribute value in <td class=\"???\"> of the main lecture on monday.", Integer.toString(u2i2Config.getDayColSpan()), mainLecMonday.getAttributes().getNamedItem("colspan").getNodeValue());
		Node mainLecMondayLink = mainLecMonday.getChildNodes().item(0);
		assertNotNull("I couldn't find the main lecture link on monday.", mainLecMondayLink);
		assertEquals("Wrong type of node found in the child of the main lecture on monday.", "A", mainLecMondayLink.getNodeName().toUpperCase());
		assertNotNull("I couldn't find the main lecture link on monday.", mainLecMondayLink.getTextContent());
		assertEquals("Wrong type of node found in the child of the main lecture on monday.", "H11", mainLecMondayLink.getTextContent());
		assertNotNull("Missing attribute in <a href=\"???\"> of the child of the main lecture on monday.", mainLecMondayLink.getAttributes().getNamedItem("href"));
		assertEquals("Wrong attribute value in <a href=\"???\"> of the child of the main lecture on monday.", "https://www2.cs.fau.de/aud", mainLecMondayLink.getAttributes().getNamedItem("href").getNodeValue());
	}

	public void run_Step4e_WS1415() {
		System.err.println("CHECKING YOUR TABLE: some of the content");
		Node tbody = null;
		try {
			tbody = htmlI2.getDocumentElement().getLastChild().getLastChild().getLastChild().getLastChild();
		} catch (Throwable t) {
		}
		assertNotNull("I couldn't find the table tbody, i.e. the most important part (!) here: ...</tbody></table></div></body></html>", tbody);
		assertEquals("I couldn't find the table tbody, i.e. the most important part (!) here: ...</tbody></table></div></body></html>", "TBODY", tbody.getNodeName().toUpperCase());
		Node cellC3R = null;
		try {
			cellC3R = tbody.getFirstChild().getNextSibling().getNextSibling().getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
		} catch (Throwable t) {
		}
		assertNotNull("I couldn't find the RUes in C3R.", cellC3R);
		assertEquals("Wrong type of node found in the RUes in C3R.", "TD", cellC3R.getNodeName().toUpperCase());
		assertEquals("Wrong number of children in <td> of the RUes in C3R.", 3, cellC3R.getChildNodes().getLength());
		assertEquals("Wrong number of attributes in <td> of the RUes in C3R.", 2, cellC3R.getAttributes().getLength());
		assertNotNull("Missing attribute in <td class=\"???\"> of the RUes in C3R.", cellC3R.getAttributes().getNamedItem("class"));
		assertEquals("Wrong attribute value in <td class=\"???\"> of the RUes in C3R.", u2i2Config.getLectureStyles()[2], cellC3R.getAttributes().getNamedItem("class").getNodeValue());
		assertNotNull("Missing attribute in <td colspan=\"???\"> of the RUes in C3R.", cellC3R.getAttributes().getNamedItem("colspan"));
		assertEquals("Wrong attribute value in <td class=\"???\"> of the RUes in C3R.", Integer.toString(u2i2Config.getDayColSpan() / 2), cellC3R.getAttributes().getNamedItem("colspan").getNodeValue());
		Node cellC3R_uebna = cellC3R.getChildNodes().item(0);
		assertNotNull("I couldn't find the first child of the RUes in C3R.", cellC3R_uebna);
		assertEquals("Wrong type of node found in the first child of the RUes in C3R.", "SPAN", cellC3R_uebna.getNodeName().toUpperCase());
		assertNotNull("I couldn't find the room of the first child of the RUes in C3R.", cellC3R_uebna.getTextContent());
		assertEquals("Wrong node value found in the first child of the RUes in C3R.", "02.135-113", cellC3R_uebna.getTextContent());
		assertNotNull("Missing attribute in <span class=\"???\"> of the first child of the RUes in C3R.", cellC3R_uebna.getAttributes().getNamedItem("class"));
		assertEquals("Wrong attribute value in <span class=\"???\"> of the first child of the RUes in C3R.", "uebnatext", cellC3R_uebna.getAttributes().getNamedItem("class").getNodeValue());
		Node cellC3R_br = cellC3R.getChildNodes().item(1);
		assertNotNull("I couldn't find the second child of the RUes in C3R.", cellC3R_br);
		assertEquals("Wrong type of node found in the second child of the RUes in C3R.", "BR", cellC3R_br.getNodeName().toUpperCase());
		Node cellC3R_rueb = cellC3R.getChildNodes().item(2);
		assertNotNull("I couldn't find the last child of the RUes in C3R.", cellC3R_rueb);
		assertNotNull("I couldn't find the room of the last child of the RUes in C3R.", cellC3R_rueb.getTextContent());
		assertEquals("Wrong node value found in the first last of the RUes in C3R.", "01.153-113", cellC3R_rueb.getTextContent());
	}
}

class U2I2_GeneralTests {
	// ================================================================================
	protected void run_Step1_General(String keyExpected, String nameExpected) {
		System.err.println("CHECKING ROOMS (at least some of them):");
		Map<String, String> rooms = u2i2.getKnownRooms();
		assertTrue("ROOMS: You didn't really do anything, did you?", rooms.keySet().size() > 0);
		boolean found = false;
		for (String key : rooms.keySet()) {
			if (keyExpected.equals(key)) {
				found = true;
				System.err.println("\t" + key + " => " + rooms.get(key));
				assertEquals("ROOMS: You f#*%ed up the rooms: ", nameExpected, rooms.get(key));
			}
		}
		assertTrue("ROOMS: You f#*%ed up the rooms: You didn't find \"" + nameExpected + "\".", found);
	}

	protected void run_Step2_General(String keyExpected, String firstNameExpected, String lastNameExpected) {
		System.err.println("CHECKING PERSONS (at least some of them):");
		Map<String, U2I2Config.Person> persons = u2i2.getKnownPersons();
		assertTrue("PERSONS: You didn't really do anything, did you?", persons.keySet().size() > 0);
		boolean found = false;
		for (String key : persons.keySet()) {
			if (keyExpected.equals(key)) {
				found = true;
				System.err.println("\t" + key + " => " + persons.get(key).pLastName + ", " + persons.get(key).pFirstName);
				assertEquals("PERSONS: You f#*%ed up the persons: ", firstNameExpected, persons.get(key).pFirstName);
				assertEquals("PERSONS: You f#*%ed up the persons: ", lastNameExpected, persons.get(key).pLastName);
			}
		}
		assertTrue("PERSONS: You f#*%ed up the persons: You didn't find \"" + firstNameExpected + " " + lastNameExpected + "\".", found);
	}

	protected void run_Step3_General(char day, int time, int type, int rows, int row, String roomName) {
		System.err.println("CHECKING LECTURES (at least some of them):");
		List<List<List<List<U2I2Config.Lecture>>>> lectures = u2i2.getTimeTableData();
		assertEquals("LECTURES: You didn't really do anything, did you?", u2i2Config.getTimeLabels().length, lectures.size());
		assertNotNull("LECTURES: You didn't really do anything, did you?", lectures.get(time - 1)); // row
		assertEquals("LECTURES: You didn't really do anything, did you?", u2i2Config.getDayLabels().length, lectures.get(time - 1).size());
		assertNotNull("LECTURES: You didn't really do anything, did you?", lectures.get(time - 1).get(day - 'A')); // cell
		assertEquals("LECTURES: You didn't really do anything, did you?", 4, lectures.get(time - 1).get(day - 'A').size()); // VL, TUEB, RUEB, IUEB
		assertNotNull("LECTURES: You didn't really do anything, did you?", lectures.get(time - 1).get(day - 'A').get(type)); // sub-cell
		assertEquals("LECTURES: You didn't really do anything, did you?", rows, lectures.get(time - 1).get(day - 'A').get(type).size());
		assertEquals("LECTURES: You didn't really do anything, did you?", 0, lectures.get(time - 1).get(day - 'A').get(type).get(type).lId); // row in sub-cell
		assertEquals("LECTURES: You didn't really do anything, did you?", roomName, lectures.get(time - 1).get(day - 'A').get(type).get(row).lRoom);
		U2I2Config.Lecture audVL = lectures.get(time - 1).get(day - 'A').get(type).get(row);
		System.err.println("\t" + audVL.lId + " - [" + audVL.lTutor.pLastName + ", " + audVL.lTutor.pFirstName + "] - " + audVL.lRoom + " - " + audVL.lUrl);
	}

	protected void run_Step4a_General() {
		System.err.println("CHECKING YOUR HTML: HEAD and BODY but outside TABLE");
		Node html = htmlI2.getDocumentElement();
		assertNotNull("You didn't really do anything, did you?", html);
		assertEquals("What the hell is in that document? Should contain <html>...", "HTML", html.getNodeName().toUpperCase());
		Node head = html.getFirstChild();
		assertNotNull("What the hell is in that document? Should contain <html><head>...", head);
		assertEquals("What the hell is in that document? Should contain <html><head>...", "HEAD", head.getNodeName().toUpperCase());
		Node body = html.getLastChild();
		assertNotNull("What the hell is in that document? Should contain ...</body></html>", body);
		assertEquals("What the hell is in that document? Should contain ...</body></html>", "BODY", body.getNodeName().toUpperCase());
		assertSame("What the hell is in that document? Found something strange here: ...</head>???<body>...", head.getNextSibling(), body);
		Node div = body.getFirstChild();
		assertNotNull("What the hell is in that document? Should contain ...<body><div id=\"content\">...", div);
		assertEquals("What the hell is in that document? Should contain ...<body><div id=\"content\">...", "DIV", div.getNodeName().toUpperCase());
		assertSame("What the hell is in that document? Should contain ...<body><div id=\"content\">...</div></body>...", body.getFirstChild(), body.getLastChild());
		assertNotNull("What the hell is in that document? Should contain ...<div id=\"content\">...", div.getAttributes().getNamedItem("id"));
		assertEquals("What the hell is in that document? Should contain ...<div id=\"content\">...", "content", div.getAttributes().getNamedItem("id").getNodeValue());
		Node h2 = div.getFirstChild();
		assertNotNull("What the hell is in that document? Should contain ...<div id=...><h2>...", h2);
		assertEquals("What the hell is in that document? Should contain ...<div id=...><h2>...", "H2", h2.getNodeName().toUpperCase());
		assertNotNull("What the hell is in that document? Should contain ...<h2>Termine & Ort:</h2>...", h2.getFirstChild());
		assertSame("What the hell is in that document? Should contain ...<h2>Termine & Ort:</h2>...", h2.getFirstChild(), h2.getLastChild());
		assertNotNull("What the hell is in that document? Should contain ...<h2>Termine & Ort:</h2>...", h2.getFirstChild().getNodeValue());
		assertEquals("What the hell is in that document? Should contain ...<h2>Termine & Ort:</h2>...", "Termine & Ort:", h2.getFirstChild().getNodeValue());
		Node table = div.getLastChild();
		assertNotNull("What the hell is in that document? Should contain ...</table></div>...", table);
		assertEquals("What the hell is in that document? Should contain ...</table></div>...", "TABLE", table.getNodeName().toUpperCase());
		assertSame("What the hell is in that document? Found something strange here: ...</h2>???<table>...", h2.getNextSibling(), table);
	}

	protected void run_Step4b_General() {
		System.err.println("CHECKING YOUR TABLE: THEAD");
		Node table = null;
		try {
			table = htmlI2.getDocumentElement().getLastChild().getLastChild().getLastChild();
		} catch (Throwable t) {
		}
		assertNotNull("I couldn't find the table at the end: ...</table></div></body></html>", table);
		assertEquals("I couldn't find the table at the end: ...</table></div></body></html>", "TABLE", table.getNodeName().toUpperCase());
		Node thead = table.getFirstChild();
		assertNotNull("What the hell is in that document? Should contain ...<table><thead>...", thead);
		assertEquals("What the hell is in that document? Should contain ...<table><thead>...", "THEAD", thead.getNodeName().toUpperCase());
		assertEquals("<thead> should contain exactly 1 <tr>", 1, thead.getChildNodes().getLength());
		Node tr = thead.getFirstChild();
		assertNotNull("What the hell is in that document? Should contain ...<table><thead><tr>...", tr);
		assertEquals("What the hell is in that document? Should contain ...<table><thead><tr>...", "TR", tr.getNodeName().toUpperCase());
		assertEquals("<thead><tr> should contain exactly " + (2 + u2i2Config.getDayLabels().length) + " <th>'s", 2 + u2i2Config.getDayLabels().length, tr.getChildNodes().getLength());
		for (int col = 0; col < tr.getChildNodes().getLength(); col++) {
			Node th = tr.getChildNodes().item(col);
			assertEquals("<thead><tr><???>: Wrong column type in colum " + col, "TH", th.getNodeName().toUpperCase());
			if (col == 0 || col == tr.getChildNodes().getLength() - 1) {
				assertEquals("<thead><tr><th ???>: Wrong number of attributes in <th> of colum " + col, 0, th.getAttributes().getLength());
				assertEquals("<thead><tr><th>???</th>: Wrong number of children in <th> of colum " + col, 1, th.getChildNodes().getLength());
				if (col == 0) {
					assertEquals("<thead><tr><th>: Wrong text in colum " + col, "Zeit\\Tag", th.getChildNodes().item(0).getNodeValue());
				} else {
					assertEquals("<thead><tr><th>: Wrong text in colum " + col, "Tag/Zeit", th.getChildNodes().item(0).getNodeValue());
				}
			} else {
				assertEquals("<thead><tr><th ???>: Wrong number of attributes in <th ...> of colum " + col, 2, th.getAttributes().getLength());
				assertNotNull("<thead><tr><th ???>: Missing attribute <th class=...> in <th ...> of colum " + col, th.getAttributes().getNamedItem("class"));
				assertEquals("<thead><tr><th class=\"???\"...>: Wrong attribute value in <th class=\"???\"...> of colum " + col, "day", th.getAttributes().getNamedItem("class").getNodeValue());
				assertNotNull("<thead><tr><th ???>: Missing attribute <th colspan=...> in <th ...> of colum " + col, th.getAttributes().getNamedItem("colspan"));
				assertEquals("<thead><tr><th colspan=\"???\"...>: Wrong attribute value in <th colspan=\"???\"...> of colum " + col, Integer.toString(u2i2Config.getDayColSpan()), th.getAttributes().getNamedItem("colspan").getNodeValue());
				assertEquals("<thead><tr><th ...>???</th>: Wrong number of children in <th ...> of colum " + col, 3, th.getChildNodes().getLength());
				assertEquals("<thead><tr><th ...>???<br/>...</th>: Wrong text in first line of <th> of colum " + col, u2i2Config.getDayLabels()[col - 1], th.getChildNodes().item(0).getNodeValue());
				assertEquals("<thead><tr><th ...>...<br/>...</th>: Missing <br> in <th ...> of colum " + col, "br", th.getChildNodes().item(1).getNodeName());
				assertEquals("<thead><tr><th ...>...<br/>???</th>: Wrong text in last line of <th ...> of colum " + col, "(" + (char) ('A' + (col - 1)) + ")", th.getChildNodes().item(2).getNodeValue());
			}
		}
	}

	protected void run_Step4c_General() {
		System.err.println("CHECKING YOUR TABLE: TBODY");
		Node table = null;
		try {
			table = htmlI2.getDocumentElement().getLastChild().getLastChild().getLastChild();
		} catch (Throwable t) {
		}
		assertNotNull("I couldn't find the table at the end: ...</table></div></body></html>", table);
		assertEquals("I couldn't find the table at the end: ...</table></div></body></html>", "TABLE", table.getNodeName().toUpperCase());
		Node tbody = table.getLastChild();
		assertNotNull("What the hell is in that document? Should contain ...</tbody></table>...", tbody);
		assertEquals("What the hell is in that document? Should contain ...</tbody></table>...", "TBODY", tbody.getNodeName().toUpperCase());
		Node thead = table.getFirstChild();
		assertNotNull("What the hell is in that document? Should contain ...<table><thead>...", thead);
		assertSame("What the hell is in that document? Found something strange here: ...</thead>???<tbody>...", thead.getNextSibling(), tbody);
		assertEquals("<tbody> should contain exactly " + (4 + u2i2Config.getTimeLabels().length) + " <tr>'s", 4 + u2i2Config.getTimeLabels().length, tbody.getChildNodes().getLength());
		NodeList rows = tbody.getChildNodes();
		for (int row = 0; row < rows.getLength(); row++) {
			Node tr = rows.item(row);
			assertNotNull("What the hell is in that document? Should contain ...<tbody>...???... in row " + row, tr);
			assertEquals("What the hell is in that document? Should contain ...<thead>...<tr>... in row " + row, "TR", tr.getNodeName().toUpperCase());
			if (row < rows.getLength() - 4) { // regular timetable row
				NodeList cols = tr.getChildNodes();
				for (int col = 0; col < cols.getLength(); col++) {
					if (col == 0 || col == cols.getLength() - 1) {
						Node th = cols.item(col);
						assertEquals("What the hell is in that regular timetable line, i.e. row " + row + " column " + col, "TH", th.getNodeName().toUpperCase());
						assertEquals("Wrong number of children in <th> of the regular timetable line, i.e. row " + row + " column " + col, 3, th.getChildNodes().getLength());
						assertEquals("Wrong text in first line of the <th> of the regular timetable line, i.e. row " + row + " column " + col, u2i2Config.getTimeLabels()[row], th.getChildNodes().item(0).getNodeValue());
						Node br = th.getChildNodes().item(1);
						assertEquals("What the hell is in that regular timetable line, i.e. row " + row + " column " + col, "BR", br.getNodeName().toUpperCase());
						assertEquals("Wrong text in last line of the <th> of the regular timetable line, i.e. row " + row + " column " + col, "(" + (row + 1) + ")", th.getChildNodes().item(2).getNodeValue());
						assertEquals("Wrong number of attributes in <th> of the regular timetable line, i.e. row " + row + " column " + col, 1, th.getAttributes().getLength());
						assertNotNull("Missing attribute in <th class=\"???\"> of the regular timetable line, i.e. row " + row + " column " + col, th.getAttributes().getNamedItem("class"));
						assertEquals("Wrong attribute value in <th class=\"???\"> of the regular timetable line, i.e. row " + row + " column " + col, "time", th.getAttributes().getNamedItem("class").getNodeValue());
					} else {
						Node td = cols.item(col);
						assertEquals("What the hell is in that regular timetable line, i.e. row " + row + " column " + col, "TD", td.getNodeName().toUpperCase());
						assertEquals("Wrong number of attributes in <td> of the regular timetable line, i.e. row " + row + " column " + col, 2, td.getAttributes().getLength());
						assertNotNull("Missing attribute in <td class=\"???\"> of the regular timetable line, i.e. row " + row + " column " + col, td.getAttributes().getNamedItem("class"));
						String possibleValues = ("noueb, " + Arrays.toString(u2i2Config.getLectureStyles())).toUpperCase();
						assertTrue("Wrong attribute value in <td class=\"???\"> of the regular timetable line, i.e. row " + row + " column " + col + ": " + td.getAttributes().getNamedItem("class").getNodeValue(), possibleValues.indexOf(td.getAttributes().getNamedItem("class").getNodeValue().toUpperCase()) >= 0);
						assertNotNull("Missing attribute in <td colspan=\"???\"> of the regular timetable line, i.e. row " + row + " column " + col, td.getAttributes().getNamedItem("colspan"));
					}
				}
			} else if (row == rows.getLength() - 4) { // footer
				assertEquals("Timetable footer should contain exactly " + (2 + u2i2Config.getDayLabels().length) + " <th>'s", 2 + u2i2Config.getDayLabels().length, tr.getChildNodes().getLength());
				for (int col = 0; col < tr.getChildNodes().getLength(); col++) {
					Node th = tr.getChildNodes().item(col);
					assertEquals("Timetable footer: Wrong column type in colum " + col, "TH", th.getNodeName().toUpperCase());
					if (col == 0 || col == tr.getChildNodes().getLength() - 1) {
						assertEquals("Timetable footer: Wrong number of attributes in <th> of colum " + col, 0, th.getAttributes().getLength());
						assertEquals("Timetable footer: Wrong number of children in <th> of colum " + col, 1, th.getChildNodes().getLength());
						if (col == 0) {
							assertEquals("Timetable footer: Wrong text in colum " + col, "Zeit/Tag", th.getChildNodes().item(0).getNodeValue());
						} else {
							assertEquals("Timetable footer: Wrong text in colum " + col, "Tag\\Zeit", th.getChildNodes().item(0).getNodeValue());
						}
					} else {
						assertEquals("Timetable footer: Wrong number of attributes in <th ...> of colum " + col, 2, th.getAttributes().getLength());
						assertNotNull("Timetable footer: Missing attribute <th class=...> in <th ...> of colum " + col, th.getAttributes().getNamedItem("class"));
						assertEquals("Timetable footer: Wrong attribute value in <th class=\"???\"...> of colum " + col, "day", th.getAttributes().getNamedItem("class").getNodeValue());
						assertNotNull("Timetable footer: Missing attribute <th colspan=...> in <th ...> of colum " + col, th.getAttributes().getNamedItem("colspan"));
						assertEquals("Timetable footer: Wrong attribute value in <th colspan=\"???\"...> of colum " + col, Integer.toString(u2i2Config.getDayColSpan()), th.getAttributes().getNamedItem("colspan").getNodeValue());
						assertEquals("Timetable footer: Wrong number of children in <th ...> of colum " + col, 3, th.getChildNodes().getLength());
						assertEquals("Timetable footer: Wrong text in first line of <th ...> of colum " + col, "(" + (char) ('A' + (col - 1)) + ")", th.getChildNodes().item(0).getNodeValue());
						assertEquals("Timetable footer: Missing <br> in <th ...> of colum " + col, "br", th.getChildNodes().item(1).getNodeName());
						assertEquals("Timetable footer: Wrong text in last line of <th> of colum " + col, u2i2Config.getDayLabels()[col - 1], th.getChildNodes().item(2).getNodeValue());
					}
				}
			} else if (row == rows.getLength() - 3) { // empty
				assertEquals("Wrong number of children in \"empty\"-line (between timetable and legend), i.e. row " + row, 1, tr.getChildNodes().getLength());
				Node th = tr.getChildNodes().item(0);
				assertEquals("What the hell is in that \"empty\"-line (between timetable and legend), i.e. row " + row, "TH", th.getNodeName().toUpperCase());
				assertEquals("Wrong number of children in <th> of the \"empty\"-line (between timetable and legend), i.e. row " + row, 0, th.getChildNodes().getLength());
				assertEquals("Wrong number of attributes in <th> of the \"empty\"-line (between timetable and legend), i.e. row " + row, 1, th.getAttributes().getLength());
				assertNotNull("Missing attribute in <th class=\"???\"> of the \"empty\"-line (between timetable and legend), i.e. row " + row, th.getAttributes().getNamedItem("class"));
				assertEquals("Wrong attribute value in <th class=\"???\"> of the \"empty\"-line (between timetable and legend), i.e. row " + row, "nr", th.getAttributes().getNamedItem("class").getNodeValue());
			} else if (row == rows.getLength() - 2) { // legend
				assertEquals("Wrong number of children in \"legend\"-line, i.e. row " + row, 1 + u2i2Config.getLectureNames().length, tr.getChildNodes().getLength());
				NodeList cols = tr.getChildNodes();
				String legendColSpan = Integer.toString(u2i2Config.getDayColSpan() * u2i2Config.getDayLabels().length / u2i2Config.getLectureNames().length);
				for (int col = 0; col < cols.getLength(); col++) {
					if (col == 0) {
						Node th = cols.item(col);
						assertEquals("What the hell is in that \"legend\"-line, i.e. row " + row + " column " + col, "TH", th.getNodeName().toUpperCase());
						assertEquals("Wrong number of children in <th> of the \"legend\"-line, i.e. row " + row + " column " + col, 1, th.getChildNodes().getLength());
						assertEquals("Wrong text in <th> of the \"legend\"-line, i.e. row " + row + " column " + col, "Legende", th.getChildNodes().item(0).getNodeValue());
						assertEquals("Wrong number of attributes in <th> of the \"legend\"-line, i.e. row " + row + " column " + col, 1, th.getAttributes().getLength());
						assertNotNull("Missing attribute in <th class=\"???\"> of the \"legend\"-line, i.e. row " + row + " column " + col, th.getAttributes().getNamedItem("class"));
						assertEquals("Wrong attribute value in <th class=\"???\"> of the \"legend\"-line, i.e. row " + row + " column " + col, "nr", th.getAttributes().getNamedItem("class").getNodeValue());
					} else {
						Node td = cols.item(col);
						assertEquals("What the hell is in that \"legend\"-line, i.e. row " + row + " column " + col, "TD", td.getNodeName().toUpperCase());
						assertEquals("Wrong number of children in <td> of the \"legend\"-line, i.e. row " + row + " column " + col, 1, td.getChildNodes().getLength());
						assertEquals("Wrong text in <td> of the \"legend\"-line, i.e. row " + row + " column " + col, u2i2Config.getLectureNames()[col - 1], td.getChildNodes().item(0).getNodeValue());
						assertEquals("Wrong number of attributes in <th> of the \"legend\"-line, i.e. row " + row + " column " + col, 2, td.getAttributes().getLength());
						assertNotNull("Missing attribute in <td class=\"???\"> of the \"legend\"-line, i.e. row " + row + " column " + col, td.getAttributes().getNamedItem("class"));
						assertEquals("Wrong attribute value in <td class=\"???\"> of the \"legend\"-line, i.e. row " + row + " column " + col, u2i2Config.getLectureStyles()[col - 1], td.getAttributes().getNamedItem("class").getNodeValue());
						assertNotNull("Missing attribute in <td colspan=\"???\"> of the \"legend\"-line, i.e. row " + row + " column " + col, td.getAttributes().getNamedItem("colspan"));
						assertEquals("Wrong attribute value in <td colspan=\"???\"> of the \"legend\"-line, i.e. row " + row + " column " + col, legendColSpan, td.getAttributes().getNamedItem("colspan").getNodeValue());
					}
				}
			} else if (row == rows.getLength() - 1) { // news
				assertEquals("Wrong number of children in \"news\"-line (after legend), i.e. row " + row, 2, tr.getChildNodes().getLength());
				Node th = tr.getChildNodes().item(0);
				assertEquals("What the hell is in that \"news\"-line (after legend), i.e. row " + row + " column 0", "TH", th.getNodeName().toUpperCase());
				assertEquals("Wrong number of children in <th> of the \"news\"-line (after legend), i.e. row " + row + " column 0", 0, th.getChildNodes().getLength());
				assertEquals("Wrong number of attributes in <th> of the \"news\"-line (after legend), i.e. row " + row + " column 0", 0, th.getAttributes().getLength());
				Node td = tr.getChildNodes().item(1);
				assertEquals("What the hell is in that \"news\"-line (after legend), i.e. row " + row + " column 1", "TD", td.getNodeName().toUpperCase());
				assertEquals("Wrong number of children in <td> of the \"news\"-line (after legend), i.e. row " + row + " column 1", 0, td.getChildNodes().getLength());
				assertEquals("Wrong number of attributes in <td> of the \"news\"-line (after legend), i.e. row " + row + " column 1", 1, td.getAttributes().getLength());
				assertNotNull("Missing attribute in <td colspan=\"???\"> of the \"news\"-line (after legend), i.e. row " + row + " column 1", td.getAttributes().getNamedItem("colspan"));
				assertEquals("Wrong attribute value in <td colspan=\"???\"> of the \"news\"-line (after legend), i.e. row " + row + " column 1", Integer.toString(u2i2Config.getDayColSpan() * u2i2Config.getDayLabels().length), td.getAttributes().getNamedItem("colspan").getNodeValue());
			}
		}
		System.err.println("Well, looks quite good so far...");
	}

	// #################### DO NOT TRY TO UNDERSTAND THE STUFF BELOW ################################################################################

	protected U2I2Config u2i2Config;
	protected U2I2Abstract u2i2;
	protected Document htmlI2;

	protected void initTests(U2I2Config u2i2Config) {
		System.err.println("#################### INITIALIZING TESTS ####################");
		// ===== INPUT =====
		System.err.println("########## Harvesting xml data for lectures ##########");
		this.u2i2Config = u2i2Config;
		String[] files = u2i2Config.getInputFileNames();
		byte[][] bytes = u2i2Config.getInputBytes();
		String[] urls = u2i2Config.getInputURLs();
		int lectureCount = Math.max(Math.max(files == null ? 0 : files.length, bytes == null ? 0 : bytes.length), urls == null ? 0 : urls.length);
		assertTrue("WTF? There are no sources of xml data to harvest from!", lectureCount > 0);
		Document[] xmlUnivIS = new Document[lectureCount];
		for (int sourceIndex = 0; sourceIndex < lectureCount; sourceIndex++) {
			System.err.println("***** LECTURE: " + sourceIndex);
			for (int sourceType = 0; xmlUnivIS[sourceIndex] == null && sourceType < 3; sourceType++) {
				try {
					StreamSource xmlStreamSource = null;
					switch (sourceType) {
					case 0:
						System.err.println("Trying to read xml from local file:");
						File file = new File(files[sourceIndex]);
						System.err.println("--> " + file.getAbsolutePath());
						xmlStreamSource = new StreamSource(file);
						break;
					case 1:
						System.err.println("Trying to read xml from internal stream:");
						xmlStreamSource = new StreamSource(new ByteArrayInputStream(bytes[sourceIndex]));
						break;
					case 2:
						System.err.println("Trying to read xml from remote url:");
						URL url = new URL(urls[sourceIndex]);
						System.err.println("--> " + url);
						xmlStreamSource = new StreamSource(url.openStream());
						break;
					}
					DOMResult xml = new DOMResult();
					TransformerFactory.newInstance().newTransformer().transform(xmlStreamSource, xml);
					xmlUnivIS[sourceIndex] = (Document) xml.getNode();
					System.err.println("==> SUCCEEDED");
				} catch (Throwable t) {
					System.err.println("==> FAILED");
				}
			}
			assertNotNull("Sorry, couldn't harvest required xml data for lecture " + sourceIndex, xmlUnivIS[sourceIndex]);
		}
		try {
			// ===== PUTPUT =====
			System.err.println("########## Preparing html document ##########");
			htmlI2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			System.err.println("==> SUCCEEDED");
			System.err.println("########## Processing xml-2-html with YOUR code... ##########");
			u2i2 = new U2I2(u2i2Config, xmlUnivIS, htmlI2);
			System.err.println("==> SUCC... uhm, well, we will see - at least we didn't die here...");
			// ===== OUTPUT =====
			System.err.println("########## Exporting YOUR html document (hopefully YOUR generated homepage) ##########");
			Transformer transformerWriter = TransformerFactory.newInstance().newTransformer();
			transformerWriter.setOutputProperty(OutputKeys.INDENT, "yes");
			transformerWriter.setOutputProperty(OutputKeys.METHOD, "html");
			transformerWriter.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			File outputLocalFile = new File(u2i2Config.getOutputFile());
			try {
				System.err.println("Trying to write your html to the local file: " + outputLocalFile.getAbsolutePath());
				transformerWriter.transform(new DOMSource(htmlI2), new StreamResult(outputLocalFile));
				System.err.println("==> SUCCEEDED");
			} catch (Throwable tOuter) {
				System.err.println("==> FAILED - Will now try to honk it to stdout somewhere along the way.");
				try {
					transformerWriter.transform(new DOMSource(htmlI2), new StreamResult(System.out));
					System.err.println("==> SUCCEEDED");
				} catch (Throwable tInner) {
					System.err.println("==> FAILED - But there is still a tiny (really tiny) chance, that I can check your $&1#...");
				}
			}
		} catch (Throwable t) {
			// ===== CAPUT =====
			System.err.println("Oops - something totally unexpected went terribly wrong:");
			t.printStackTrace(System.err);
		}
		assertNotNull("Oops - something totally unexpected went terribly wrong. Please check stderr output!", u2i2);
		System.err.println("#################### DONE INITIALIZING TESTS ####################");
	}
}
