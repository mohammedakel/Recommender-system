package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import edu.brown.cs.student.main.kdtree.KdTreeNode;
import edu.brown.cs.student.main.kdtree.Student;
import edu.brown.cs.student.main.kdtree.Tree;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JUnitTest {

  @Test
  public void testLoad() {
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
    assertEquals(tree.getRoot().getId(),7);
    assertEquals(student7.getLeft().getId(),4);
    assertEquals(student7.getRight().getId(),1);
  }

}
