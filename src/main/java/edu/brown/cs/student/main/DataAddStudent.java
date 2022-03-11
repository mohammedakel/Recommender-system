package edu.brown.cs.student.main;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Run add_names
 *
 * Loads in the database student data into a hashmap and iterates through them to add all elements
 * to the student object and prints them out to make sure it worked properly
 *
 * Implements REPL and Command interface
 *
 * @author hjeon15
 */
public class DataAddStudent implements REPL, Command{
  private Database proxy;
  private Map<Integer, DatabaseStudent> students;
  /**
   * Constructor must add itself to REPL hashmap containing command name and classes
   */
  DataAddStudent() {
    String commandName = "add_students";
    this.addCommands(commandName, this);
  }

  /**
   * Gives permission to each table elements and runs sql statement using database proxy
   *
   * @param args (array of strings)
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void execute(String[] args) throws IOException, ClassNotFoundException {
    Map<String, String> permissions = new HashMap<>();
    permissions.put("interests", "R");
    permissions.put("names", "R");
    permissions.put("skills", "R");
    permissions.put("traits", "R");
    String filePath = "data/sprint2/sql/data.sqlite3";
    proxy = new Database(filePath, permissions);
    students = new HashMap<>();
    this.constructStudent();
    REPL.addCommandObject("add_students", students);
  }

  /**
   * Helper function to test add_student command is working. Prints out all the componenets of
   * student object
   */
  public void printDatabaseStudents() {
    for (Integer id : students.keySet()) {
      DatabaseStudent student = students.get(id);
      System.out.println(id + ", name: " + student.getName() + ", "
          + student.getEmail());
      System.out.println("Skilled at " + student.getSkill() + ", interested in "
          + student.getInterests());
      System.out.println(student.getStrengths());
      System.out.println(student.getWeaknesses());
      System.out.println(" ");
    }
  }

  /**
   * Constructs student objects by reading into data using SQL statements
   */
  private void constructStudent() {
    try {
      ResultSet rs = this.proxy.executeSQLWithCache("SELECT * FROM names JOIN skills "
          + "WHERE names.id = skills.id ORDER BY names.id");

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String skill = rs.getString("skill");

        DatabaseStudent student = new DatabaseStudent(id, name, email, skill);
        this.students.put(id,student);
      }

      ResultSet interests = this.proxy.executeSQLWithCache("SELECT * FROM interests ORDER BY " +
          "interests.id");
      while (interests.next()) {
        int id = interests.getInt("id");
        String interest = interests.getString("interest");
        this.students.get(id).addInterest(interest);
      }

      ResultSet traits = this.proxy.executeSQLWithCache("SELECT * FROM traits ORDER BY traits.id");
      while (traits.next()) {
        int id = traits.getInt("id");
        String typeOfAttribute = traits.getString("type_of_attribute");
        String trait = traits.getString("trait");
        this.students.get(id).addTrait(typeOfAttribute, trait);
      }
    } catch (SQLException e) {
      System.out.println("ERROR: SQLException caught");
    }
  }
}
