package com.kabanov.app.sorter;

/**
 * @author kabaale
 */
public interface DataSorterFactory<T> {
    DataSorter<T> create();
}
