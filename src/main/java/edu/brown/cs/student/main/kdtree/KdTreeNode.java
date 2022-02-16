package edu.brown.cs.student.main.kdtree;

import java.util.List;

public interface KdTreeNode<T>{
  List<Double> getData();
  int getId();
  T getLeft();
  T getRight();
  int getDimension();
  void setLeft(T object);
  void setRight(T object);
  void setDimension(int cd);
}
