package com.kabanov.app.writer;

import java.util.List;

import com.kabanov.app.validation.StorageNotAccessibleException;

/**
 * @author kabaale
 */
public class CollectionDataStorageWriterFactory<D> implements DataStorageWriterFactory<List<D>, D> {

    @Override
    public DataStorageWriter<D> createDataStorageWriter(List<D> dataSource) throws StorageNotAccessibleException {
        return new CollectionDataSourceWriter<>(dataSource);
    }
}
