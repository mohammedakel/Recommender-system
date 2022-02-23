package edu.brown.cs.student.main;

/**
 * The insert_bf command inserts elemented into a bloom filter
 * Run insert_bf <element>
 * <p>
 * It implements the REPL and Command interface, so it must add itself to the
 * commands hashMap and implement execute.
 *
 * @author kkmarcus
 */
public class InsertBF implements Command {

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  InsertBF() {
    String commandName = "insert_bf";
    this.addCommands(commandName, this);
  }

  /**
   * execute must check to see if a bloom filter has been created yet, otherwise
   * throw error
   *
   * @param args (array of strings)
   */
  public void execute(String[] args) {
    BloomFilterBuilder bloomFilter =
        (BloomFilterBuilder) REPL.getCommandObject("create_bf");
    if (bloomFilter == null) {
      System.out.println("ERROR: No Bloom Filter exists, run create_bf <r> <n>");
    } else if (args.length < 2) {
      System.out.println("ERROR: Missing args, enter insert_bf <element>");
    } else {
      String element = args[1];
      bloomFilter.add(element);
    }
  }
}

