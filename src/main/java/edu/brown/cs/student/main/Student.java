package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.List;

public class Student implements KdTreeNode<KdTreeNode>{
  int cd;
  int id;
  double years;
  double hours;
  double confidence;
  KdTreeNode right;
  KdTreeNode left;

  public Student(int id, double years, double hours, double confidence){
    this.id = id;
    this.years = years;
    this.hours = hours;
    this.confidence = confidence;

  }
  public int getId(){
    return id;
  }
  @Override
  public List<Double> getData(){
    List<Double> dataList = new ArrayList();
    dataList.add(years);
    dataList.add(hours);
    dataList.add(confidence);
    return dataList;
  }
  @Override
  public KdTreeNode getRight(){
    return right;
  }
  @Override
  public KdTreeNode getLeft(){
    return left;
  }
  @Override
  public int getDimension(){
    return cd;
  }
  @Override
  public void setRight(KdTreeNode right){
    this.right = right;
  }
  @Override
  public void setLeft(KdTreeNode left){
    this.left = left;
  }
  @Override
  public void setDimension(int cd){
    this.cd = cd;
  }
}
