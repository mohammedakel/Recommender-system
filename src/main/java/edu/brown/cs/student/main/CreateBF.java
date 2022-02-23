package edu.brown.cs.student.main;

import edu.brown.cs.student.main.bloomfilter.BloomFilter;
import edu.brown.cs.student.main.bloomfilter.BloomFilterBuilder;

/**
 * The create_bf command construct an empty, generic Bloom filter given:
 * r, a desired false positive rate
 * n, the expected maximum number of elements the Bloom filter will contain
 * Run create_bf <r> <n>
 * <p>
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
    if (args.length < 2) {
      System.out.println("ERROR: Missing args, enter create_bf <r> <n> or create_bf <n>");
    } else if (args.length > 3) {
      System.out.println("ERROR: Too many args, enter create_bf <r> <n> or create_bf <n>");
    } else {
      int n = 0;
      if (args.length == 3) { // create_bf <r> <n>
        try {
          double r = Double.parseDouble(args[1]);
          n = Integer.parseInt(args[2]);
          if (r > 0 && r < 1) {
            BloomFilterBuilder bloom = new BloomFilterBuilder(n, r);
            REPL.addCommandObject("create_bf", bloom);
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
          REPL.addCommandObject("create_bf", bloom);
        } catch (NumberFormatException e) {
          System.out.println("ERROR: Invalid arg. <n> must be an Integer");
        }
      }
    }
  }
}
