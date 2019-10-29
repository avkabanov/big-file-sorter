package com.kabanov.app.writer;

import com.kabanov.app.validation.StorageNotAccessibleException;

/**
 * @author kabaale
 */
public interface DataStorageWriterFactory<T, D> {
    
    DataStorageWriter<D> createDataStorageWriter(T dataSource) throws StorageNotAccessibleException;
}
