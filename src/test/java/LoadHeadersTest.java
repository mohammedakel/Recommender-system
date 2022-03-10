package edu.brown.cs.student.main;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class LoadHeadersTest {

  public LoadHeadersTest() {

  }

  @Test
  public void testQualitativeQuantitative() throws IOException {
    String[] load = {"headers_load", "data/sprint2/header_types.csv"};
    LoadHeaders loadHeaders = new LoadHeaders();

    loadHeaders.execute(load);

    HashMap<String, String> headers =
        (HashMap<String, String>) REPL.getCommandObject("headers_load");


    Assert.assertTrue(headers.containsKey("id"));
    Assert.assertTrue(headers.get("id").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("name"));
    Assert.assertTrue(headers.get("name").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("email"));
    Assert.assertTrue(headers.get("email").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("gender"));
    Assert.assertTrue(headers.get("gender").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("class_year"));
    Assert.assertTrue(headers.get("class_year").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("ssn"));
    Assert.assertTrue(headers.get("ssn").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("nationality"));
    Assert.assertTrue(headers.get("nationality").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("race"));
    Assert.assertTrue(headers.get("race").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("years_experience"));
    Assert.assertTrue(headers.get("years_experience").equals("quantitative"));
    Assert.assertTrue(headers.containsKey("communication_style"));
    Assert.assertTrue(headers.get("communication_style").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("weekly_avail_hours"));
    Assert.assertTrue(headers.get("weekly_avail_hours").equals("quantitative"));
    Assert.assertTrue(headers.containsKey("meeting_style"));
    Assert.assertTrue(headers.get("meeting_style").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("meeting_time"));
    Assert.assertTrue(headers.get("meeting_time").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("software_engn_confidence"));
    Assert.assertTrue(headers.get("software_engn_confidence").equals("quantitative"));
    Assert.assertTrue(headers.containsKey("strengths"));
    Assert.assertTrue(headers.get("strengths").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("weaknesses"));
    Assert.assertTrue(headers.get("weaknesses").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("skills"));
    Assert.assertTrue(headers.get("skills").equals("qualitative"));
    Assert.assertTrue(headers.containsKey("interests"));
    Assert.assertTrue(headers.get("interests").equals("qualitative"));


    Assert.assertFalse(headers.containsKey("meeting_times"));
    Assert.assertFalse(headers.get("meeting_time").equals("quantitative"));
    Assert.assertFalse(headers.containsKey("races"));
    Assert.assertFalse(headers.get("race").equals("quantitative"));
  }
}
