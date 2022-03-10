package edu.brown.cs.student.main.testAPI;

import edu.brown.cs.student.main.API.FileParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * a class that tests File parser class and its read line method
 *
 */

public class FileParserTest {

    @Test
    public void testFileParserLine(){
        String  filePath = "src/test/java/edu/brown/cs/student/main/testAPI/testParserData";
        FileParser parser = new FileParser(filePath);
        String firstLine = "This is the first line";
        String secondLine = "This is the second line";
        String thirdLine = "This is the third line";
        String firstLineExpected = parser.readNewLine();
        String secondLineExpected = parser.readNewLine();
        String thirdLineExpected = parser.readNewLine();
        assertEquals(firstLine, firstLineExpected);
        assertEquals(secondLine, secondLineExpected);
        assertEquals(thirdLine, thirdLineExpected);
    }
}
