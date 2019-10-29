package com.kabanov.app.merger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.inject.Inject;
import com.kabanov.app.validation.StorageNotAccessibleException;

/**
 * @author kabaale
 */
public class SortedDataMerger<T> {

    private PairDataMerger<T> pairDataMerger;

    @Inject
    public SortedDataMerger(PairDataMerger<T> pairDataMerger) {
        this.pairDataMerger = pairDataMerger;
    }

    public T mergeAllChunks(List<T> sortedChunks) throws StorageNotAccessibleException {
        if (sortedChunks.isEmpty()) {
            throw new IllegalArgumentException("chunks can not be empty");
        }
        
        if (sortedChunks.size() == 1) {
            return sortedChunks.get(0);
        }

        List<T> mergedChunks = new ArrayList<>();
        Iterator<T> it = sortedChunks.iterator();
        while (it.hasNext()) {
            T first = it.next();
            T second;

            if (it.hasNext()) {
                second = it.next();
            } else {
                mergedChunks.add(first);
                break;
            }
            mergedChunks.add(pairDataMerger.merge(first, second));
        }

        return mergeAllChunks(mergedChunks);
    }
}
