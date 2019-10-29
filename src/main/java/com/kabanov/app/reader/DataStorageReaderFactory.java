package com.kabanov.app.reader;

import com.kabanov.app.validation.StorageNotAccessibleException;

/**
 * @author kabaale
 */
public interface DataStorageReaderFactory<T, D> {
    
    DataSourceReader<D> createDataStorageReader(T dataSource) throws StorageNotAccessibleException;
}
