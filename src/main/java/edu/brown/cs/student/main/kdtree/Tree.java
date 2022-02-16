package edu.brown.cs.student.main;

import java.util.Collections;
import java.util.List;

public class Tree {
  private int k = 3;
  private KdTreeNode root;

  public Tree(List<KdTreeNode> nodeList) {
    this.root = buildTree(0, nodeList.size() - 1, 0, nodeList);
  }

  public KdTreeNode buildTree(int start, int end, int cd, List<KdTreeNode> list) {
    if (start > end) {
      return null;
    }
    int mid = (start + end) / 2;
    int mid2 = (int) Math.ceil((double) (start + end) / 2);
    Collections.sort(list, new SortByData(cd));
    cd = (cd + 1) % k;
    KdTreeNode root = list.get(mid);
    root.setLeft(buildTree(start, mid - 1, cd, list.subList(start, mid)));
    root.setRight(buildTree(start, mid2 - 1, cd, list.subList(mid + 1, end + 1)));
    return root;
  }

  public KdTreeNode getRoot() {
    return root;
  }

}
