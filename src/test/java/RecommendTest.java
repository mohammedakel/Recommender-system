package edu.brown.cs.student.main;

import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RecommendTest {

  private LoadKD load_kd;
  private LoadRecSys load_recsys;
  private Recommend recommend;
  private LoadHeaders load_headers;


  public RecommendTest() {

  }

  @Before
  public void setUp() {
    load_kd = new LoadKD();
    load_recsys = new LoadRecSys();
    recommend = new Recommend();
    load_headers = new LoadHeaders();
  }

  @After
  public void tearDown(){
    REPL.removeCommands("load_kd");
    REPL.removeCommands("load_recsys");
    REPL.removeCommands("recommend");
    REPL.removeCommands("load_headers");
    REPL.removeCommands("load_bf");
    REPL.removeCommands("similar_bf");
    REPL.removeCommands("create_bf");
    REPL.removeCommands("insert_bf");
    REPL.removeCommands("query_bf");
  }

  @Test
  public void testRecommend() throws IOException {
    String[] loadRec = {"recsys_load", "data/project1/proj1_studentTest2.csv", "file1" , "file2", "file3"};
    String[] recommendString  = {"recommend", "4", "1"};
    String[] headers = {"headers_load", "data/sprint2/header_types.csv"};


    load_headers.execute(headers);
    load_recsys.execute(loadRec);
    recommend.execute(recommendString);

    List<Map.Entry<Integer, Double>> normalizedList =
        (List<Map.Entry<Integer, Double>>) REPL.getCommandObject("recommend");

    int first_student_id = normalizedList.get(0).getKey();
    int second_student_id = normalizedList.get(1).getKey();
    int third_student_id = normalizedList.get(2).getKey();
    int fourth_student_id = normalizedList.get(3).getKey();

    assertEquals(2, first_student_id);
    assertEquals(3, second_student_id);
    assertEquals(5, third_student_id);
    assertEquals(4, fourth_student_id);

//    Tree studentTree = (Tree) REPL.getCommandObject("load_kd");
//    assertEquals(studentTree.getRoot().getId(),4);
  }
}
