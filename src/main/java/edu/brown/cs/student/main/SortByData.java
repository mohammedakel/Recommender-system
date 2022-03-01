package edu.brown.cs.student.main;

import java.util.Comparator;
import java.util.List;

/**
 * This class implements Comparator interface to create a comparator that can be used to sort each
 * node in a list by their relevant axis coordinate. An instance of this class will be created
 * inside Collection.sort() method in Tree class.
 */
public class SortByData implements Comparator<KdTreeNode>, REPL {
  private int cd;


  public SortByData(int cd) {
    this.cd = cd;
  }

  /**
   * Compare takes in two KdTreeNode objects and compares the relevant axis coordinate. It returns 1
   * if first node is greater or equal to second node and -1 if less than second node.
   *
   * @param node1 First node object
   * @param node2 Second node object
   * @return int result which could be 1 or -1
   */
  public int compare(KdTreeNode node1, KdTreeNode node2) {
    List<Double> list1;
    List<Double> list2;
    if (REPL.getCommandObject("headers_load") != null) {
      list1 = node1.getQuantitativeData(); // use quantitative data struct
      list2 = node2.getQuantitativeData();
    } else {
      list1 = node1.getData(); // else default
      list2 = node2.getData();
    }

    if (list1.get(cd) > list2.get(cd)) {
      return 1;
    } else if (list1.get(cd) < list2.get(cd)) {
      return -1;
    } else {
      return 1;
    }

  }
}
