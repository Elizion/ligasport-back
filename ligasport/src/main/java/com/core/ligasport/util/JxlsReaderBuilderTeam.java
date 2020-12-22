package com.core.ligasport.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReader;
import org.xml.sax.SAXException;
import com.core.ligasport.payload.XlsTeamDataRequest;
import com.core.ligasport.payload.XlsTeamRequest;
import com.core.ligasport.util.ReadProperties;

public class JxlsReaderBuilderTeam
{
	public static XLSReader build(List<String> sheetNames, Map<String, XlsTeamRequest> beans) throws IOException, SAXException {		
		
		String row = ReadProperties.getProperty("jsxl.row.5");
		
		String c3 = ReadProperties.getProperty("jsxl.cell.c3");
		String e3 = ReadProperties.getProperty("jsxl.cell.e3");
		String f3 = ReadProperties.getProperty("jsxl.cell.f3");
	    
		int[] col = new int[5];
		
	    col[0] = 1;  
	    col[1] = 2; 	          
	    col[2] = 3; 
	    col[3] = 4;
	    col[4] = 5; 
	    
		String baseVarName = "input_";
		StringBuilder mapping = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		mapping.append("<workbook>");		
		int index = 0;
		
		for (String sheetName : sheetNames) {
			
			XlsTeamRequest dataModel = new XlsTeamRequest();
			
			dataModel.setXlsTeamDataRequest(new ArrayList<XlsTeamDataRequest>());
			
			beans.put(baseVarName + index, dataModel);			
			dataModel.setSheetName(sheetName);
			
			mapping.append("<worksheet name=\"" + sheetName + "\">");			
				mapping.append("<section startRow=\"0\" endRow=\"4\">");
					mapping.append("<mapping cell=\"" + c3 + "\">" + baseVarName + index + ".customerId</mapping>");
					mapping.append("<mapping cell=\"" + e3 + "\">" + baseVarName + index + ".leagueId</mapping>");
					mapping.append("<mapping cell=\"" + f3 + "\">" + baseVarName + index + ".gender</mapping>");
				mapping.append("</section>");				
				
				mapping.append("<loop startRow=\"5\" endRow=\"5\" items=\"" + baseVarName + index + ".xlsTeamDataRequest\" var=\"xlsTeamDataRequest_" + index + "\" varType=\"com.core.ligasport.payload.XlsTeamDataRequest\" >");					
					mapping.append("<section startRow=\"5\" endRow=\"5\">");
						mapping.append("<mapping row=\"" + row + "\" col=\"" + col[0] + "\">xlsTeamDataRequest_" + index + ".sequence</mapping>");
						mapping.append("<mapping row=\"" + row + "\" col=\"" + col[1] + "\">xlsTeamDataRequest_" + index + ".name</mapping>");
						mapping.append("<mapping row=\"" + row + "\" col=\"" + col[2] + "\">xlsTeamDataRequest_" + index + ".dateFoundation</mapping>");
						mapping.append("<mapping row=\"" + row + "\" col=\"" + col[3] + "\">xlsTeamDataRequest_" + index + ".state</mapping>");
						mapping.append("<mapping row=\"" + row + "\" col=\"" + col[4] + "\">xlsTeamDataRequest_" + index + ".uniforms</mapping>");
					mapping.append("</section>");					
					mapping.append("<loopbreakcondition>");
						mapping.append("<rowcheck offset=\"0\">");
							mapping.append("<cellcheck offset=\"1\"/>");
						mapping.append("</rowcheck>");
					mapping.append("</loopbreakcondition>");					
				mapping.append("</loop>");
				
			mapping.append("</worksheet>");			
			index++;
		}		
		mapping.append("</workbook>");		
		XLSReader reader = ReaderBuilder.buildFromXML(new ByteArrayInputStream(mapping.toString().getBytes()));	
		return reader;
	}
	
}