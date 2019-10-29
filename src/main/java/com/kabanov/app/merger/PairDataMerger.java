package com.kabanov.app.merger;

import com.kabanov.app.validation.StorageNotAccessibleException;

/**
 * @author kabaale
 */
public interface PairDataMerger<T> {
    
    T merge(T first, T second) throws StorageNotAccessibleException;
}
