package edu.brown.cs.student.main;

import java.util.Comparator;
import java.util.List;

public class SortByData implements Comparator<KdTreeNode> {
  private int cd;

  public SortByData(int cd) {
    this.cd = cd;
  }

  public int compare(KdTreeNode node1, KdTreeNode node2) {
    List<Double> list1 = node1.getData();
    List<Double> list2 = node2.getData();
    if (list1.get(cd) > list2.get(cd)) {
      return 1;
    } else if (list1.get(cd) < list2.get(cd)) {
      return -1;
    } else {
      return 1;
    }

  }
}
