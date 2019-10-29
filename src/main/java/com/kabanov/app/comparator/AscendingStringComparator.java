package com.kabanov.app.comparator;

/**
 * @author kabaale
 */
public class AscendingStringComparator implements DataComparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
