package edu.brown.cs.student.main;

/**
 *
 * A class that constructs a Bit Array data structure
 * Adapted From: https://alvinalexander.com/java/jwarehouse/
 * openjdk-8/jdk/src/share/classes/sun/security/util/BitArray.java.shtml
 *
 */
public class BitArray {
  private byte[] repn;
  private int length;
  private static final int BITS_PER_UNIT = 8;

  private static int subscript(int idx) {
    return idx / BITS_PER_UNIT;
  }

  private static int position(int idx) { // bits big-endian in each unit
    return 1 << (BITS_PER_UNIT - 1 - (idx % BITS_PER_UNIT));
  }

  /**
   * A method that creates a BitArray of the specified size, initialized to zeros.
   *
   * @param length desired array size
   * @throws IllegalArgumentException if input length is negative
   */
  public BitArray(int length) throws IllegalArgumentException {
    if (length < 0) {
      throw new IllegalArgumentException("Negative length for BitArray");
    }
    this.length = length;
    this.repn = new byte[(length + BITS_PER_UNIT - 1) / BITS_PER_UNIT];
  }

  /**
   * A method that returns the indexed bit in this BitArray.
   *
   * @param index
   *          indexed bit which value we would like
   * @throws ArrayIndexOutOfBoundsException
   *           for invalid indices
   * @return boolean
   *          true if array bit is 1 and false otherwise
   */
  public boolean get(int index) throws ArrayIndexOutOfBoundsException {
    if (index < 0 || index >= length) {
      throw new ArrayIndexOutOfBoundsException(Integer.toString(index));
    }

    return (repn[subscript(index)] & position(index)) != 0;
  }

  /**
   *  a method that sets the indexed bit in this BitArray.
   *
   * @param index
   *          array bit to be set
   * @param value
   *          value fot the array bit to be set to
   * @throws ArrayIndexOutOfBoundsException
   *          for invalid indices
   */
  public void set(int index, boolean value)
      throws ArrayIndexOutOfBoundsException {
    if (index < 0 || index >= length) {
      throw new ArrayIndexOutOfBoundsException(Integer.toString(index));
    }
    int idx = subscript(index);
    int bit = position(index);

    if (value) {
      repn[idx] |= bit;
    } else {
      repn[idx] &= ~bit;
    }
  }

  /**
   * A method that returns the length of this BitArray.
   *
   * @return length
   */
  public int length() {
    return length;
  }

  /**
   * A method that returns if an input Bit Array is equal to this BitArray.
   *
   * @param obj
   *      an BitArray object to be compared with this BitArray
   * @return
   *      true if all the bits are the same and false otherwise
   */
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || !(obj instanceof BitArray)) return false;

    BitArray ba = (BitArray) obj;

    if (ba.length != length) return false;

    for (int i = 0; i < repn.length; i += 1) {
      if (repn[i] != ba.repn[i]) return false;
    }
    return true;
  }

  /**
   *  Returns a string representation of this BitArray.
   */
  public String toString() {
    StringBuilder s = new StringBuilder();
    for(int i=0; i<this.length(); i++) {
      if(this.get(i)) {
        s.append("1");
      } else {
        s.append("0");
      }
    }
    return s.toString();
  }

  /**
   * A method that returns the current BitArray.
   *
   * @return byte[]
   */
  public byte[] getByteArray() {
    return this.repn;
  }
}
