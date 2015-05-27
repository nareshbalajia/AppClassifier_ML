package androidappclassifier;

import featuresparser.TxtParser;
import featuresparser.XmlParser;
import classifierbuilder.J48Classifier;
import classifierbuilder.NaiveBayesClassifier;
import classifierbuilder.NaiveBayesUpdateableClassifier;


public class ClassifierFramework {

	public static void main(String[] args) throws Exception {
		
		try {
			//to invoke the xml parser class
			//XmlParser xp=new XmlParser();
			//xp.permissionsParsing();
			//to invoke the text parser class
			//TxtParser tp=new TxtParser();
			//tp.stringParsing();
			//naive bayes algorithm invocation
			NaiveBayesClassifier nbc=new NaiveBayesClassifier();
			//j48 algorithm invocation
			J48Classifier j48c=new J48Classifier();
			//NaiveBayesUpdateable algorithm invlocation
			NaiveBayesUpdateableClassifier nbs=new NaiveBayesUpdateableClassifier();
			//cross validation results on permissions
			nbc.naiveBayesOnPermissionsWithCrossValidation();
			j48c.j48OnPermissionsWithCrossValidation();
			//individual testing results on permissions
			nbc.naiveBayesOnGenuinePermissionsWithoutCrossValidation();
			nbc.naiveBayesOnMalwarePermissionsWithoutCrossValidation();
			//cross validation results on strings
			//nbs.naiveBayesUpdateableOnStringsWithCrossValidation();
			//nbc.naiveBayesOnStringsWithCrossValidation();
			
			} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

}
