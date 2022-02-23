package edu.brown.cs.student.main.BloomFilterr;
import java.util.Comparator;

public class XNORComparator implements Comparator<SBLTuple>{

    public XNORComparator(){};

    @Override
    public int compare(SBLTuple obj1, SBLTuple obj2) {
        int obj1Count = 0;
        int obj2Count = 0;

        BitArray obj1Bit = obj1.idBloom.getBitArray();
        BitArray obj2Bit = obj2.idBloom.getBitArray();

        for(int i=0; i<obj1Bit.length(); i++) {
            if (obj1Bit.get(i)) {
                obj1Count++;
            }
        }
        for(int j=0; j<obj2Bit.length(); j++) {
            if (obj2Bit.get(j)) {
                obj2Count++;
            }
        }
        if(obj1Count>obj2Count) {return 1;}
        if(obj1Count<obj2Count) {return -1;}
        return 0;
    }
}
