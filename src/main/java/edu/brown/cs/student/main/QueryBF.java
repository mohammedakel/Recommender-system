package edu.brown.cs.student.main;

/**
 * The query_bf command querys a Bloom filter to see whether it might contain
 * a given element
 * Run query_bf [element]
 *
 * query_bf outputs whether the input element might have been inserted into the Bloom filter or
 * definitely has not been inserted into the Bloom filter
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
    BloomFilterBuilder bloomFilter =
        (BloomFilterBuilder) REPL.getCommandObject("create_bf"); //get bloom filter, if it exists
    if (bloomFilter == null) { // create_bf hasn't been called if null
      System.out.println("ERROR: No Bloom Filter exists, run create_bf <r> <n>");
    } else if (args.length != 2) {
      System.out.println("ERROR: Incorrect amount of args, run query_bf <element>");
    } else {
      String element = args[1];
      boolean contains = bloomFilter.mightContain(element); // check if bloom filter contains given element
      if (contains) {
        System.out.println('"' + element + '"' + " might be in the set.");
      }
      else {
        System.out.println('"' + element + '"' + " is definitely not in the set.");
      }
    }
  }
}
