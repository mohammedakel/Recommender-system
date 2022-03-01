package edu.brown.cs.student.main;

import edu.brown.cs.student.main.BloomFilterr.BitArray;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * A class that tests the BitArray data structure and its relevant methods
 *
 *
 */
public class BitArrayTest {
  /**
   // a method to test invalid array constructor inputs
   @Test (expected = IllegalArgumentException.class)
   public void testBitArrayConstructor() {
   BitArray invalidNegative = new BitArray(-4);
   }
   */

  // a method that tests accessing valid indices
  // also tests that array is originally set to zero (false)
  @Test
  public void testGetValidArrayIndex() {
    BitArray sampleArray = new BitArray(3);
    assertFalse(sampleArray.get(0));
    assertFalse(sampleArray.get(1));
    assertFalse(sampleArray.get(2));
    assertEquals(3, sampleArray.length());
    assertEquals("000", sampleArray.toString());
  }

  // a method to test invalid get array indices
  @Test (expected = ArrayIndexOutOfBoundsException.class)
  public void testBitArrayGetInvalidIndex() {
    BitArray sampleArray = new BitArray(4);
    sampleArray.get(4);
  }

  // a method that tests upadating inidivual bit balues
  @Test
  public void testSetValidArrayIndex () {
    BitArray sampleArray = new BitArray(3);
    sampleArray.set(1, true);
    assertTrue(sampleArray.get(1));
    assertFalse(sampleArray.get(2));
  }

  // a method to test invalid array set indices
  @Test (expected = ArrayIndexOutOfBoundsException.class)
  public void testBitArraySetInvalidIndex() {
    BitArray sampleArray = new BitArray(4);
    sampleArray.set(4, true);
  }
}
