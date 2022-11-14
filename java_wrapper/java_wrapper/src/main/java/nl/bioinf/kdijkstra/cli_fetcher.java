package nl.bioinf.kdijkstra;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;



public class cli_fetcher{

    public String buildOptions(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("file", true, "Input file (arff format) of instances to be predicted");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("file")) {
            return args[1];
        }
        throw new RuntimeException();
    }


}
