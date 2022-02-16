package edu.brown.cs.student.main;

// look into using these imports for your REPL!


import edu.brown.cs.student.main.BloomFilterr.BloomFilter;
import edu.brown.cs.student.main.BloomFilterr.BloomFilterBuilder;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.Spark;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {


    // use port 4567 by default when running server
    private static final int DEFAULT_PORT = 4567;

    /**
     * The initial method called when execution begins.
     *
     * @param args An array of command line arguments
     */
    public static void main(String[] args) {
        new Main(args).run();
    }

    private String[] args;

    private Main(String[] args) {
        this.args = args;
    }

    private void run() {
        // set up parsing of command line flags
        OptionParser parser = new OptionParser();

        // "./run --gui" will start a web server
        parser.accepts("gui");

        // use "--port <n>" to specify what port on which the server runs
        parser.accepts("port").withRequiredArg().ofType(Integer.class)
                .defaultsTo(DEFAULT_PORT);

        OptionSet options = parser.parse(args);
        if (options.has("gui")) {
            runSparkServer((int) options.valueOf("port"));
        }

        // TODO: Add your REPL here!

        //given  the following user inputs:
        int n = 5;
        double r = 0.01;

        BloomFilterBuilder<String> sampleBloomFilter = new BloomFilterBuilder<>(n, r);
        System.out.println(sampleBloomFilter.toBinaryString() + "\n");
        sampleBloomFilter.add("Java");
        System.out.println(sampleBloomFilter.toBinaryString() + "\n");
        System.out.println(sampleBloomFilter.mightContain("Java"));

        BloomFilterBuilder<String> sampleBloomFilterTwo = new BloomFilterBuilder<>(n);
        System.out.println(sampleBloomFilterTwo.toBinaryString() + "\n");
        sampleBloomFilterTwo.add("Java");
        System.out.println(sampleBloomFilterTwo.toBinaryString() + "\n");
        System.out.println(sampleBloomFilterTwo.mightContain("Java"));
      
       Stars star = new Stars();
       NaiveNeighbors naive = new NaiveNeighbors();
       LoadKD load = new LoadKD();
       CreateBF createBF = new CreateBF();
       InsertBF insertBF = new InsertBF();
       QueryBF queryBF = new QueryBF();

       REPL.runREPL();
    }

    private void runSparkServer(int port) {
        // set port to run the server on
        Spark.port(port);

        // specify location of static resources (HTML, CSS, JS, images, etc.)
        Spark.externalStaticFileLocation("src/main/resources/static");
    }

}
