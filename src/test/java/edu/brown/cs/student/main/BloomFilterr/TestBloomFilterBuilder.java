package edu.brown.cs.student.main.BloomFilterr;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestBloomFilterBuilder {

    @Test
    public void testBloomFilterBuilderTwoArguments() {
        BloomFilterBuilder<String> sampleBloom = new BloomFilterBuilder<>(5, 0.01);
        assertEquals("000000000000000000000000000000000000000000000000000", sampleBloom.toBinaryString());
    }

    @Test
    public void testBloomFilterBuilderOneArguments() {
        BloomFilterBuilder<String> sampleBloom = new BloomFilterBuilder<>(5);
        assertEquals("000000000000000000000000000000000000000000000000000", sampleBloom.toBinaryString());
    }

    @Test
    public void testBloomFilterBuilderAdd() {
        BloomFilterBuilder<String> sampleBloom = new BloomFilterBuilder<>(5);
        sampleBloom.add("Java");
        assertEquals("010000000001000000000000000110000001000000001000000", sampleBloom.toBinaryString());
    }

    @Test
    public void testBloomFilterBuilderContains() {
        BloomFilterBuilder<String> sampleBloom = new BloomFilterBuilder<>(5);
        sampleBloom.add("Java");
        assertTrue(sampleBloom.mightContain("Java"));
    }

    @Test
    public void testFalsePositive() {
        BloomFilterBuilder<String> sampleBloom = new BloomFilterBuilder<>(1, 0.5);
        assertEquals("00", sampleBloom.toBinaryString());
        sampleBloom.add("cs1710");
        assertTrue(sampleBloom.mightContain("cs1710"));
        assertTrue(sampleBloom.mightContain("cs0320"));
    }

    @Test
    public void testTrueNegative() {
        BloomFilterBuilder<String> sampleBloom = new BloomFilterBuilder<>(1, 0.05);
        assertEquals("00000000", sampleBloom.toBinaryString());
        sampleBloom.add("username");
        assertFalse(sampleBloom.mightContain("hunter2"));
        assertEquals("01010010", sampleBloom.toBinaryString());
        assertFalse(sampleBloom.mightContain("******"));
    }

    @Test
    public void testTruePositive() {
        BloomFilterBuilder<String> sampleBloom = new BloomFilterBuilder<>(1, 0.05);
        assertEquals("00000000", sampleBloom.toBinaryString());
        sampleBloom.add("username");
        assertTrue(sampleBloom.mightContain("username"));
        assertEquals("01010010", sampleBloom.toBinaryString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInvalidRate() {
        BloomFilterBuilder<String> sampleBloom = new BloomFilterBuilder<>(1, 3);
    }








}
