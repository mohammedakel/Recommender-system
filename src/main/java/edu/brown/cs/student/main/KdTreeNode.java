package edu.brown.cs.student.main;

import java.util.List;

/**
 * Interface for storing tree-related functions to be used when building a tree/finding knn.
 * Allows the kdtree to be generic.
 */

public interface KdTreeNode{
  List<Double> getData();
  int getId();
  KdTreeNode getLeft();
  KdTreeNode getRight();
  int getDimension();
  void setLeft(KdTreeNode object);
  void setRight(KdTreeNode object);
  void setDimension(int cd);
}
