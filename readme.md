# Cirrhosis prediction

This repostitory was made as part of the curriculum of the machine learning course of the bio-informatics major.
In this project a model was created to predict the occurence of cirrhosis in primary biliary cholangitis patients.
The model is made on a dataset acquired from the kaggle website, links is in the references.

The project consists of three parts:

## EDA and ML-analysis
In the first two parts we performed exploratory data analysis on the given dataset.
This consited of cleaning the data and looking for meaningful correlations.
We also researched and tested vaious machine learning algorithms, one of these was used for our eventual model.
This can all be found in the logbook.Rmd file.

## Java application
The third part is a java wrapper around the model.
Through this a new instances can be predicted through the commandline interface.

### Prerequisities
* java version "18.0.2.1" or newer
* The Weka java package 

### Arguments
* -file : path
    Path to a file with new instances to be predicted, must be in the arff format.
    A test file can be found under 'data' **within** the java-wrapper directory.

### Usage
To call the java program, you wil have to call the shadow jar object, this might look something like this:

```cli
java -jar java_wrapper-1.0-SNAPSHOT-all.jar -file <path to file>
```

## References
fedesoriano. (August 2021). Cirrhosis Prediction Dataset. Retrieved 15-9-2022 from https://www.kaggle.com/fedesoriano/cirrhosis-prediction-dataset.
Eibe Frank, Mark A. Hall, and Ian H. Witten (2016). The WEKA Workbench. Online Appendix for "Data Mining: Practical Machine Learning Tools and Techniques", Morgan Kaufmann, Fourth Edition, 2016.