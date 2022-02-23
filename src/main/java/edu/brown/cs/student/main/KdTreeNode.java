package edu.brown.cs.student.main;

import java.util.List;

public interface KdTreeNode {
  List<Double> getData();
  int getId();
  KdTreeNode getLeft();
  KdTreeNode getRight();
  int getDimension();
  void setLeft(KdTreeNode object);
  void setRight(KdTreeNode object);
  void setDimension(int cd);
}


