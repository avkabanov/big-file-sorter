package com.kabanov.app.reader;

import java.util.List;

/**
 * @author kabaale
 */
public class CollectionDataStorageReaderFactory<D> implements DataStorageReaderFactory<List<D>, D> {

    @Override
    public DataSourceReader<D> createDataStorageReader(List<D> dataSource) {
        return new CollectionDataSourceReader<>(dataSource);
    }
}
