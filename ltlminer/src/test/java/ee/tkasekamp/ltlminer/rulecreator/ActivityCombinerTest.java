package ee.tkasekamp.ltlminer.rulecreator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.deckfour.xes.model.XLog;
import org.junit.Before;
import org.junit.Test;
import org.processmining.plugins.ltlchecker.RuleModel;

import ee.tkasekamp.ltlminer.LTLMiner;
import ee.tkasekamp.ltlminer.util.XLogReader;

public class ActivityCombinerTest {
	private ActivityCombiner acCombiner;

	@Before
	public void setup() {
		acCombiner = new ActivityCombiner();
	}
	/*	@Test
	public void combineActivities() {
		String rule = "formula two( A: activity, B: activity) = {} \n"
				+ "( <>( event == A ) /\\ <>( event == B) );";

		ArrayList<String> parameters = new ArrayList<>(Arrays.asList("A", "B"));
		ArrayList<String> activities = new ArrayList<>(Arrays.asList("send", "received"));

		ArrayList<String> rules = acCombiner.combine(rule,activities, parameters);
		for(int i = 0; i<rules.size(); i++){
			System.out.println("rule "+ i +" "+rules.get(i));
		}

		assertEquals("Number of combinations", 2, rules.size());

	}
	 */
	@Test
	public void combineActivities2() throws Exception {
		String rule = "formula two( A: activity, B: activity) = {} \n"
				+ "( <>( (event == A /\\ eventtype == \"complete\") ));";
		/*String rule2 = "formula two( A: activity, B: activity) = {} \n"
				+ "( <>( (event == A /\\ /\\ eventtype == \"start\") ) /\\ <>( (event == B /\\ eventtype == \"complete\")) );";
*/
		ArrayList<String> parameters = new ArrayList<>(Arrays.asList("A", "B"));
		ArrayList<String> activities = new ArrayList<>(Arrays.asList("ReceiveInvoice", "PayInvoice","OrderGoods", "RecordTransaction"));
		ArrayList<String> ruleTemplates = new ArrayList<>(Arrays.asList(rule));

		ArrayList<String> rules = acCombiner.combine(ruleTemplates, activities, parameters);

		for (int i =0;i<rules.size();i++) {

			System.out.println(rules.get(i));
			

		}
		System.out.println(" Analisando......");
		XLog log = XLogReader.openLog("src/test/resources/orderGoodsLog.xes");
		LTLMiner miner = new LTLMiner();
		Object[] objList = miner.checkRules(log, rules);
		ArrayList<RuleModel> result = miner.filter(objList);
		
		System.out.println("tamanho do array "+result.size());
		//assertEquals("Number of combinations", 40, rules.size());

	}

	/*@Test
	public void specificReplacementsTest() {
		String rule = "formula two( A: activity, B: activity) = {} \n"
				+ "( <>( event == A ) /\\ <>( event == B) );";
		ArrayList<String> parameters = new ArrayList<>(Arrays.asList("A", "B"));
		ArrayList<String> activities = new ArrayList<>(Arrays.asList("A", "B",
				"C", "D", "E"));

		HashMap<String, String[]> replacements = new HashMap<>();
		replacements.put("A", new String[] { "C", "D", "A" });
		replacements.put("B", new String[] { "A", "B" });

		ArrayList<String> rules = acCombiner.combine(rule,
				activities, parameters, replacements);
		assertEquals("Number of combinations", 5, rules.size());

	}*/

}
