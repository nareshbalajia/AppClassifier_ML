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
import weka.core.Instances;
import weka.filters.*;
import weka.filters.unsupervised.attribute.*;


public class NaiveBayesClassifier {
	static int count=1,count2=1;;
	
    public void naiveBayesOnPermissionsWithCrossValidation() throws Exception,FileNotFoundException,IOException
	{
		BufferedReader datasetReader=null;
		try {
			datasetReader=new BufferedReader(new FileReader("Datasets/CrossEvaluationDatasetForPermissions.arff"));
			//to load the dataset to the trainer
			Instances classifierTrainer=new Instances(datasetReader);
			//to convert string attribute to numeric(naive bayes cannot accept string attributes)
			StringToWordVector filter=new StringToWordVector();
			filter.setInputFormat(classifierTrainer);
			Instances filteredTrainer=Filter.useFilter(classifierTrainer, filter);
			//to set the class index-class attribute will be put first by the StringToWordVector class
			filteredTrainer.setClassIndex(0);
			datasetReader.close();
			//to write the numeric type arff file
			//BufferedWriter nominalTypeWriter=new BufferedWriter(new FileWriter("nominal.arff"));
			//nominalTypeWriter.write(filteredTrainer.toString());
			//to instantiate the NaiveBayes weka class
			NaiveBayes classifier=new NaiveBayes();
			//to build the classifier with the dataset
			classifier.buildClassifier(filteredTrainer);
			//to define the evaluation model
			Evaluation evaluate=new Evaluation(filteredTrainer);
			//to set the cross validation characteristics
			evaluate.crossValidateModel(classifier, filteredTrainer, 15, new Random(1));
			//System.out.println(evaluate.toSummaryString("\n The classification results\n",true));
			//to write the output to a log file
			BufferedWriter outputWriter=new BufferedWriter(new FileWriter("OutputStatistics/Demo/CrossValidationResults/NaiveBayesCrossValidationResultsOnPermissions.log"));
			outputWriter.write(evaluate.toSummaryString());
			outputWriter.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

  }
	public void naiveBayesOnGenuinePermissionsWithoutCrossValidation() throws Exception,FileNotFoundException,IOException
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
			NaiveBayes classifier=new NaiveBayes();
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
			BufferedWriter outputWriter=new BufferedWriter(new FileWriter("OutputStatistics/Demo/IndividualTesting/GenuineTesting/"+"GenuineApp"+count+".log"));
			outputWriter.write(evaluate.toSummaryString("\n The Test results\n",false));
			outputWriter.flush();
			++count;
			}
			dataReader.close();
			
	}
	public void naiveBayesOnMalwarePermissionsWithoutCrossValidation() throws Exception,FileNotFoundException,IOException
	{
			BufferedReader dataReader=null;
			//training data
			dataReader=new BufferedReader(new FileReader("Datasets/TrainingDataset.arff"));
			Instances classifierTrainer=new Instances(dataReader);
			//to convert string attribute to numeric(naive bayes cannot accept string attributes)
			StringToWordVector filter=new StringToWordVector();
			filter.setInputFormat(classifierTrainer);
			Instances filteredTrainer=Filter.useFilter(classifierTrainer, filter);
			//to set the class index-class attribute will be put first by the StringToWordVector class
			filteredTrainer.setClassIndex(0);
			NaiveBayes classifier=new NaiveBayes();
			classifier.buildClassifier(filteredTrainer);
			//test data
			for(File testFile: new File("Malware/ARFFOnPermissionsMalware").listFiles())
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
			BufferedWriter outputWriter=new BufferedWriter(new FileWriter("OutputStatistics/Demo/IndividualTesting/MalwareTesting/"+"MalwareApp"+count2+".log"));
			outputWriter.write(evaluate.toSummaryString("\n The Test results\n",false));
			outputWriter.flush();
			++count2;
			}
			dataReader.close();
			
	}
	public void naiveBayesOnStringsWithCrossValidation() throws Exception,FileNotFoundException,IOException
	{
		BufferedReader datasetReader=null;
		try {
			datasetReader=new BufferedReader(new FileReader("Datasets/CrossEvaluationDatasetForStrings.arff"));
			//to load the dataset to the trainer
			Instances classifierTrainer=new Instances(datasetReader);
			//to convert string attribute to numeric(naive bayes cannot accept string attributes)
			StringToWordVector filter=new StringToWordVector();
			filter.setInputFormat(classifierTrainer);
			Instances filteredTrainer=Filter.useFilter(classifierTrainer, filter);
			//to set the class index-class attribute will be put first by the StringToWordVector class
			filteredTrainer.setClassIndex(0);
			datasetReader.close();
			//to write the numeric type arff file
			//BufferedWriter nominalTypeWriter=new BufferedWriter(new FileWriter("nominal.arff"));
			//nominalTypeWriter.write(filteredTrainer.toString());
			//to instantiate the NaiveBayes weka class
			NaiveBayes classifier=new NaiveBayes();
			//to build the classifier with the dataset
			classifier.buildClassifier(filteredTrainer);
			//to define the evaluation model
			Evaluation evaluate=new Evaluation(filteredTrainer);
			//to set the cross validation characteristics
			evaluate.crossValidateModel(classifier, filteredTrainer, 15, new Random(1));
			//System.out.println(evaluate.toSummaryString("\n The classification results\n",true));
			//to write the output to a log file
			BufferedWriter outputWriter=new BufferedWriter(new FileWriter("OutputStatistics/CrossValidationResults/NaiveBayesCrossValidationResultsOnStrings.log"));
			outputWriter.write(evaluate.toSummaryString());
			outputWriter.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}


		
	}

	
}
