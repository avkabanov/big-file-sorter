package com.kabanov.app.comparator;

/**
 * @author kabaale
 */
public class AscendingIntegerComparator implements DataComparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
