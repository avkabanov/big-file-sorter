package com.kabanov.app.splitter;

import java.util.List;

import com.kabanov.app.validation.StorageNotAccessibleException;

/**
 * @author kabaale
 */
public interface DataSplitter<T> {
    
    List<T> splitIntoSortedChunks(T dataSource) throws StorageNotAccessibleException;
}
