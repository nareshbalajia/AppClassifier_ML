package featuresparser;

//IO packages needed to read the dex files
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//packages needed for Regular Expression forming and matching with file contents
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import arffgenerator.ARFFOnStrings;

public class TxtParser {

	public void stringParsing()  throws IOException  {
		//Two Pattern and Matcher class objects are declared.
		//One is for finding the string array length to store the values and other one is to parse the file 
		Pattern regExpPattern,regExpPattern1;
		Matcher expMatcher,expMatcher1;
		//Two BufferedReader objects to parse the file twice
		BufferedReader dexFileReader,dexFileReader1;
		//two string arrays-one for unfiltered and one for filtered string values
		String[] stringValue,filteredStringValue;
		for(File dexFile: new File("D:/project/apks/dedex").listFiles())
		//to parse the malicious apps dex files use the below for loop
		//for(File dexFile: new File("D:/project/apks/malicious/dedex").listFiles())
		{
			//variable needed to set the length of the string array
			int stringInstancesCount=0,filteredInstancesCount=0;
			int i=0,l=0;
		try {
			dexFileReader=new BufferedReader(new FileReader(dexFile));
			//two string variables to store the current line which is being read
			String currentLine,currentLine1;
			while((currentLine=dexFileReader.readLine())!=null)
			{
			//First to match the pattern "const-string" in order to find the no of occurrences in the file
			regExpPattern=Pattern.compile("const-string");
			expMatcher=regExpPattern.matcher("");
			expMatcher.reset(currentLine);
			while(expMatcher.find())
				{
				//if the regex if matched, increment the counter variable to set the array size
				++stringInstancesCount;
				}
			}
			//string array size of being set to the value of counter variable
			stringValue=new String[stringInstancesCount];
			//dex file is being parsed for the second time to extract the const-string values
		    dexFileReader1=new BufferedReader(new FileReader(dexFile));
			regExpPattern1=Pattern.compile("(?<=const-string).*");
			expMatcher1=regExpPattern1.matcher("");
				while((currentLine1=dexFileReader1.readLine())!=null)
				{
					expMatcher1.reset(currentLine1);
					while(expMatcher1.find())
					{
						stringValue[i]=expMatcher1.group();
						//to increment the i variable to move to the next index position of array
						++i;
						}
				}
				//to filter the unwanted and empty string values(for eg:string length less than 15)
				//to set the length for filtered string array
				for(int j=0;j<stringValue.length;j++)
				{
					if(stringValue[j].length()>15)
					{
						++filteredInstancesCount;
					}
				}
				//to set the filtered string count to the string array
			filteredStringValue=new String[filteredInstancesCount];
			//to store the const-string values in the array
			for(int k=0;k<stringValue.length;k++)
			{
				if(stringValue[k].length()>15)
				{
					filteredStringValue[l]=stringValue[k];
					++l;
				}
			}
			//string array is passed to the method that generates the ARFF file.	
			ARFFOnStrings obj=new ARFFOnStrings();
			obj.arffBuilder(filteredStringValue);
			} 
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
		}
		
		
}

}
