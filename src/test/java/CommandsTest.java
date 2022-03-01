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
import java.util.HashMap;


/**
 * Input and Output learned from https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
 * https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
 * How to test system: https://stackoverflow.com/questions/47144489/testing-system-out-println-not-working-but-system-out-print-using-junit
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


  /**
   * Test addCommands method
   */
  @Test
  public void testAddCommands() {

    Command newCommand = new Command() {
      @Override
      public void execute(String[] args) throws IOException {
      }
    };
    newCommand.addCommands("commandName", newCommand);
    Assert.assertTrue(outContent.toString().startsWith("Created new command: commandName"));
    Assert.assertTrue(REPL.checkCommandExists("commandName"));
    outContent.reset();  // clear output stream

    Command newCommand2 = new Command() {
      @Override
      public void execute(String[] args) throws IOException {
      }
    };
    newCommand2.addCommands("commandName", newCommand2);

    Assert.assertTrue(outContent.toString()
        .startsWith("ERROR: Command already exists using that name, choose different name"));

    outContent.reset();

    newCommand2.addCommands("commandName2", newCommand2); // add unique name

    Assert.assertTrue(outContent.toString().startsWith("Created new command: commandName2"));

    Assert.assertTrue(REPL.checkCommandExists("commandName2"));
  }

  /**
   * Test removeCommand method
   */
  @Test
  public void testRemoveCommand() {
    Command newCommand = new Command() {
      @Override
      public void execute(String[] args) throws IOException {
      }
    };
    newCommand.addCommands("commandToRemoveName", newCommand);
    Assert.assertTrue(outContent.toString().startsWith("Created new command: commandToRemoveName"));
    Assert.assertTrue(REPL.checkCommandExists("commandToRemoveName"));
    outContent.reset();  // clear output stream

    newCommand.removeCommands("commandToRemoveNam", newCommand);
    Assert.assertTrue(outContent.toString()
        .startsWith("ERROR: No command with name: commandToRemoveNam exists, cannot remove"));
    outContent.reset();

    newCommand.removeCommands("commandToRemoveName", newCommand);
    Assert.assertTrue(outContent.toString().startsWith("Removed command: commandToRemoveName"));
    Assert.assertFalse(REPL.checkCommandExists("commandToRemoveName"));
  }

  @Test
  public void commandDoesntExist() {
    Command newCommand = new Command() {
      @Override
      public void execute(String[] args) throws IOException {
      }
    };
    newCommand.addCommands("", newCommand);
    Assert.assertFalse(REPL.checkCommandExists(""));
    Assert.assertTrue(outContent.toString()
        .startsWith("ERROR: No command entered, command name must be non-empty string"));
  }

  @Test
  public void getCommandObject() throws IOException {
    HashMap<String, String> headers =
        (HashMap<String, String>) REPL.getCommandObject("headers_load");
    Assert.assertTrue(headers == null);
   //Assert.assertTrue(outContent.toString().startsWith("ERROR: No command with that name exists"));
    outContent.reset();

    String[] load = {"headers_load", "data/sprint2/header_types.csv"};
    LoadHeaders loadHeaders = new LoadHeaders();
    loadHeaders.execute(load);
    Assert.assertTrue(outContent.toString().startsWith("Created new command: headers_load"));
    Assert.assertTrue(REPL.checkCommandExists("headers_load"));
    outContent.reset();  // clear output stream

//    HashMap<String, String> headers =
//        (HashMap<String, String>) REPL.getCommandObject("headers_load");
//
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
