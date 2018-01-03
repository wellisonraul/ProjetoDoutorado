package ee.tkasekamp.ltlminer;

import org.deckfour.xes.model.XLog;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.junit.Test;

import br.ufpe.behaviour.mapeamento.Mapeamento;

public class StarterTest {

	/*public HashMap<String, Double> testBehaviour(XLog logBehaviour) throws Exception {
		Map<String, Double> mapaDeAnalise = new HashMap<>(); // Mapa para conter resultado de análise!
		LTLMinerStarter starter = new LTLMinerStarter(getProps()); // Cria o objeto e gera as propriedades
		mapaDeAnalise = starter.mineBehaviour(logBehaviour); // O método de análise
		return  (HashMap<String, Double>) mapaDeAnalise;
	}*/
	
	@Test
	public void test() throws Exception {
		LTLMinerStarter starter = new LTLMinerStarter(getProps());
		starter.mine();
	}

	/*@Test
	public void test2() throws Exception {
		Properties props = getProps();
		props.setProperty("outputFormat", "text");
		props.setProperty("outputPath", "rules.txt");
		LTLMinerStarter starter = new LTLMinerStarter(props);
		starter.mine();
		assertTrue(new File("rules.txt").exists());
	}*/

	/*@Test
	public void test3() throws Exception {
		Properties props = getProps();
		String queries = "\"[](( (?x{A,B})  ->  <>(?y{E,A})))\"; \"<>(?x{C,sad})\"";
		props.setProperty("queries", queries);
		props.setProperty("considerEventTypes", "false");
		props.setProperty("minSupport", "0.0");
		LTLMinerStarter starter = new LTLMinerStarter(props);
		starter.mine();

	}*/

	private Properties getProps() {
		Properties props = new Properties();
		Mapeamento mapeamento = new Mapeamento();
		//props.setProperty("logPath","/home/wellisonraul/eclipse-workspace/Doutorado/activiti-behaviour/traceExecucao.xes");
		props.setProperty("logPath", "/home/wellisonraul/eclipse-workspace/Doutorado/LTLMiner-master/src/test/resources/eventlog.xes");
		props.setProperty("considerEventTypes", "true");
		props.setProperty("minSupport", "0.0");
		props.setProperty("outputFormat", "console");
		//String consulta = mapeamento.eventualmenteTodasAtividadesAcontecem();
		String consulta = "\" <>(?act1)\"";
		String query01 = "\"<>(?act1{ValorICMS})\"";
		String consulta2 = "\"( (<>(?act1{A}) /\\ <>(?act2{B}) ))\"";
		//String queries = "\" (<>(((?res1{"+service.getName()+":"+service.getOperation()+"})"+" /\\ (?act1{"+service.getTask()+"}))))\"";
		String teste = "\" (<>(((?res1{ValorAliquota:AliquotRate})\"";
		String queries = "\" (<>(((?res1{AliquotRate:toSayAliquot})"+" /\\ (?act1{ValorAliquota}))))\"";
		props.setProperty("queries", queries);

		return props;
	}

}
