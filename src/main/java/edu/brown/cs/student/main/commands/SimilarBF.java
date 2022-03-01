package edu.brown.cs.student.main.commands;

import edu.brown.cs.student.main.BloomFilterr.BloomFilterBuilder;
import edu.brown.cs.student.main.BloomFilterr.SBLTuple;
import edu.brown.cs.student.main.BloomFilterr.SimilarityGenerator;
import edu.brown.cs.student.main.REPL;
import edu.brown.cs.student.main.commands.Command;

import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Run similar_bf [k] [user_id]
 * <p>
 * similar_bf outputs the k most similar students to the student whose ID
 * is user_id based on the Bloom filter similarity comparisons
 * <p>
 * It implements the REPL and Command interface, so it must add itself to the
 * commands hashMap and implement execute.
 *
 * @author kkmarcus
 */
public class SimilarBF implements Command {

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  public SimilarBF() {
    String commandName = "similar_bf";
    this.addCommands(commandName, this);
  }

  /**
   * execute must check if there are the correct amount and type of args
   * and call similar_bf method
   *
   * @param args (array of strings)
   */
  public void execute(String[] args) throws IOException {
    HashMap<String, BloomFilterBuilder> idsToBlooms =
        (HashMap<String, BloomFilterBuilder>) REPL.getCommandObject("load_bf");
    if (idsToBlooms == null) {
      System.out.println("ERROR: No bloom filter data for students, run load_bf [path-to-data] first");
    } else if (args.length != 3) {
      System.out.println("ERROR: Incorrect amount of args, run similar_bf <k> <user_id>");
    } else {
      try {
        int k = Integer.parseInt(args[1]);
        int user_id = Integer.parseInt((args[2]));
        SimilarityGenerator generator = new SimilarityGenerator(idsToBlooms, k);
        PriorityQueue<SBLTuple> similarityResult = generator.findSimilar(user_id);
        printNeighbors(similarityResult, k);
      } catch (NumberFormatException e) {
        System.out.println("ERROR: Invalid arg(s). <k> and <some_user_id> must be Integers");
      }
    }
  }

  /**
   * Prints out the k most similar students to the student whose ID
   * is user_id
   * @param similarityResult
   * @param k
   */
  public void printNeighbors(PriorityQueue<SBLTuple> similarityResult, int k){
    while(k>0) {
      SBLTuple current = similarityResult.poll();
      System.out.println(current.id);
      k--;
    }
  }
}
