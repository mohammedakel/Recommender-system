package edu.brown.cs.student.main;
import java.util.ArrayList;

public interface NearestNeighbor {
  ArrayList<Integer> findNeighbor(int k, int userId);
  void traverse(KdTreeNode current, KdTreeNode target, int n);
  double findDistance(KdTreeNode nodeOne, KdTreeNode nodeTwo);
}
