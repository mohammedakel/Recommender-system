package edu.brown.cs.student.main;

import edu.brown.cs.student.main.bloomfilter.BloomFilterBuilder;

/**
 * The query_bf command querys a Bloom filter to see whether it might contain
 * a given element
 * Run query_bf <element>
 *
 *  It implements the REPL and Command interface, so it must add itself to the
 *  REPL hashMap and implement execute.
 *
 * @author kkmarcus
 */
public class QueryBF implements Command {

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  QueryBF() {
    String commandName = "query_bf";
    this.addCommands(commandName, this);
  }

  /**
   * execute must check to see if a bloom filter has been created yet, otherwise
   * throw error
   * @param args (array of strings)
   */
  public void execute(String[] args) {
    BloomFilterBuilder<String> bloomFilter =
        (BloomFilterBuilder<String>) REPL.getCommandObject("create_bf");
    if (bloomFilter == null) {
      System.out.println("ERROR: No Bloom Filter exists, run create_bf <r> <n>");
    } else if (args.length < 2) {
      System.out.println("ERROR: Missing args, enter query_bf <element>");
    } else {
      String element = args[1];
      bloomFilter.mightContain(element);
    }
  }
}