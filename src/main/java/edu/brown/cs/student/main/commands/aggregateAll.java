package edu.brown.cs.student.main.commands;

import edu.brown.cs.student.main.API.ApiAggregate;
import edu.brown.cs.student.main.API.InfoStudents;
import edu.brown.cs.student.main.API.MatchStudents;
import edu.brown.cs.student.main.REPL;
import edu.brown.cs.student.main.Student;

import java.io.IOException;
import java.util.*;
import java.util.HashMap;

import static java.lang.Double.parseDouble;


/**
 * a class responsible for aggregating all data regardless of the type. stores a list of students
 *
 * */
public class aggregateAll implements Command{

    public aggregateAll() {
        String commandName = "api_aggregate_all";
        this.addCommands(commandName, this);
    }

    @Override
    public void execute(String[] args) throws IOException {
        ApiAggregate aggregator = new ApiAggregate();
        aggregator.aggregate("all");
        List<InfoStudents> infoStudents = aggregator.getInfoStudents();
        System.out.println("HERE " + infoStudents.size());
        List<MatchStudents> matchStudents = aggregator.getMatchStudents();
        System.out.println("HERE 2 " + matchStudents.size());
        List<Student> aggregatedStudentsData = new ArrayList<>();
        //  fields from info: id, name, year, ....., comunication, ....., zoom/inperson, time of the day
        //  fields from match api: id, name, gender, social security, nationality, race, .....
        /// fields from students:  public Student(int id, String name, String email, String gender,
        //                   String class_year, String ssn, String nationality, String race, double years,
        //                   String communciation_style, double hours, String meeting_style,
        //                   String meeting_time,
        //                   double confidence, String strengths, String weaknesses, String skills,
        //                   String interests)
        HashMap<Integer, HashMap<String, String>> idsToInfoData = new HashMap<>();
        HashMap<Integer, HashMap<String, String>> idsToMatchData = new HashMap<>();
        for (InfoStudents students:
             infoStudents) {
            HashMap<String, String> infoValues = new HashMap<>();
            int id = students.getId();
            String name = students.getName();
            String email = students.getEmail();
            String classYear = students.getClassYear();
            String yearsExperience = students.getYearsExp();
            String communicationStyle = students.getCommunicationStyle();
            String hours = students.getWeeklyHours();
            String meetingStyle = students.getMeetingStyle();
            String meetingTime = students.getMeetingTime();
            infoValues.put("name", name);
            infoValues.put("email", email);
            infoValues.put("classYear", classYear);
            infoValues.put("yearsExperience", yearsExperience);
            infoValues.put("communicationStyle", communicationStyle);
            infoValues.put("weeklyAvailableHours", hours);
            infoValues.put("meetingStyle", meetingStyle);
            infoValues.put("meetingTime", meetingTime);
            idsToInfoData.put(id, infoValues);
        }
        System.out.println("loaded " + idsToInfoData.size() + " from students info API");
        for (MatchStudents students: matchStudents){
            HashMap<String, String> infoValues = new HashMap<>();
            int id = students.getId();
            String gender = students.getGender();
            String ssn = students.getSSN();
            String nationality = students.getNationality();
            String race = students.getRace();
            String softwareConfidence = students.getSoftwareConfidence();
            infoValues.put("gender", gender);
            infoValues.put("ssn", ssn);
            infoValues.put("nationality", nationality);
            infoValues.put("race", race);
            infoValues.put("softwareConfidence", softwareConfidence);
            idsToMatchData.put(id, infoValues);
        }

        List<Student> result = new ArrayList<>();
        Set<Integer> infoIDs = idsToInfoData.keySet();
        Set<Integer> matchIDs = idsToMatchData.keySet();
        System.out.println("loaded " + infoIDs.size() + " from students info API");
        System.out.println("loaded " + matchIDs.size() + " from students info API");
        if (infoIDs.size() < matchIDs.size() ) {
            for (Integer id:
                 infoIDs) {
                int currentID = id;
                String name = idsToInfoData.get(id).get("name");
                String years = idsToInfoData.get(id).get("classYear");
                double yearsExperience = parseDouble(idsToInfoData.get(id).get("yearsExperience"));
                double hours = parseDouble(idsToInfoData.get(id).get("weeklyAvailableHours"));
                double confidence = parseDouble(idsToMatchData.get(id).get("softwareConfidence"));
                String email = idsToInfoData.get(id).get("email");
                String gender = idsToMatchData.get(id).get("gender");
                String ssn = idsToMatchData.get(id).get("ssn");
                String nationality = idsToMatchData.get(id).get("nationality");
                String race = idsToMatchData.get(id).get("race");
                String communicationStyle = idsToInfoData.get(id).get("communicationStyle");
                String meetingStyle = idsToInfoData.get(id).get("meetingStyle");
                String meetingTime = idsToInfoData.get(id).get("meetingTime");
                String strengths = "";
                String weakness = "";
                String skills = "";
                String interests = "";
                Student currentStudent = new Student(id, name, email, gender, years,
                        ssn, nationality, race, yearsExperience, communicationStyle, hours,
                        meetingStyle, meetingTime, confidence, strengths, weakness,
                        skills, interests);
                result.add(currentStudent);
            }

            System.out.println("processed data for " + result.size() + " from all APIS");
            REPL.addCommandObject("aggregate_all", result);
        }






    }
}
