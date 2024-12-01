import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

public class SentimentAnalysis {
    public static String analyzeSentiment(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.annotation().get(CoreAnnotations.SentencesAnnotation.class);
        StringBuilder sentimentResult = new StringBuilder();

        for (CoreMap sentence : sentences) {
            String sentiment = sentence.get(CoreAnnotations.SentimentClass.class);
            sentimentResult.append(sentiment).append(" ");
        }
        return sentimentResult.toString().trim();
    }

    public static void main(String[] args) {
        String exampleText = "The stock market is looking very positive today!";
        String sentiment = analyzeSentiment(exampleText);
        System.out.println("Sentiment: " + sentiment);
    }
}
