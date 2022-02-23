package edu.brown.cs.student.main;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Tree implements NearestNeighbor {
  private int k = 3;
  private KdTreeNode root;
  private List<KdTreeNode> nodeList;
  private HashMap<KdTreeNode, Double> distanceMap;
  private PriorityQueue<KdTreeNode> neighborsPq;

  public Tree(List<KdTreeNode> nodeList) {
    distanceMap = new HashMap<>();
    this.nodeList = nodeList;
    this.root = buildTree(0, this.nodeList.size() - 1, 0, nodeList);
  }

  public KdTreeNode buildTree(int start, int end, int cd, List<KdTreeNode> list) {
    if (start > end) {
      return null;
    }
    int mid = (start + end) / 2;
    int mid2 = (int) Math.ceil((double) (start + end) / 2);
    Collections.sort(list, new SortByData(cd));
    KdTreeNode root = list.get(mid);
    root.setDimension(cd);
    cd = (cd + 1) % k;
    List<KdTreeNode> leftList = list.subList(start, mid);
    List<KdTreeNode> rightList = list.subList(mid + 1, end + 1);
    root.setLeft(buildTree(start, mid - 1, cd, leftList));
    root.setRight(buildTree(start, mid2 - 1, cd, rightList));
    return root;
  }

  public KdTreeNode getRoot() {
    return root;
  }

  public double findDistance(KdTreeNode nodeOne, KdTreeNode nodeTwo) {
    double distance = 0;
    for (int i = 0; i < k; i++) {
      distance += Math.pow(nodeOne.getData().get(i) - nodeTwo.getData().get(i), 2);
    }
    return Math.sqrt(distance);
  }

  public ArrayList<Integer> findNeighbor(int n, int userId) {
    ArrayList<Integer> neighbors = new ArrayList<>();

    //Handle potential edge cases
    if (getRoot() == null) {
      System.out.println("ERROR: kd tree is empty");
      return null;
    } else if (n == 0) {
      return neighbors;
    } else if (n < 0) {
      System.out.println("ERROR: number of neighbors is negative");
      return neighbors;
    }

    KdTreeNode targetNode = null;
    //Find the target node from user ID
    for (int i = 0; i < nodeList.size(); i++) {
      if (nodeList.get(i).getId() == userId) {
        targetNode = nodeList.get(i);
      }
    }

    //Instantiate priority queue with appropriate comparator that takes in n number of objects
    neighborsPq = new PriorityQueue<>(n, (distanceOne, distanceTwo) -> {
      if (distanceMap.get(distanceOne) > distanceMap.get(distanceTwo)) {
        return -1;
      } else if (distanceMap.get(distanceOne) < distanceMap.get(distanceTwo)) {
        return 1;
      } else {
        return 0;
      }
    });

    if (targetNode != null) {
      traverse(root, targetNode, n);
    } else {
      System.out.println("ERROR: ID not in kd tree");
      return neighbors;
    }

    if (neighborsPq.size() == 1) {
      neighbors.add(neighborsPq.remove().getId());
    } else {
      //remove the targetNode from the neighbors list
      neighborsPq.remove(targetNode);
      while (!neighborsPq.isEmpty()) {
        neighbors.add(neighborsPq.remove().getId());
      }
    }
    //reverse priority queue
    Collections.reverse(neighbors);
    return neighbors;
  }

  public void traverse(KdTreeNode current, KdTreeNode target, int n) {
    double distance = this.findDistance(current, target);
    //store distance in hash map
    distanceMap.put(current, distance);

    //if the distance for the current node is lower than the distance for any of the stored nodes
    //with the target point (or if the collection of stored nodes is not complete)
    if ((neighborsPq.size() < n + 1)) {
      //add to the priority queue - this is an edge case
      neighborsPq.add(current);
    } else if ((!neighborsPq.isEmpty()) && distance < findDistance(neighborsPq.peek(), target)) {
      //update the stored nodes accordingly
      neighborsPq.remove();
      neighborsPq.add(current);
    }

    //find the relevant axis according to the depth
    double currentRelevantAxis = (double) current.getData().get(current.getDimension());
    double targetRelevantAxis = (double) target.getData().get(current.getDimension());
    double relevantAxisDistance = Math.abs(targetRelevantAxis - currentRelevantAxis);

    //if the distance between the farthest stored node and the target point is greater than the
    //difference between the current node and target point on the relevant axis
    double farthestEuclideanDistance = findDistance(neighborsPq.peek(), target);
    if ((!this.neighborsPq.isEmpty()) && farthestEuclideanDistance >= relevantAxisDistance) {
      //recur on both children of the current node
      if (current.getLeft() != null) {
        this.traverse(current.getLeft(), target, n);
      }
      if (current.getRight() != null) {
        this.traverse(current.getRight(), target, n);
      }
    } else { //if the previous condition is false then check:
      //if the current node's coordinate on the relevant axis is less than the
      //target's coordinate on the relevant axis
      if (currentRelevantAxis < targetRelevantAxis) {
        //recur on the right child
        if (current.getRight() != null) {
          this.traverse(current.getRight(), target, n);
        }
        //else if the current node's coordinate on the relevant axis is greater than
        //the target's coordinate on the relevant axis
      } else if (currentRelevantAxis > targetRelevantAxis) {
        //recur on the left child
        if (current.getLeft() != null) {
          this.traverse(current.getLeft(), target, n);
        }
      }
    }
  }

}
