package edu.brown.cs.student.main.commands;

import edu.brown.cs.student.main.API.ApiAggregate;
import edu.brown.cs.student.main.API.ApiStudent;
import edu.brown.cs.student.main.API.InfoStudents;
import edu.brown.cs.student.main.API.MatchStudents;
import edu.brown.cs.student.main.REPL;
import edu.brown.cs.student.main.Student;

import java.io.IOException;
import java.util.*;
import java.util.HashMap;

import static java.lang.Double.parseDouble;


/**
 * a class responsible for aggregating all data regardless of the API type. stores a list of API students
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
        aggregator.aggregate("info");
        List<InfoStudents> infoStudents = aggregator.getInfoStudents();
        System.out.println("HERE " + infoStudents.size());
        aggregator.aggregate("match");
        List<MatchStudents> matchStudents = aggregator.getMatchStudents();
        System.out.println("HERE 2 " + matchStudents.size());
        List<Integer> studentsIds = new ArrayList<Integer>();
        List<ApiStudent> finalAPI = new ArrayList<ApiStudent>();
        for (InfoStudents student: infoStudents
              ) {
            int currentID = student.getId();
            studentsIds.add(currentID);
        }
        for (Integer ID:
             studentsIds) {
            String name = "";
            String class_year = "";
            String email = "";
            int years_experience = 0;
            String communication_style = "";
            int weekly_avail_hours = 0;
            String meeting_style = "";
            String meeting_time = "";
            String gender = "";
            String ssn = "";
            String nationality = "";
            String race = "";
            int software_eng_confidence = 0;
            for (InfoStudents s:
                 infoStudents) {
                if (s.getId() == ID) {
                    name = s.getName();
                    class_year = s.getClassYear();
                    email = s.getEmail();
                    years_experience = Integer.parseInt(s.getYearsExp());
                    communication_style = s.getCommunicationStyle();
                    weekly_avail_hours = Integer.parseInt(s.getWeeklyHours());
                    meeting_style = s.getMeetingStyle();
                    meeting_time = s.getMeetingTime();
                }
            }
            for (MatchStudents m:
                    matchStudents) {
                if (m.getId() == ID) {
                    gender = m.getGender();
                    ssn = m.getSSN();
                    nationality = m.getNationality();
                    race = m.getRace();
                }
            }
            ApiStudent temp = new ApiStudent(ID, name, class_year, email, years_experience, communication_style,
                    weekly_avail_hours, meeting_style, meeting_time, gender, ssn, nationality, race,
                    software_eng_confidence);
            finalAPI.add(temp);
        }
        System.out.println("Loaded the following " + finalAPI.size() + " students from both APIs:");
        for (ApiStudent s:
             finalAPI) {
            System.out.println(s.convertToString());
        }
        REPL.addCommandObject("api_aggregate_all", finalAPI);
    }
}
