package br.ufpe.activiti.behaviour.conversores;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;

public class ConversorXMLBPMN {
	
	
	public List<Process> converterXMLBPMN(String caminhoArquivo) {
		XMLStreamReader xmlStreamReader = xmlParaStreamReader(caminhoArquivo);
		BpmnModel modeloBPMN = streamReaderParaBPMNModel(xmlStreamReader);
		return colecaoServicos(modeloBPMN);
	}
	
	public XMLStreamReader xmlParaStreamReader(String caminhoArquivo) {
		String caminhoDoArquivo = caminhoArquivo;
		XMLStreamReader xmlStreamReader = null;
		
		try {
			Reader arquivoLido = new FileReader(caminhoDoArquivo);
			
			XMLInputFactory xmlInputFactory = 
					XMLInputFactory.newInstance();
			
			xmlStreamReader  = 
					xmlInputFactory.createXMLStreamReader(arquivoLido);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlStreamReader;	
	}
	
	public BpmnModel streamReaderParaBPMNModel(XMLStreamReader xmlStreamReader) {
		BpmnXMLConverter bpmnXC = new BpmnXMLConverter();
		
		return bpmnXC.convertToBpmnModel(xmlStreamReader);
	}
	
	public List<Process> colecaoServicos(BpmnModel modeloBPMN) {
		List<Process> processos = modeloBPMN.getProcesses();
		
		
		/*List<ServiceTask> listaDeServicos = new ArrayList<ServiceTask>();
		
		for(Process processo: processos) {
			Collection<FlowElement> colecaoFlow = processo.getFlowElements();
			for(FlowElement flow: colecaoFlow) {
				if(flow.getClass().equals(ServiceTask.class)) {
					ServiceTask servico = (ServiceTask) flow;
					listaDeServicos.add(servico);
				}
				
			}
		}*/
		
		return processos;
	}
	
	public static void main(String []args) throws FileNotFoundException, XMLStreamException {
		/*ConversorXMLBPMN c = new ConversorXMLBPMN();
		c.converter("/home/wellisonraul/eclipse-workspace/Doutorado/activiti-behaviour/src/main/resources/processICMS.bpmn20.xml");
		
		BpmnXMLConverter bpmnXC = new BpmnXMLConverter();
		 
		//File Path
		String filePath = "/home/wellisonraul/eclipse-workspace/Doutorado/activiti-behaviour/src/main/resources/processICMS.bpmn20.xml";
		
		//Read XML file.
		Reader fileReader = new FileReader(filePath);
		
		//Get XMLInputFactory instance.
		XMLInputFactory xmlInputFactory = 
				XMLInputFactory.newInstance();
		
		//Create XMLStreamReader object.
		XMLStreamReader xmlStreamReader  = 
				xmlInputFactory.createXMLStreamReader(fileReader);
		
		BpmnModel bpmnmodel;
		
		bpmnmodel = bpmnXC.convertToBpmnModel(xmlStreamReader);
		
		//FlowElement flowElement = bpmnmodel.getMainProcess().getFlowElement("sid-BBDCCE8C-1362-4FFF-AE5F-075C206FB1D8").getClass();
		ServiceTask serviceTask = (ServiceTask) bpmnmodel.getMainProcess().getFlowElement("sid-BBDCCE8C-1362-4FFF-AE5F-075C206FB1D8");
		//System.out.println(serviceTask.getFieldExtensions().get(0).getExpression());
		
		Collection<FlowElement> colecaoFlow = bpmnmodel.getProcessById("processICMS").getFlowElements();
		
		for(FlowElement flow: colecaoFlow) {
			if(flow.getClass().equals(ServiceTask.class)) {
				ServiceTask service = (ServiceTask) flow;
				for(FieldExtension field: service.getFieldExtensions()) {
					System.out.println("O extension " +field.getFieldName() + " tem o valor: "+field.getExpression());
				}
			}
			
		}
		
		
		/*ArrayList<ServiceTask> arrayServiceTask =
		System.out.println(bpmnmodel.getProcessById("processICMS").getFlow);
		
		
		
		for(FieldExtension field: serviceTask.getFieldExtensions()) {
			System.out.println("O extension " +field.getFieldName() + " tem o valor: "+field.getExpression());
		}*/
		
		
		//bpmnmodel.getProcessById("processICMS").getFlowElements().
		
		//System.out.println(bpmnmodel.getProcessById("processICMS").getFlowElementsContainer("sid-BBDCCE8C-1362-4FFF-AE5F-075C206FB1D8").getFlowElements());
	}
}
