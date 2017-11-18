package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.Vector;
import org.deckfour.xes.model.XLog;
import org.processmining.mapper.controller.XESameController;
import org.processmining.mapper.mapping.Attribute;
import org.processmining.mapper.mapping.Classifier;
import org.processmining.mapper.mapping.Connection;
import org.processmining.mapper.mapping.Event;
import org.processmining.mapper.mapping.Extension;
import org.processmining.mapper.mapping.Log;
import org.processmining.mapper.mapping.Mapping;
import org.processmining.mapper.mapping.Properties;
import org.processmining.mapper.mapping.Trace;

import br.ufpe.activiti.behaviour.util.Util;

public class Monitor{
	
	private String processInstanceIDCorrente; 
	
	public String getProcessInstanceIDCorrente() {
		return processInstanceIDCorrente;
	}
	public void setProcessInstanceIDCorrente(String processInstanceIDCorrente) {
		this.processInstanceIDCorrente = processInstanceIDCorrente;
	}
	
	protected XLog Mapear() {
		Mapping mapping = setXESame(getProcessInstanceIDCorrente());
		XESameController xesameController;
		XLog xLog = null;
		
		
		try {
			xesameController = new XESameController(mapping,"traceExecucao",Util.quantidadeTraces);
			xLog = xesameController.run(); 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Houve um problema para conseguir os dados do banco no monitoramento!");
		}
		
		String ultimaInstancia = xLog.get(xLog.size()-1).getAttributes().values().toString();	
		
		if(xLog.size()==Util.quantidadeTraces) {
			setProcessInstanceIDCorrente(ultimaInstancia.substring(1, ultimaInstancia.length()-1));
		}else if(xLog.size()<Util.quantidadeTraces){
			System.out.println("Precisamos aumentar o tempo"+xLog.size());
			xLog = null;
		}else {
			System.out.println("Precisamos aumentar a quantidade de traces!"+xLog.size());
			xLog = null;
		}
		
		return xLog;
	}
	
	protected Mapping setXESame(String processID) {
		/*--------Monitoring Manager---------*/

		Connection connection = new Connection();
		connection.setDriverLocation("/home/wellisonraul/eclipse-workspace/Doutorado/activiti-behaviour/lib/mysql-connector-java-5.1.36.jar");
		connection.setURL("jdbc:mysql://localhost:3306/activiti");
		connection.setDriver("com.mysql.jdbc.Driver");
		connection.setColumnSeperatorStart("");
		connection.setColumnSeperatorEnd("");
		

		LinkedList<SimpleEntry<String, String>> connectionProperties = new LinkedList<SimpleEntry<String, String>>();

		SimpleEntry<String, String> user = new SimpleEntry<String, String>("user", "root");
		SimpleEntry<String, String> password = new SimpleEntry<String, String>("password", "21289395");

		connectionProperties.add(user);
		connectionProperties.add(password);
		connection.setProperties(connectionProperties);

		Mapping mapping = new Mapping();
		mapping.setConnection(connection);

		Extension conceptExtension = new Extension();
		conceptExtension.setName("Concept");
		conceptExtension.setPrefix("concept");
		conceptExtension.setURI("http://www.xes-standard.org/concept.xesext");

		Extension lifecycleExtension = new Extension();
		lifecycleExtension.setName("Lifecycle");
		lifecycleExtension.setPrefix("lifecycle");
		lifecycleExtension.setURI("http://www.xes-standard.org/lifecycle.xesext");

		Extension orgExtension = new Extension();
		orgExtension.setName("Organizational");
		orgExtension.setPrefix("org");
		orgExtension.setURI("http://www.xes-standard.org/org.xesext");

		Extension timeExtension = new Extension();
		timeExtension.setName("Time");
		timeExtension.setPrefix("time");
		timeExtension.setURI("http://www.xes-standard.org/time.xesext");

		Extension semanticExtension = new Extension();
		semanticExtension.setName("Semantic");
		semanticExtension.setPrefix("semantic");
		semanticExtension.setURI("http://www.xes-standard.org/semantic.xesext");

		Vector<Extension> extensions = new Vector<Extension>();
		extensions.add(semanticExtension);
		extensions.add(timeExtension);
		extensions.add(orgExtension);
		extensions.add(lifecycleExtension);
		extensions.add(conceptExtension);

		mapping.setExtensions(extensions);

		Log log = new Log();
		log.addItemAttribute(new Attribute("concept:name", "PROCESS_ID", "String", conceptExtension));
		log.addItemAttribute(new Attribute("lifecycle:model", "", "String", lifecycleExtension));
		log.addItemAttribute(new Attribute("semantic:modelReference", "", "String", semanticExtension));
		Properties logProperties = new Properties();
		logProperties.setFrom("PROCESS_LOG");

		log.setProperties(logProperties);

		Vector<Classifier> classifiers = new Vector<Classifier>();
		Classifier classifier = new Classifier();
		classifier.setName("classifier");
		classifier.setKeys("concept:instance concept:name time:timestamp org:resource lifecycle:transition");

		classifiers.add(classifier);

		log.setClassifiers(classifiers);

		Trace trace = new Trace();

		trace.addItemAttribute(new org.processmining.mapper.mapping.Attribute("concept:name", "PROCESS_INSTANCE_ID", "String", conceptExtension));
		trace.addItemAttribute(new org.processmining.mapper.mapping.Attribute("semantic:modelReference", "", "String", semanticExtension));

		Properties traceProperties = new Properties();
		traceProperties.setFrom("PROCESS_LOG");
		traceProperties.setTraceID("PROCESS_INSTANCE_ID");
		traceProperties.setWhere("PROCESS_INSTANCE_ID > '"+ processID +"'");
		
		trace.setProperties(traceProperties);

		Event event = new Event();
		
		event.addItemAttribute(new org.processmining.mapper.mapping.Attribute("concept:instance", "PROCESS_INSTANCE_ID", "String", conceptExtension));
		event.addItemAttribute(new org.processmining.mapper.mapping.Attribute("concept:name", "ACTIVITY_NAME", "String", conceptExtension));
		event.addItemAttribute(new org.processmining.mapper.mapping.Attribute("time:timestamp", "TIMESTAMP", "Date", timeExtension));
		event.addItemAttribute(new org.processmining.mapper.mapping.Attribute("org:resource", "RESOURCE", "String", orgExtension));
		event.addItemAttribute(new org.processmining.mapper.mapping.Attribute("lifecycle:transition", "EVENT_TYPE", "String", lifecycleExtension));

		Properties eventProperties = new Properties();
		eventProperties.setFrom("PROCESS_LOG");
		eventProperties.setTraceID("PROCESS_INSTANCE_ID");
		event.setProperties(eventProperties);
		log.getProperties().setEventOrder(processID);
		Vector<Event> events = new Vector<Event>();
		events.add(event);
		trace.setEvents(events);

		log.setTrace(trace);
		mapping.setLog(log);
		
		return mapping;
	}

}
