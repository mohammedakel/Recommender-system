package edu.brown.cs.student.main;

// look into using these imports for your REPL!

import edu.brown.cs.student.main.BloomFilterr.BloomFilterBuilder;
import edu.brown.cs.student.main.BloomFilterr.BloomFiltersLoader;
import edu.brown.cs.student.main.BloomFilterr.SBLTuple;
import edu.brown.cs.student.main.BloomFilterr.SimilarityGenerator;

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

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

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

    private void run()  {
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
        String filePath = "data/project1/proj1_small.csv";
        CSVParser newParser = new CSVParser(filePath, Student::new, true);
        try{
            newParser.readLine();}
        catch (IOException e){
            System.out.println("error");
        }
        List<Student> students = newParser.getListOfObjects();

        //create a bloom filter for all students using the BloomFilterLoader class which takes in
        // a list of students and the loadAllbloom() method
        BloomFiltersLoader loader = new BloomFiltersLoader(students);
        HashMap<String, BloomFilterBuilder> idstoBlooms =  loader.loadAllBlooms();

        //find similar using the similarity generator class which takes in the hashmap and number of neighbours as well
        // as its findSimilar methods which takes in the id of the target student
        // k represents the desired number of neighbours (from user)
        SimilarityGenerator generator = new SimilarityGenerator(idstoBlooms, 5);

        // id is the student who we want to find similars for
        PriorityQueue<SBLTuple> similarityResult = generator.findSimilar(1);

        //this limit variable must be set equal to the number of desired neighbours
        //loop through the priority que and print the ids of the similar students
        int limit = 5;
        while(limit>0) {
            SBLTuple current = similarityResult.poll();
            System.out.println(current.id);
            limit--;
        }

    }

    private void runSparkServer(int port) {
        // set port to run the server on
        Spark.port(port);

        // specify location of static resources (HTML, CSS, JS, images, etc.)
        Spark.externalStaticFileLocation("src/main/resources/static");
    }

}
