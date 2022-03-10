package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KDTest {

  private LoadKD load_kd;

  public KDTest(){

  }
  @Before
  public void setUp() {
    load_kd = new LoadKD();
  }

  @After
  public void tearDown(){
    REPL.removeCommands("load_kd");
  }

  @Test
  public void testLoadRoot() throws IOException {
    String[] load = {"load_kd", "data/project1/proj1_small.csv"};
    String filePath = "data/project1/proj1_small.csv";
    CSVParser newParser = new CSVParser(filePath, Student::new, true); // instantiate parser w type of object specified
    newParser.readLine();
    List<KdTreeNode> students = newParser.getListOfObjects(); // get list of KdTreeNodes
    Tree studentTree = new Tree(students); // create new tree
//    LoadKD loadKd = new LoadKD();
//    loadKd.execute(load);
//    Tree studentTree = (Tree) REPL.getCommandObject("load_kd");
    assertEquals(studentTree.getRoot().getId(),4);
  }

  @Test
  public void testLoadLeftRight() throws IOException {
    String[] load = {"load_kd", "data/project1/proj1_small.csv"};
    String filePath = "data/project1/proj1_small.csv";
//    LoadKD loadKd = new LoadKD();
//    loadKd.execute(load);
    load_kd.execute(load);
    Tree studentTree = (Tree) REPL.getCommandObject("load_kd");
    assertEquals(studentTree.getRoot().getLeft().getId(),13);
    assertEquals(studentTree.getRoot().getRight().getId(),16);
  }

  @Test
  public void testDimension() throws IOException {
    String[] load = {"load_kd", "data/project1/proj1_small.csv"};
    String filePath = "data/project1/proj1_small.csv";
//    LoadKD loadKd = new LoadKD();
//    loadKd.execute(load);
    load_kd.execute(load);
    Tree studentTree = (Tree) REPL.getCommandObject("load_kd");
    assertEquals(studentTree.getRoot().getDimension(),0);
    assertEquals(studentTree.getRoot().getLeft().getDimension(),1);
  }

  @Test
  public void testKnn() throws IOException {
    String[] load = {"load_kd", "data/project1/proj1_small.csv"};
    String filePath = "data/project1/proj1_small.csv";
//    LoadKD loadKd = new LoadKD();
//    loadKd.execute(load);
    load_kd.execute(load);
    Tree studentTree = (Tree) REPL.getCommandObject("load_kd");
    ArrayList<Integer> list = studentTree.findNeighbor(3,1);
    assertEquals(list.get(0),Integer.valueOf(15));
    assertEquals(list.get(1),Integer.valueOf(10));
  }
  @Test
  public void testNullKnn() throws IOException {
    String[] load = {"load_kd", "data/project1/proj1_small.csv"};
    String filePath = "data/project1/proj1_small.csv";
//    LoadKD loadKd = new LoadKD();
//    loadKd.execute(load);
    load_kd.execute(load);
    Tree studentTree = (Tree) REPL.getCommandObject("load_kd");
    ArrayList<Integer> list = studentTree.findNeighbor(0,1);
    assert(list.isEmpty());
  }
}
