package edu.brown.cs.student.main;

import java.util.ArrayList;

/**
 * Interface for storing nearest neighbor function so people can write their own distance function
 * for finding nearest neighbor with their own metrics.
 */

public interface NearestNeighbor {
  ArrayList<Integer> findNeighbor(int k, int userId);
  void traverse(KdTreeNode current, KdTreeNode target, int n);
  double findDistance(KdTreeNode nodeOne, KdTreeNode nodeTwo);
}
