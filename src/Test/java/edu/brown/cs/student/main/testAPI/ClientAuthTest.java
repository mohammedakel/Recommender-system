package edu.brown.cs.student.main.testAPI;
import edu.brown.cs.student.main.API.ClientAuth;
import edu.brown.cs.student.main.API.FileParser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * a class that tests clinet auth class and its get API key method
 *
 */

public class ClientAuthTest {

        @Test
        public void testGetAPI() {
            String key = ClientAuth.getApiKey();
            FileParser parser = new FileParser("config/secret/apikey.txt");
            String secretKey = parser.readNewLine();
            assertEquals(secretKey, key);
        }
}
