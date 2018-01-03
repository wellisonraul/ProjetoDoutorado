package ee.tkasekamp.ltlminer;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Properties;
import org.deckfour.xes.model.XLog;


import org.junit.Test;

public class StarterTest {

	/*@Test
	public void test() throws Exception {
		LTLMinerStarter starter = new LTLMinerStarter(getProps());
		starter.mine();
	}

	@Test
	public void test2() throws Exception {
		Properties props = getProps();
		props.setProperty("outputFormat", "text");
		props.setProperty("outputPath", "rules.txt");
		LTLMinerStarter starter = new LTLMinerStarter(props);
		starter.mine();
		assertTrue(new File("rules.txt").exists());
	}

	@Test
	public void test3() throws Exception {
		Properties props = getProps();
		String queries = "\"[](( (?x{A,B})  ->  <>(?y{E,A})))\"; \"<>(?x{C,sad})\"";
		props.setProperty("queries", queries);
		props.setProperty("considerEventTypes", "false");
		props.setProperty("minSupport", "0.0");
		LTLMinerStarter starter = new LTLMinerStarter(props);
		starter.mine();

	}*/

	
	@Test
	public void test4() throws Exception {
		Properties props = getProps();
		//props.setProperty("logPath", "src/test/resources/eventlogteste.xes");
		props.setProperty("logPath", "src/test/resources/eventlog.xes");
		
		
		//String queries = "\"[](( (?act1{Pagamento}) -> <>(?act2{Entrega})))\";\"<>(?act2{Entrega})\";\"<>(?act2{EscolhaProduto})\"";
		//String queries = "\"[](( (?act1{A}) -> _O(?act2{B})))\"";
		
		//Fórmulas: 
		//String queries = "\"<>(( (?act1{A}) -> _O( ( ?act2{B} -> _O( ?act3{C} ) ) ) ) )\""; //permite que seja A, B, D, C; A, B, D; 
		String queries = "\"<>(( (?act1{A}) -> <>( ( ?act2{B} -> <>( ?act3{C} ) ) ) ) )\""; //permite que seja A, B, D, C; A, B, D;
		String queriesWellison = "\" (<>(((?res1{AliquotRate:toSayAliquot})"+" /\\ (?act1{ValorAliquota}))))\"";
		

		
		props.setProperty("queries", queriesWellison);
		LTLMinerStarter starter = new LTLMinerStarter(props);
	
		starter.mine();
	}
	
	private Properties getProps() {
		Properties props = new Properties();
		//props.setProperty("logPath", "src/test/resources/exercise1.xes");
		props.setProperty("considerEventTypes", "false");
		props.setProperty("minSupport", "0.0");
		props.setProperty("outputFormat", "console");
		//String queries = "\"[](( (?x)  ->  <>(?y)))\"; \"<>(?x)\"";
		//props.setProperty("queries", queries);

		return props;
	}

}
