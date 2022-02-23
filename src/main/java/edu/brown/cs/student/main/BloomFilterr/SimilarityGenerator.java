package edu.brown.cs.student.main.BloomFilterr;

import java.util.*;

/**
 *
 * A class that takes in students bloom filters and generates recommendations based on XNOR similarity
 *
 */

public class SimilarityGenerator {
    HashMap<String, BloomFilterBuilder> bloomMap;
    int maxNeighbours;


    /**
     * a class constructor that takes in a list of students data and custom comparator
     *
     * @param idsToBlooms
     *            a map os student ids to their bloom filters
     *
     *
     */
    public SimilarityGenerator(HashMap<String, BloomFilterBuilder> idsToBlooms, int k) {
        this.bloomMap = idsToBlooms;
        this.maxNeighbours = k;
    }

    //think about how to add a constructor that allows for other comparators

    /**
     * a method that transforms IdsToBlooms map into a list of pairs of ids and blooms filters
     * the pairs are used by the comparator to organize the priority queue
     *
     * @return List<SBLTuple>
     *     `    a list of pairs of student ids and stucent bloom filters
     */
    public List<SBLTuple> getIdBloomPairs() {
        List<SBLTuple> result = new ArrayList<>();
        for (Map.Entry<String, BloomFilterBuilder> entry : this.bloomMap.entrySet()) {
            int id = Integer.parseInt(entry.getKey());
            BloomFilterBuilder bloom = entry.getValue();
            SBLTuple temp = new SBLTuple(id, bloom);
            result.add(temp);
        }
       return result;
    }

    /**
     * a method that computes the Xnor array of teo given bit arrays
     *
     * @param target
     *          bitarray to compare
     * @param current
     *          bitarray to compare to
     * @return BitArray
     *     `    a Xnor bitArray
     */
    public BitArray computeXnorArray(BitArray target, BitArray current) {
        BitArray m = new BitArray(target.length());
        for(int i=0; i<target.length(); i++) {
            if(target.get(i) && current.get(i)
                    || (!target.get(i) && !current.get(i))) {
                m.set(i, true);
            }
            else {
                m.set(i, false);
            }
        }
        return m;
    }

    /**
     * a method that computes the Xnor similarity score
     * for now, the method counts the number of similar bits only
     *
     * @param XnorArray
     *          similarity bitArray
     *
     * @return int
     *     `    similarity score
     */
    public int similarityScore(BitArray XnorArray) {
        int count = 0;
        for (int i=0; i<XnorArray.length(); i++) {
            if(XnorArray.get(i)) {count++;}
            // consider deducting one from similarity score if the bits are different
        }
        return count;
    }

    /**
     * a method that returns the K most similar students to a given student
     * @param id
     *      id of the student whom we want to find similar students for
     * @return PriorityQueue<SBLTuple>
     *     a priority queue of the most similar students. the queue consists of pairs of the similat students id
     *     and their corresponding XNOR similarity bitarray
     * @throws IllegalArgumentException
     *          for invalid student ids
     */
    public PriorityQueue<SBLTuple> findSimilar(int id) {
        List<SBLTuple> pairs = this.getIdBloomPairs();
        BitArray targetBloomArray = null;
        for (SBLTuple currentPair: pairs
             ) {
            if (currentPair.id==id) {
                targetBloomArray = currentPair.idBloom.getBitArray();
            }
        }
        XNORComparator comparator = new XNORComparator();
        PriorityQueue<SBLTuple> result = new PriorityQueue<>(comparator);
        for (SBLTuple currentPair: pairs
        ) {
            BitArray currentCompareArray = currentPair.idBloom.getBitArray();
            BitArray XnorBitArray = this.computeXnorArray(targetBloomArray, currentCompareArray);
            BloomFilterBuilder mBloom = new BloomFilterBuilder(XnorBitArray);
            SBLTuple neighbourPair = new SBLTuple(currentPair.id, mBloom);
            if (result.size()<this.maxNeighbours) {
                boolean added = result.add(neighbourPair);
                if (!added) {System.out.println("Error: item could not be added");}
            } else {
                SBLTuple currentMin = result.peek();
                if(comparator.compare(currentMin, neighbourPair) < 0) {
                    SBLTuple min = result.poll();
                    boolean added = result.add(neighbourPair);
                    System.out.println("the id removed is: " + currentMin.id);
                    System.out.println("the id added is: " + currentPair.id);
                }
            }
        }
        System.out.println("the size of the queue is: " + result.size());
        return result;
    }

}








