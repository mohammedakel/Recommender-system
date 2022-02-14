package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

/**
 * Class contains the overarching structure of a REPL and parses the system input. The code for
 * parsing using Pattern to separate by quotation marks was taken from:
 * https://stackoverflow.com/questions/366202/regex-for-splitting-a-string-using-space-when-not-
 * surrounded-by-single-or-double/366532#366532
 *
 * This class also contains other classes such as Load and NaiveNeighbors which will run the code
 * needed to fulfill the user stories.
 *
 * @author Hannah Jeon
 */
public class Repl {
  /**
   * Constructor of Repl creates a loop that takes in user input and continues to do so until otherwise
   * specified
   *
   * @throws IOException
   */
  public Repl() throws IOException {
    List<KdTreeNode> nodeList = new ArrayList();
    Student student1 = new Student(1,6,2,6);
    Student student2 = new Student(2,7,1,7);
    Student student3 = new Student(3,2,9,2);
    Student student4 = new Student(4,3,6,3);
    Student student5 = new Student(5,4,8,4);
    Student student6 = new Student(6,8,4,8);
    Student student7 = new Student(7,5,3,5);
    Student student8 = new Student(8,1,5,1);
    Student student9 = new Student(9,9,5,9);
    nodeList.add(student1);
    nodeList.add(student2);
    nodeList.add(student3);
    nodeList.add(student4);
    nodeList.add(student5);
    nodeList.add(student6);
    nodeList.add(student7);
    nodeList.add(student8);
    nodeList.add(student9);
    Tree tree = new Tree(nodeList);
    System.out.println(tree.getRoot().getId());
    System.out.println(student7.getLeft().getId());
    System.out.println(student7.getRight().getId());
    System.out.println(student4.getLeft().getId());
    System.out.println(student4.getRight().getId());
    System.out.println(student1.getLeft().getId());
    System.out.println(student1.getRight().getId());
    System.out.println(student3.getRight().getId());
    System.out.println(student6.getRight().getId());

  }
}
