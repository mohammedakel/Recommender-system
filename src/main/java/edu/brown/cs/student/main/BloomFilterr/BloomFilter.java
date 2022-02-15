package edu.brown.cs.student.main.BloomFilterr;

/**
 * An interface that represents a Bloom Filter of type T
 */

public interface BloomFilter<T> {

    /**
     * a method that adds an item to the given bloom filter
     *
     * @param item
     *            item to be added to the bloom filter
     */
    void add(T item);

    /**
     * a method that checks if a given item may be contained in the bloom filter
     *
     * @param item
     *            item to check if it is contained in the bloom filter
     * @return boolean
     *              true if the item is in the bloom filter and false otherwise
     *
     */
    boolean mightContain(T item);
}
