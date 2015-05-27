package featuresparser;

import java.io.File;
import java.io.IOException;

//packages needed to parse the xml file using DOM parser
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import arffgenerator.ARFFOnPermissions;

public class XmlParser 
{
	
   public void permissionsParsing() throws Exception
	{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder document=factory.newDocumentBuilder();
			for(File xmlFile: new File("Genuine/dexml").listFiles()) 
			//to parse the malicious app's xml files use the below for loop
			//for(File xmlFile: new File("Malware/dexml").listFiles())	
			{
			//to get the file's path
			Document doc=document.parse(xmlFile.getAbsolutePath());
			//to search the tag name "uses-permission"
			NodeList permissionsList=doc.getElementsByTagName("uses-permission");
			//to set the string array's length to the number of permissions in the parsing xml file
			String[] permissionValue=new String[permissionsList.getLength()];
			for(int i=0;i<permissionsList.getLength();i++)
			{
				Node n=permissionsList.item(i);
				//first to verify the node type
				if(n.getNodeType()==Node.ELEMENT_NODE)
				{
					//to cast the node type to element type since getAttribute() method accepts only element types
					Element permission=(Element) n;
					permissionValue[i]=permission.getAttribute("android:name");
					}
				}
			//creating object for the class which contains the ARFF file generating method
			ARFFOnPermissions obj=new ARFFOnPermissions();
			obj.arffBuilder(permissionValue);
			
		   }
		}
			
		catch (ParserConfigurationException e) 
		{
			
			e.printStackTrace();
		} 
		catch (SAXException e) 
		{
			
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}

}
