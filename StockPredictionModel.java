import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;

public class StockPredictionModel {
    public static void main(String[] args) throws Exception {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("sentiment_polarity"));
        ArrayList<String> classValues = new ArrayList<>();
        classValues.add("Positive");
        classValues.add("Negative");
        attributes.add(new Attribute("class", classValues));
        Instances dataset = new Instances("StockPrediction", attributes, 0);
        dataset.setClassIndex(1);
        double[] values1 = {0.7, dataset.attribute(1).indexOfValue("Positive")};
        double[] values2 = {-0.5, dataset.attribute(1).indexOfValue("Negative")};
        dataset.add(new DenseInstance(1.0, values1));
        dataset.add(new DenseInstance(1.0, values2));
        NaiveBayes model = new NaiveBayes();
        model.buildClassifier(dataset);
        Instance testInstance = new DenseInstance(2);
        testInstance.setValue(attributes.get(0), 0.3); 
        testInstance.setDataset(dataset);

        double prediction = model.classifyInstance(testInstance);
        System.out.println("Predicted class: " + dataset.classAttribute().value((int) prediction));
    }
}
