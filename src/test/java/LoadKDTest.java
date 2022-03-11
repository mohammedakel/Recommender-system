package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;

public class LoadKDTest {

  @Test
  public void testValidInput() throws IOException {
    String[] load = {"load_kd", "data/project1/proj1_small.csv"};
    LoadKD loadKd = new LoadKD();
    loadKd.execute(load);
    Tree studentTree = (Tree) REPL.getCommandObject("load_kd");
    assertEquals(studentTree.getRoot().getId(),4);
    REPL.removeCommands("load_kd");
  }
}
