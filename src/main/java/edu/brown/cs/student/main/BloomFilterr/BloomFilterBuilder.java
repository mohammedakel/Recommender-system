package edu.brown.cs.student.main.BloomFilterr;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import static java.lang.Math.log;
import static java.lang.Math.max;

/**
 * A class that implements a bit-set based bloom filter data structure and its relevant methods
 */

public class BloomFilterBuilder  {

    private BitArray sampleBitArray;
    private int maxNumHash;
    private int maxBloomSize;
    private BigInteger[] hashFunctions;



    /**
     * First constructor for the Bloom Filter. calculates the ideal size for the bloom filter.
     * uses user input false positivity rate as well as total elements expected
     *
     * @param n
     *            maximum number of elements that will be inserted into the bloomfilter
     * @param r
     *            desired false positivity rate
     * @throws IllegalArgumentException
     *          for invalid rate inputs
     */
    public BloomFilterBuilder(int n, double r) {
        if(0<r && r<1) {
            double log2r = (log(r) / log(2));
            int k = (int) Math.ceil(-1 * log2r);
            int m = (int) Math.ceil((k * n) / log(2));
            this.sampleBitArray = new BitArray(m);
            this.maxBloomSize = m;
            this.maxNumHash = k;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Second constructor for the Bloom Filter. uses deadfult value of 0.1 for desired positivity rate and
     * user input expected number of elements
     *
     * @param n
     *            maximum number of elements that will be inserted into the bloomfilter
     *
     */
    public BloomFilterBuilder(int n) {
        double log2r = (log(0.01) / log(2));
        int k = (int) Math.ceil(-1 * log2r);
        int m = (int) Math.ceil((k * n) / log(2));
        this.sampleBitArray = new BitArray(m);
        this.maxBloomSize = m;
        this.maxNumHash = k;
    }


    /**
     * a method that adds an item to the given bloom filter
     *
     * @param item
     *            categorical item to be added to the bloom filter
     */
    public void add(String item) {
        HashGenerator hashFuncGenerator = new HashGenerator();
        String str = item.toString();
        byte[] b = str.getBytes(StandardCharsets.UTF_8);
        BigInteger[] hashFunctions = hashFuncGenerator.createHashes(b, this.maxNumHash);
        for (BigInteger hashfun: hashFunctions) {
            System.out.println(hashfun);
            BigInteger b1 = new BigInteger(String.valueOf(this.maxBloomSize));
            System.out.println("check");
            System.out.println(b1);
            BigInteger index = hashfun.mod(b1);
            System.out.println(index);
             this.sampleBitArray.set(index.intValue(), true);
        }
    }

    /**
     * a method that checks if a given item may be contained in the bloom filter
     *
     * @param item
     *            item to check if it is contained in the bloom filter
     * @return boolean
     *              true if the item is in the bloom filter and false otherwise
     *
     */
    public boolean mightContain(String item) {
        HashGenerator hashFuncGenerator = new HashGenerator();
        String str = item.toString();
        byte[] b = str.getBytes(StandardCharsets.UTF_8);
        BigInteger[] hashFunctions = hashFuncGenerator.createHashes(b, this.maxNumHash);
        for (BigInteger hashfun: hashFunctions) {
            BigInteger index = hashfun.mod(BigInteger.valueOf(this.maxBloomSize));
            if(!this.sampleBitArray.get(index.intValue())) {return false;}
        }
        return true;
    }


    /**
     * A method that returns the bit Array.
     *
     * @return current BitArray
     */
    public BitArray getBitArray() {
        return this.sampleBitArray;
    }
    public int getLen() {return this.maxBloomSize;}

    /**
     * a method that prints this bitSet in the form a binary string
     * from: https://stackoverflow.com/questions/38490760/how-to-print-bitset-as-series-of-bits
     *
     *
     * @return "01010000" binary string
     */
    public  String toBinaryString() {
        return(this.sampleBitArray.toString());
    }


}
