package ee.tkasekamp.ltlminer;
import static org.junit.Assert.assertEquals;

import java.util.Vector;
import org.deckfour.xes.model.XLog;
import org.junit.Before;
import org.junit.Test;
import org.processmining.plugins.ltlchecker.LTLChecker;
import org.processmining.plugins.ltlchecker.model.LTLModel;

import ee.tkasekamp.ltlminer.util.XLogReader;

/**
 * Proof of concept tests.
 * 
 * @author Tonis Kasekamp
 *
 */
public class LTLCheckerTest {
	LTLModel model;
	XLog log;

	@Before
	public void setup() {
		model = new LTLModel();
		try {
			//String answ = Util.scanFile("tests/testfiles/standard.ltl");
			//model.setFile(answ);
			log = XLogReader.openLog("tests/testfiles/repairExample.xes");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void oneMatchTest() {
		LTLChecker checker = new LTLChecker();
		Vector rules = new Vector();
		rules.add("always_when_A_then_eventually_B");
		checker.setSelectedRules(rules);
		Object[] result = checker.analyse(log, model);
		XLog comLog = (XLog) result[1];
		XLog ncomLog = (XLog) result[2];
		assertEquals("Matches everything", 1104, comLog.size());
		assertEquals(0, ncomLog.size());
	}

	/*public void oneMatchTest() {
  LTLChecker checker = new LTLChecker();
  Vector rules = new Vector();
  rules.add("always_when_A_then_eventually_B");
  checker.setSelectedRules(rules);
  Object[] result = checker.analyse(log, model);
  XLog comLog = (XLog) result[1];
  XLog ncomLog = (XLog) result[2];
  assertEquals("Matches everything", 1104, comLog.size());
  assertEquals(0, ncomLog.size());
 }*/


	@Test
	public void noMatchTest() {
		LTLChecker checker = new LTLChecker();
		Vector rules = new Vector();
		rules.add("activity_A_is_done_by_person_P_and_Q");
		checker.setSelectedRules(rules);
		Object[] result = checker.analyse(log, model);
		XLog comLog = (XLog) result[1];
		XLog ncomLog = (XLog) result[2];
		assertEquals("No matches", 0, comLog.size());
		assertEquals(1104, ncomLog.size());
	}

}