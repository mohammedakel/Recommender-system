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


public class LoadRecSysTest {

  private LoadKD load_kd;
  private LoadRecSys load_recsys;
  private Recommend recommend;
  private LoadHeaders load_headers;

  public LoadRecSysTest() {

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



}
