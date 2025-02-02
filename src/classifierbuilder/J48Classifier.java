package classifierbuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class J48Classifier {
	static int count=1;

	public void j48OnPermissionsWithCrossValidation() throws Exception,FileNotFoundException,IOException
{
	try
	{
	BufferedReader inputReader=null;
	inputReader=new BufferedReader(new FileReader("Datasets/CrossEvaluationDatasetForPermissions.arff"));
	//to load the dataset to the trainer
	Instances classifierTrainer=new Instances(inputReader);
	//to convert string attribute to numeric(naive bayes cannot accept string attributes)
	StringToWordVector filter=new StringToWordVector();
	filter.setInputFormat(classifierTrainer);
	Instances filteredTrainer=Filter.useFilter(classifierTrainer, filter);
	//to set the class index-class attribute will be put first by the StringToWordVector class
	filteredTrainer.setClassIndex(0);
	inputReader.close();

	J48 classifier=new J48();
	//build the J48 classifier
	classifier.buildClassifier(filteredTrainer);
	//to define the evaluation model
	Evaluation evaluate=new Evaluation(filteredTrainer);
	//to set the cross validation characteristics
	evaluate.crossValidateModel(classifier, filteredTrainer, 15, new Random(1));
	//System.out.println(evaluate.toSummaryString("\n The classification results\n",true));
	BufferedWriter outputWriter=new BufferedWriter(new FileWriter("OutputStatistics/Demo/CrossValidationResults/J48CrossValidationResultsOnPermissions.log"));
	outputWriter.write(evaluate.toSummaryString());
	outputWriter.close();
	
	}
	catch (Exception e) {
		
		e.printStackTrace();
	}

}
	public void j48OnPermissionsWithoutCrossValidation() throws Exception,FileNotFoundException,IOException
	{
			BufferedReader dataReader=null;
			//training data
			dataReader=new BufferedReader(new FileReader("Datasets/TrainingDataset2.arff"));
			Instances classifierTrainer=new Instances(dataReader);
			//to convert string attribute to numeric(naive bayes cannot accept string attributes)
			StringToWordVector filter=new StringToWordVector();
			filter.setInputFormat(classifierTrainer);
			Instances filteredTrainer=Filter.useFilter(classifierTrainer, filter);
			//to set the class index-class attribute will be put first by the StringToWordVector class
			filteredTrainer.setClassIndex(0);
			J48 classifier=new J48();
			classifier.buildClassifier(filteredTrainer);
			//test data
			for(File testFile: new File("Genuine/ARFFOnPermissionsGenuine").listFiles())
			{
			dataReader=new BufferedReader(new FileReader(testFile));
			Instances classifierTester=new Instances(dataReader);
			//to convert string attribute to numeric(naive bayes cannot accept string attributes)
			StringToWordVector filterTester=new StringToWordVector();
			filterTester.setInputFormat(classifierTester);
			Instances filteredTester=Filter.useFilter(classifierTester, filterTester);
			//to set the class index-class attribute will be put first by the StringToWordVector class
			filteredTester.setClassIndex(0);
			//evaluate the model
			Evaluation evaluate=new Evaluation(filteredTrainer);
			evaluate.evaluateModel(classifier,filteredTester);
			//System.out.println(evaluate.toSummaryString("\n The test results\n",false));
			BufferedWriter outputWriter=new BufferedWriter(new FileWriter("OutputStatistics/IndividualTesting/"+"app"+count+".log"));
			outputWriter.write(evaluate.toSummaryString("\n The Test results\n",false));
			outputWriter.flush();
			++count;
			}
			dataReader.close();
			
	}
	
}
