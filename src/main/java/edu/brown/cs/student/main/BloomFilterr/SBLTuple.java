package edu.brown.cs.student.main.BloomFilterr;

/**
 *
 * a class that represents a basic data structure to store a pair of student ids and their correposnding Bloom Filter
 *
 */

public class SBLTuple {
    public int id;
    public BloomFilterBuilder idBloom;

    // a constructor that takes in an id and a bloom filter
    public SBLTuple(int id, BloomFilterBuilder idBloom) {
        this.id = id;
        this.idBloom = idBloom;
    }
}
