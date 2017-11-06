package ee.tkasekamp.ltlminer;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.deckfour.xes.model.XLog;
import org.processmining.plugins.ltlchecker.RuleModel;
import ee.tkasekamp.ltlminer.util.XLogReader;

public class LTLMinerStarter {
	// Here the properties file reading and starting LTLMiner
	// Read in XLog and after handle the output
	Properties config;
	LTLMiner miner;

	public LTLMinerStarter() throws IOException {
		config = loadProperties("config.properties");
		miner = new LTLMiner();
	}

	public LTLMinerStarter(Properties properties) {
		config = properties;
		miner = new LTLMiner();
	}

	public void mine() throws Exception {
		XLog log = readLogFile(config.getProperty("logPath"));
		ArrayList<RuleModel> output = miner.mine(log, config);
		String asd = config.getProperty("outputFormat");
		switch (asd) {
		case "console":
			printToConsole(output);
			break;
		case "text":
			saveToFile(output, config.getProperty("outputPath"));
			break;
		default:
			break;
		}
	}
	
	// Miner modificado por Wellison para manter o original!
	public HashMap<String,Double> mineBehaviour(XLog logBehaviour) throws Exception {
		Map<String,Double> mapaDeAnalise = new HashMap<>(); // Contém resultado da análise
		XLog log = logBehaviour; // Log de entrada enviado pelo Monitor
		
		
		ArrayList<RuleModel> output = miner.mine(log, config); // Faz a execução do método!
		String asd = config.getProperty("outputFormat"); // Recebe qual formato de saída deve ocorrer!
		
		switch (asd) {
		case "console":
			mapaDeAnalise = printToConsoleBehaviour(output);
			break;
		default:
			break;
		}
		return (HashMap<String, Double>) mapaDeAnalise;
	}
	
	// Modificado por Wellison - Mantendo o original
	private HashMap<String,Double> printToConsoleBehaviour(ArrayList<RuleModel> output) {
		Map<String,Double> mapaDeAnalise = new HashMap<>(); // Contém resultado da análise
		System.out.println("coverage\tLTLRule"); // Imprimi 
		for (RuleModel ruleModel : output) {
			mapaDeAnalise.put(ruleModel.getLtlRule(), ruleModel.getCoverage()); // Cria o meu mapa de resultados!
			
			System.out.println(ruleModel.getCoverage() + "\t"
					+ ruleModel.getLtlRule());
		}
		
		return (HashMap<String, Double>) mapaDeAnalise;
		
	}
	
	private Properties loadProperties(String propFileName) throws IOException {
		Properties prop = new Properties();
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}
		return prop;
	}

	private XLog readLogFile(String logPath) throws Exception {
		return XLogReader.openLog(logPath);
	}

	private void printToConsole(ArrayList<RuleModel> output) {
		System.out.println("coverage\tLTLRule");
		for (RuleModel ruleModel : output) {
			System.out.println(ruleModel.getCoverage() + "\t"
					+ ruleModel.getLtlRule());
		}
	}

	public void saveToFile(ArrayList<RuleModel> output, String outputPath) {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outputPath), "UTF-8"));
			for (RuleModel ruleModel : output) {
				out.write(ruleModel.getCoverage() + "\t"
						+ ruleModel.getLtlRule() + "\n");
			}
			out.close();
		} catch (IOException e) {
			System.err.println("Couldn't save to file: " + outputPath);
		}

	}
}
