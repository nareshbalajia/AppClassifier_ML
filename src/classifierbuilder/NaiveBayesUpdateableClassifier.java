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
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.core.Instances;
import weka.filters.*;
import weka.filters.unsupervised.attribute.*;

public class NaiveBayesUpdateableClassifier {
	public void naiveBayesUpdateableOnStringsWithCrossValidation() throws Exception,FileNotFoundException,IOException
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
			NaiveBayesUpdateable classifier=new NaiveBayesUpdateable();
			//to build the classifier with the dataset
			classifier.buildClassifier(filteredTrainer);
			//to define the evaluation model
			Evaluation evaluate=new Evaluation(filteredTrainer);
			//to set the cross validation characteristics
			evaluate.crossValidateModel(classifier, filteredTrainer, 15, new Random(1));
			//System.out.println(evaluate.toSummaryString("\n The classification results\n",true));
			//to write the output to a log file
			BufferedWriter outputWriter=new BufferedWriter(new FileWriter("OutputStatistics/CrossValidationResults/NaiveBayesSimpleCrossValidationResultsOnStrings.log"));
			outputWriter.write(evaluate.toSummaryString());
			outputWriter.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}


		
	}


}
