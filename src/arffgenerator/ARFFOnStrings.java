package arffgenerator;

//below are the WEKA API's packages needed to build ARFF file
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

//java.io packages needed to write output to a file
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ARFFOnStrings 
{
	//declaration of variables and types needed for creating ARFF file using WEKA API
	FastVector attributes;
	FastVector classAttribute;
	Instances data;
	double[] dataValue;
	BufferedWriter arffWriter;
	static int count=1;
	public ARFFOnStrings()
	{
		// To set up the attributes
				attributes=new FastVector();
				// To define the permissions attribute
				attributes.addElement(new Attribute("conststringvalue",(FastVector)null));
				// To define the class attribute
				classAttribute=new FastVector(2);
				// Two class attribute value-genuine and malware
				classAttribute.addElement("genuine");
				classAttribute.addElement("malware"); 
				// adding the class attribute to dataset
				attributes.addElement(new Attribute("class",classAttribute));
				// To create the instances object
				data=new Instances("AndroidAppClassifier",attributes,0);
	}
	public void arffBuilder(String[] stringVal) throws IOException
	{
		for(int i=0;i<stringVal.length;i++)
		{
			//it is essential to initialize double array everytime when the method is  called
			dataValue=new double[data.numAttributes()];
			dataValue[0]=data.attribute(0).addStringValue(stringVal[i]);
			dataValue[1]=classAttribute.indexOf("genuine");
			//use the below line to set class option as malware for malicious apps
			//dataValue[1]=classAttribute.indexOf("malware");
			data.add(new Instance(1.0,dataValue));
			
		}
		arffWriter=new BufferedWriter(new FileWriter("genuine"+count+".arff"));
		//use the below line to name the files for malicious apps
		//arffWriter=new BufferedWriter(new FileWriter("malicious"+count+".arff"));
		arffWriter.write(data.toString());
		arffWriter.flush();
		++count;
		
	}

}
