package com.kabanov.app.sorter;

import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.validation.ValidationException;

/**
 * @author kabaale
 */
public interface DataSorter<T> {
    
    void sort(T src, T dest) throws ValidationException, StorageNotAccessibleException;
}
