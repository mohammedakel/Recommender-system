package edu.brown.cs.student.main;

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
public class QueryBF implements REPL, Command {

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
    if (!REPL.bloomFilterExists()) { // check boolean
      System.out.println("ERROR: No Bloom Filter exists, run create_bf <element>");
    } else if (args.length < 2) {
      System.out.println("ERROR: Missing args, enter query_bf <element>");
    } else {
      String element = args[1];
      // call query element
    }
  }
}
