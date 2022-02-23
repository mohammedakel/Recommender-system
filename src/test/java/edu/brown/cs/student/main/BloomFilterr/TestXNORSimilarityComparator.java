package edu.brown.cs.student.main.BloomFilterr;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * A class that tests the XNOR comparator and its compare method
 *
 *
 */


public class TestXNORSimilarityComparator {

    @Test
    public void testXNOR() {
        XNORComparator sampleXNOR = new XNORComparator();
        BloomFilterBuilder sampleBloomOne = new BloomFilterBuilder(3);
        sampleBloomOne.add("a");
        SBLTuple samplePairOne = new SBLTuple(1, sampleBloomOne);
        BloomFilterBuilder sampleBloomTwo = new BloomFilterBuilder(3);
        SBLTuple samplePairTwo = new SBLTuple(2, sampleBloomTwo);
        assertEquals(+1, sampleXNOR.compare(samplePairOne, samplePairTwo));
        assertEquals(-1, sampleXNOR.compare(samplePairTwo, samplePairOne));
        BloomFilterBuilder sampleBloomThree = new BloomFilterBuilder(3);
        SBLTuple samplePairThree = new SBLTuple(3, sampleBloomThree);
        assertEquals(0, sampleXNOR.compare(samplePairTwo, samplePairThree));
    }

}
