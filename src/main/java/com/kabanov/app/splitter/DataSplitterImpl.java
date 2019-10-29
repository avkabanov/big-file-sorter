package com.kabanov.app.splitter;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.kabanov.app.comparator.DataComparator;
import com.kabanov.app.creator.TempDataStorageCreator;
import com.kabanov.app.properties.AppProperties;
import com.kabanov.app.reader.DataSourceReader;
import com.kabanov.app.reader.DataStorageReaderFactory;
import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.writer.DataStorageWriter;
import com.kabanov.app.writer.DataStorageWriterFactory;

/**
 * @author kabaale
 */
public class DataSplitterImpl<T, D> implements DataSplitter<T> {

    private TempDataStorageCreator<T> tempDataStorageCreator;
    private final AppProperties appProperties;
    private DataComparator<D> stringComparator;
    private DataStorageWriterFactory<T, D> dataStorageWriterFactory;
    private DataStorageReaderFactory<T, D> dataStorageReaderFactory;

    @Inject
    public DataSplitterImpl(TempDataStorageCreator<T> tempDataStorageCreator,
                            AppProperties appProperties,
                            DataComparator<D> stringComparator,
                            DataStorageWriterFactory<T, D> dataStorageWriterFactory,
                            DataStorageReaderFactory<T, D> dataStorageReaderFactory) {
        this.tempDataStorageCreator = tempDataStorageCreator;
        this.appProperties = appProperties;
        this.stringComparator = stringComparator;
        this.dataStorageWriterFactory = dataStorageWriterFactory;
        this.dataStorageReaderFactory = dataStorageReaderFactory;
    }

    @Override
    public List<T> splitIntoSortedChunks(T dataSource) throws StorageNotAccessibleException {
        List<T> chunks = new ArrayList<>();                        
        try (DataSourceReader<D> dataReader = dataStorageReaderFactory.createDataStorageReader(dataSource)) {
            while (dataReader.hasNext()) {
                List<D> lines = dataReader.readLines(appProperties.getMaxNumberOfLinesToLoad());
                lines.sort(stringComparator);

                T tmpStorage = tempDataStorageCreator.create();
                try (DataStorageWriter<D> writer = dataStorageWriterFactory.createDataStorageWriter(tmpStorage)) {
                    writer.writeLines(lines);
                }
                chunks.add(tmpStorage);
            }
            return chunks;
        }
    }
}
