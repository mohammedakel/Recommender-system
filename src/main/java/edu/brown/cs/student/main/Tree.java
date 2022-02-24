package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class is the main class for building a tree. It implements NearestNeighbor interface for
 * calculating distance between nodes and returning a list of closest neighbors id
 */
public class Tree implements NearestNeighbor {
  private int k;
  private KdTreeNode root;
  private List<KdTreeNode> nodeList;
  private HashMap<KdTreeNode, Double> distanceMap;
  private PriorityQueue<KdTreeNode> neighborsPq;

  /**
   * The constructor instantiates the hashmap for later use and takes in the nodeList, which is the
   * list of students, to build the tree using buildTree().
   * @param nodeList List of student objects parsed from csv file
   */
  public Tree(List<KdTreeNode> nodeList){
    k = 3;
    distanceMap = new HashMap<>();
    this.nodeList = nodeList;
    this.root=buildTree(0,this.nodeList.size()-1,0,nodeList);
  }

  /**
   * buildTree recursively builds a balanced k dimensional tree using the given list of nodes
   * @param start starting index
   * @param end ending index
   * @param cd current dimension of the tree
   * @param list list of nodes
   * @return KdTreeNode root node
   */
  public KdTreeNode buildTree(int start, int end, int cd, List<KdTreeNode> list){
    if(start > end){
      return null;
    }
    int mid = (start + end)/2;
    int mid2 = (int) Math.ceil((double)(start + end)/2);
    Collections.sort(list,new SortByData(cd));
    KdTreeNode newRoot = list.get(mid);
    newRoot.setDimension(cd);
    cd = (cd+1)%k;
    List<KdTreeNode> leftList = list.subList(start,mid);
    List<KdTreeNode> rightList = list.subList(mid+1,end+1);
    newRoot.setLeft(buildTree(start,mid-1,cd,leftList));
    newRoot.setRight(buildTree(start,mid2-1,cd,rightList));
    return newRoot;
  }

  /**
   * returns the root node of the tree
   * @return KdTreeNode root node
   */
  public KdTreeNode getRoot(){
    return root;
  }

  /**
   * Finds the Euclidean distance between the two given nodes and returns it
   * @param nodeOne First node
   * @param nodeTwo Second node
   * @return double Euclidean distance between two nodes
   */
  public double findDistance(KdTreeNode nodeOne, KdTreeNode nodeTwo){
    double distance = 0;
    for (int i = 0; i < k; i++) {
      distance += Math.pow(nodeOne.getData().get(i) - nodeTwo.getData().get(i), 2);
    }
    return Math.sqrt(distance);
  }

  /**
   * This function actually finds and compiles a list of Ids for the closest neighbors using helper
   * function traverse() and returns the list to be printed out.
   * @param n number of neighbors
   * @param userId target node
   * @return ArrayList<Integer> list of Id for nearest neighbors
   */
  public ArrayList<Integer> findNeighbor(int n, int userId){
    ArrayList<Integer> neighbors = new ArrayList<>();

    //Handle potential edge cases
    if (getRoot() == null) {
      System.out.println("ERROR: kd tree is empty");
      return null;
    } else if (n == 0) {
      return neighbors;
    } else if (n < 0){
      System.out.println("ERROR: number of neighbors is negative");
      return neighbors;
    }

    KdTreeNode targetNode = null;
    //Find the target node from user ID
    for(int i = 0; i < nodeList.size(); i++){
      if (nodeList.get(i).getId() == userId) {
        targetNode = nodeList.get(i);
      }
    }

    neighbors.clear();
    distanceMap.clear();

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
      System.out.println("ERROR: Student does not exist");
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

  /**
   * Traverses through the tree to calculate and find the nearest node and stores it in priority
   * queue
   * @param current current node
   * @param target target node
   * @param n number of neighbors
   */
  public void traverse(KdTreeNode current, KdTreeNode target, int n){
    double distance = this.findDistance(current, target);
    //store distance in hash map
    distanceMap.put(current, distance);

    //Case 1: The collection of nodes is incomplete or distance for the current node is lower than
    // the distance for any of the stored
    if ((neighborsPq.size() < n + 1)) {
      neighborsPq.add(current);
    } else if ((!neighborsPq.isEmpty()) && distance < findDistance(neighborsPq.peek(), target)) {
      neighborsPq.remove();
      neighborsPq.add(current);
    }

    //find the relevant axis distance
    double currentRelevantAxis = current.getData().get(current.getDimension());
    double targetRelevantAxis = target.getData().get(current.getDimension());
    double relevantAxisDistance = Math.abs(targetRelevantAxis - currentRelevantAxis);

    //Case 2: distance between the farthest stored node and the target point is greater than the
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
    } else {
      //Case 3: if the current node's coordinate on the relevant axis is less than the
      //target's coordinate on the relevant axis
      if (currentRelevantAxis< targetRelevantAxis) {
        //recur on the right child
        if (current.getRight() != null) {
          this.traverse(current.getRight(), target, n);
        }
        //Case 4: if the current node's coordinate on the relevant axis is greater than
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
