package edu.brown.cs.student.main;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;


/**
 * Input and Output learned from https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
 * https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
 * how to test system: https://stackoverflow.com/questions/47144489/testing-system-out-println-not-working-but-system-out-print-using-junit
 */
public class CommandsTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream standardOut = System.out;
  private final InputStream systemIn = System.in;
  private ByteArrayInputStream testInput;

  public CommandsTest() {
  }

  private void provideInput(String data) {
    testInput = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
    System.setIn(testInput);


  }

  /**
   * Initializing Test: Reassign standard output stream to outContent
   */
  @Before
  public void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  /**
   * The standard output stream is a shared static resource used by other parts of system,
   * so we need to restore it to its original state when test ends
   */
  @After
  public void tearDown() {
    System.setOut(new PrintStream(standardOut));
  }


  @Test
  public void testAddCommands() {

    Command newCommand = new Command() {
      @Override
      public void execute(String[] args) throws IOException {
        System.out.println("HI");
      }
    };
    newCommand.addCommands("commandName", newCommand);

    //Assert.assertEquals("Created new command: commandName" + System.getProperty("line.separator"), outContent.toString());
    Assert.assertTrue(outContent.toString().startsWith("Created new command: commandName"));
    outContent.reset();  // clear output stream

    Command newCommand2 = new Command() {
      @Override
      public void execute(String[] args) throws IOException {
        System.out.println("HI");
      }
    };
    newCommand2.addCommands("commandName", newCommand2);

    //Assert.assertEquals("ERROR: Command already exists using that name, choose different name", outContent.toString());
    Assert.assertTrue(outContent.toString()
        .startsWith("ERROR: Command already exists using that name, choose different name"));

    outContent.reset();

    newCommand2.addCommands("commandName2", newCommand2); // add unique name

    //Assert.assertEquals("Created new command: commandName2", outContent.toString());
    Assert.assertTrue(outContent.toString().startsWith("Created new command: commandName2"));

  }

  @Test
  public void removeCommand() {
    Command newCommand = new Command() {
      @Override
      public void execute(String[] args) throws IOException {
        System.out.println("HI");
      }
    };
    newCommand.addCommands("commandToRemoveName", newCommand);
    Assert.assertTrue(outContent.toString().startsWith("Created new command: commandToRemoveName"));
    outContent.reset();  // clear output stream

    newCommand.removeCommands("commandToRemoveNam", newCommand);
    Assert.assertTrue(outContent.toString()
        .startsWith("ERROR: No command with name: commandToRemoveNam exists, cannot remove"));
    outContent.reset();

    newCommand.removeCommands("commandToRemoveName", newCommand);
    Assert.assertTrue(outContent.toString().startsWith("Removed command: commandToRemoveName"));
  }

  @Test
  public void commandDoesntExist() {
    Command newCommand = new Command() {
      @Override
      public void execute(String[] args) throws IOException {
        System.out.println("HI");
      }
    };
    newCommand.addCommands("", newCommand);
    Assert.assertTrue(outContent.toString()
        .startsWith("ERROR: No command entered, command name must be non-empty string"));
    //Assert.assertEquals("ERROR: No command entered, command name must be non-empty string", outContent.toString());
  }

//  @Test
//  public void addEmptyCommand() throws IOException {
//    final String testString = "load";
//    REPL.runREPL();
//    provideInput(testString);
//
//    //Assert.assertEquals("ERROR: No command with that name exists", outContent.toString());
//
//    final String testString2 = "exit";
//    provideInput(testString2);
//    System.exit(1);
//
//
////    Command newCommand3 = new Command() {
////      @Override
////      public void execute(String[] args) throws IOException {
////        System.out.println("HI");
////      }
////    };
////    newCommand3.addCommands("load", newCommand3);
//
//    //Assert.assertEquals("", outContent.toString());
//  }

}
