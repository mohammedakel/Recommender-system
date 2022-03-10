package edu.brown.cs.student.main;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAddStudent implements REPL, Command{
  private Database proxy;
  private Map<Integer, DatabaseStudent> students;

  DataAddStudent() {
    String commandName = "add_students";
    this.addCommands(commandName, this);
  }

  public void execute(String[] args) throws IOException, ClassNotFoundException {
    Map<String, String> permissions = new HashMap<>();
    permissions.put("interests", "R");
    permissions.put("names", "R");
    permissions.put("skills", "R");
    permissions.put("traits", "R");
    String filePath = "data/project1/Recommendation Data/sql/data.sqlite3";
    proxy = new Database(filePath, permissions);
    students = new HashMap<>();
    this.constructStudent();

    this.printDatabaseStudents();

  }

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
