package org.apache.camel.example.rest;

import java.io.IOException;
import java.util.Locale;

import javax.xml.transform.stream.StreamSource;

import org.apache.camel.Exchange;
import org.apache.camel.converter.stream.InputStreamCache;
import org.apache.log4j.Logger;
import org.milyn.Smooks;
import org.milyn.SmooksException;
import org.milyn.container.ExecutionContext;
import org.milyn.event.report.HtmlReportGenerator;
import org.milyn.payload.StringResult;
import org.xml.sax.SAXException;

public class EDItoXML {
	private static final Logger log = Logger.getLogger(EDItoXML.class.getName());
	
	
	public void toXML(Exchange exchange)
	{
		
		try {
			log.info("Running SmooksTransform");
			
			if(exchange!=null)
				log.info("exchange!=null");
				
			System.out.println(exchange.getIn().getBody());
			
			String messageOut = runSmooksTransform((InputStreamCache)exchange.getIn().getBody());
			
			exchange.getIn().setBody(messageOut);
		} catch (SmooksException | IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	protected static String runSmooksTransform(InputStreamCache messageIn) throws SmooksException, IOException, SAXException {

		Locale defaultLocale = Locale.getDefault();
		Locale.setDefault(new Locale("en", "IE"));

		// Instantiate Smooks with the config...
		Smooks smooks = new Smooks("smooks-config.xml");

		try {
			// Create an exec context - no profiles....
			ExecutionContext executionContext = smooks.createExecutionContext();

			StringResult result = new StringResult();

			// Configure the execution context to generate a report...
			executionContext.setEventListener(new HtmlReportGenerator("target/report/report.html"));

			// Filter the input message to the outputWriter, using the execution
			// context...
			smooks.filterSource(executionContext, new StreamSource(messageIn), result);

			Locale.setDefault(defaultLocale);

			String xml = result.getResult();

//			JSONObject xmlJSONObj = XML.toJSONObject(xml);
//			String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
//			System.out.println(jsonPrettyPrintString);
//
//			return jsonPrettyPrintString;
			return xml;
		} finally {
			smooks.close();
		}
	}

}
