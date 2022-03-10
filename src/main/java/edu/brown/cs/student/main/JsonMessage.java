package edu.brown.cs.student.main;
import java.util.List;

/**
 * class for interacting with JSON parser
 *
 */
public class JsonMessage {
  private String message;

  /**
   * Simple constructor.
   * @param
   *      message the message extracted from the JSON object.
   */
  public JsonMessage(String message) {
    this.message = message;
  }

  /**
   * Returns the extracted message.
   *
   */
  public String getMessage() {
    return this.message;
  }
}
