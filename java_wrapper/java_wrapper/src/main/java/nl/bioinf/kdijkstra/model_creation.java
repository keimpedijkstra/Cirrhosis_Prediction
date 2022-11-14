package nl.bioinf.kdijkstra;

import org.apache.commons.cli.ParseException;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.IOException;


public class model_creation {
    private final String modelFile = "C:/Users/keimp/Cirrhosis_Prediction/java_wrapper/java_wrapper/data/naive.model/";

    public static void main(String[] args) {
        cli_fetcher cf = new cli_fetcher();
        String unknownFile = null;
        try {
            unknownFile = cf.buildOptions(args);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        model_creation runner = new model_creation();
        runner.start(unknownFile);
    }

    private void start(String unknownFile) {
        String datafile = "C:/Users/keimp/Cirrhosis_Prediction/java_wrapper/java_wrapper/data/data_single_class.arff";

        try {
            Instances instances = loadArff(datafile);
            NaiveBayes nb = buildClassifier(instances);
            saveClassifier(nb);
            NaiveBayes fromFile = loadClassifier();
            Instances unknownInstances = loadArff(unknownFile);
            System.out.println("\nunclassified unknownInstances = \n" + unknownInstances);
            classifyNewInstance(fromFile, unknownInstances);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void classifyNewInstance(NaiveBayes tree, Instances unknownInstances) throws Exception {
        Instances labeled = new Instances(unknownInstances);

        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            double clsLabel = tree.classifyInstance(unknownInstances.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
        }
        System.out.println("\nNew, labeled = \n" + labeled);
    }

    private void saveClassifier(NaiveBayes nb) throws Exception {
        weka.core.SerializationHelper.write(modelFile, nb);
    }

    private NaiveBayes buildClassifier(Instances instances) throws Exception {

        NaiveBayes tree = new NaiveBayes();         // new instance of tree

        tree.buildClassifier(instances);   // build classifier
        return tree;
    }

    private void printInstances(Instances instances) {
        int numAttributes = instances.numAttributes();

        for (int i = 0; i < numAttributes; i++) {
            System.out.println("attribute " + i + " = " + instances.attribute(i));
        }

        System.out.println("class index = " + instances.classIndex());

        //or
        int numInstances = instances.numInstances();
        for (int i = 0; i < numInstances; i++) {
            if (i == 5) break;
            Instance instance = instances.instance(i);
            System.out.println("instance = " + instance);
        }
    }


    private Instances loadArff(String datafile) throws IOException {
        try {
            DataSource source = new DataSource(datafile);
            Instances data = source.getDataSet();
            // setting class attribute if the data format does not provide this information
            // For example, the XRFF format saves the class attribute information as well
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (Exception e) {
            throw new IOException("could not read from file");
        }
    }

    private NaiveBayes loadClassifier() throws Exception {
        // deserialize model
        return (NaiveBayes) weka.core.SerializationHelper.read(modelFile);
    }


}


