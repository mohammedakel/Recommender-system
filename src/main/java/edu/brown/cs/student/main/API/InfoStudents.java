package edu.brown.cs.student.main.API;

/**
 * Class for storing information on student objects extracted from API aggregation calls
 * Info: id, name, class_year, years_experience, communication_style, weekly_avail_hours, meeting_style, meeting_time
 */

public class InfoStudents {
    private  int id;
    private  String name;
    private  String class_year;
    private  String email;
    private  int years_experience;
    private  String communication_style;
    private  int weekly_avail_hours;
    private  String meeting_style;
    private  String meeting_time;

    /**
     * Constructor for the API Info students class
     * @param id
     * @param name
     * @param email
     * @param classYear
     * @param experienceYrs
     * @param communicationStyle
     * @param weeklyAvailHrs
     * @param meetingStyle
     * @param meetingTime
     */
    public InfoStudents(int id, String name, String email, String classYear, int experienceYrs, String communicationStyle, int weeklyAvailHrs, String meetingStyle, String meetingTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.class_year = classYear;
        this.years_experience = experienceYrs;
        this.communication_style = communicationStyle;
        this.weekly_avail_hours = weeklyAvailHrs;
        this.meeting_style = meetingStyle;
        this.meeting_time = meetingTime;
    }

    /**
     * method that returns student ID
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * method that returns student name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * method that returns student email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * method that returns student class_year
     * @return class_year
     */
    public String getClassYear() {
        return String.valueOf(class_year);
    }

    /**
     * method that returns student years_experience
     * @return years_experience
     */
    public String getYearsExp() {
        return String.valueOf(years_experience);
    }


    /**
     * method that returns student communication_style
     * @return communication_style
     */
    public String getCommunicationStyle() {
        return communication_style;
    }

    /**
     * method that returns student weekly availble hours
     * @return weekly availble hours
     */
    public String getWeeklyHours() {
        return String.valueOf(weekly_avail_hours);
    }

    /**
     * method that returns student wmeeting style
     * @return meeting style
     */
    public String getMeetingStyle() {
        return meeting_style;
    }

    /**
     * method that returns student meeting time
     * @return meeting time
     */
    public String getMeetingTime() {
        return meeting_time;
    }

    /**
     * Coverts any given Info student object into a single string
     * @return
     */
    public String convertToString() {
        return String.join(", ",
                Integer.toString(this.id),
                this.name,
                this.class_year,
                Integer.toString(this.years_experience),
                this.communication_style,
                Integer.toString(this.weekly_avail_hours),
                this.meeting_style, this.meeting_time);
    }
}