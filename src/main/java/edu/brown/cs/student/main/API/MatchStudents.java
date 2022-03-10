package edu.brown.cs.student.main.API;

/**
 *
 * Class for storing information on student objects extracted from API aggregation calls
 * info = id, name, gender, ssn, nationality, race, software_eng_confidence
 */

public class MatchStudents {
    private  int id;
    private  String name;
    private  String gender;
    private  String ssn;
    private  String nationality;
    private  String race;
    private  int software_eng_confidence;


    /**
     * Constructor for the API Match students class
     * @param id
     * @param name
     * @param gender
     * @param ssn
     * @param nationality
     * @param race
     * @param software_confidence
     */
    public MatchStudents(int id, String name, String gender, String ssn, String nationality, String race, int software_confidence) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.ssn = ssn;
        this.nationality = nationality;
        this.race = race;
        this.software_eng_confidence = software_confidence;
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
     * method that returns student GENDER
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * method that returns student SSN
     * @return SSN
     */
    public String getSSN() {
        return ssn;
    }

    /**
     * method that returns student nationality
     * @return nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * method that returns student race
     * @return race
     */
    public String getRace() {
        return race;
    }

    /**
     * method that returns student software confidence
     * @return software confidence
     */
    public String getSoftwareConfidence() {
        return String.valueOf(software_eng_confidence);
    }

    /**
     * Coverts any given APIMatchStudents object into a single string
     * @return
     */
    public String convertToString() {
        return String.join(", ",
                Integer.toString(this.id),
                this.name,
                this.gender,
                this.ssn,
                this.nationality,
                this.race,
                Integer.toString(this.software_eng_confidence));
    }

}
