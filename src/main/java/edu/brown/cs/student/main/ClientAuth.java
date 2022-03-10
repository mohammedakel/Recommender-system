package edu.brown.cs.student.main;

/**
 * class responsible for reading the secret API
 */
public class ClientAuth {
  /**
   * Reads the API Key from the secret text file where it has been stored.
   *
   * @return a String of the api key.
   */
  public static String getApiKey() {
    FileParser parser = new FileParser("config/secret/apikey.txt");
    return parser.readNewLine();
  }
}
