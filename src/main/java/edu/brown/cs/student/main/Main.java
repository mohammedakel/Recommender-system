package edu.brown.cs.student.main;

// look into using these imports for your REPL!

import edu.brown.cs.student.main.BloomFilterr.BloomFilterBuilder;
import edu.brown.cs.student.main.BloomFilterr.BloomFiltersLoader;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;

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
        int n = 10;
        double r = 0.01;


        BloomFilterBuilder sampleBloomFilter = new BloomFilterBuilder(n, r);
        System.out.println(sampleBloomFilter.toBinaryString() + "\n");
        sampleBloomFilter.add("Java");
        System.out.println(sampleBloomFilter.toBinaryString() + "\n");
        System.out.println(sampleBloomFilter.mightContain("Java"));

        BloomFilterBuilder sampleBloomFilterTwo = new BloomFilterBuilder(n);
        System.out.println(sampleBloomFilterTwo.toBinaryString() + "\n");
        sampleBloomFilterTwo.add("Java");
        System.out.println(sampleBloomFilterTwo.toBinaryString() + "\n");
        System.out.println(sampleBloomFilterTwo.mightContain("Java"));
        System.out.println(sampleBloomFilterTwo.mightContain("Python"));


        Student one = new Student(1, "name", "email", "gender", "class year",
                "ssn", "nationality", "race", 20, "communication style",
                3, "meeting style", "meeting time", 10, "strength",
                "weakness", "skills", "intrests");
        Student two = new Student(2, "name", "email", "gender", "class year",
                "ssn", "nationality", "race", 20, "written, email, " +
                "before midnight", 3, "zoom", "mornings, afternoons", 10,
                "a, b, c", "d, e, f", "aa, bb", "nothing");
        Student three = new Student(2, "name", "email", "gender", "class year",
                "ssn", "nationality", "race", 20, "written, email, call" +
                "before midnight", 3, "zoom, in person", "mornings, afternoons, nights", 10,
                "a, b, c, k", "d, e, f, l", "aa, bb", "swimming, sports");
        List<Student> studentsList = new ArrayList<Student>() {{
            add(one);
            add(two);
            add(three);
        } };
        BloomFiltersLoader loader = new BloomFiltersLoader(studentsList, 0.01);
        //HashMap<String, BloomFilterBuilder> result = loader.loadAllBlooms();
        //assertEquals(3, result.size());
    }

    private void runSparkServer(int port) {
        // set port to run the server on
        Spark.port(port);

        // specify location of static resources (HTML, CSS, JS, images, etc.)
        Spark.externalStaticFileLocation("src/main/resources/static");
    }
}
