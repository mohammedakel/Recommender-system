package edu.brown.cs.student.main;

/**
 * The create_bf command construct an empty, generic Bloom filter given:
 * r, a desired false positive rate
 * n, the expected maximum number of elements the Bloom filter will contain
 * Run create_bf [r] [n]
 *
 * Create_bf outputs the Bloom filter’s bitset of length m based on the input
 * values for r and n.
 *
 * It implements the REPL and Command interface, so it must add itself to the
 * commands hashMap and implement execute.
 *
 * @author kkmarcus
 */
public class CreateBF implements Command {

  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  CreateBF() {
    String commandName = "create_bf";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) {
    if (args.length != 3 && args.length != 2) {
      System.out.println("ERROR: Incorrect amount of args, run create_bf <r> <n> or create_bf <n>");
    } else {
      int n = 0;
      if (args.length == 3) { // create_bf <r> <n>
        try {
          double r = Double.parseDouble(args[1]);
          n = Integer.parseInt(args[2]);
          if (r > 0 && r < 1) {
            BloomFilterBuilder bloom = new BloomFilterBuilder(n, r);
            REPL.addCommandObject("create_bf",
                bloom); // add the object to REPL hashmap so it can be accessed by other methods
            String bitset = bloom.toBinaryString();
            System.out.println(bitset);
          } else {
            System.out.println("ERROR: r must be between 0 and 1 (create_bf <r> <n>)");
          }
        } catch (NumberFormatException e) {
          System.out.println("ERROR: Invalid arg(s). <n> must be an Integer, <r> must be a Double");
        }
      } else {
        try {
          n = Integer.parseInt(args[1]);
          BloomFilterBuilder bloom = new BloomFilterBuilder(n);
          REPL.addCommandObject("create_bf", bloom); // add object to REPL hashmap
          String bitset = bloom.toBinaryString();
          System.out.println(bitset);
        } catch (NumberFormatException e) {
          System.out.println("ERROR: Invalid arg. <n> must be an Integer");
        }
      }
    }
  }
}
