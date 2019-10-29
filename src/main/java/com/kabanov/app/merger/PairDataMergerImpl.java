package com.kabanov.app.merger;

import com.google.inject.Inject;
import com.kabanov.app.comparator.DataComparator;
import com.kabanov.app.creator.TempDataStorageCreator;
import com.kabanov.app.reader.DataSourceReader;
import com.kabanov.app.reader.DataStorageReaderFactory;
import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.writer.DataStorageWriter;
import com.kabanov.app.writer.DataStorageWriterFactory;

/**
 * @author Kabanov Alexey
 */
public class PairDataMergerImpl<T, D> implements PairDataMerger<T> {
    
    private TempDataStorageCreator<T> tempCreator;
    private DataComparator<D> comparator;
    private DataStorageReaderFactory<T, D> readerFactory;
    private DataStorageWriterFactory<T, D> writerFactory;

    @Inject
    public PairDataMergerImpl(TempDataStorageCreator<T> tempCreator, DataComparator<D> comparator,
                              DataStorageReaderFactory<T, D> readerFactory,
                              DataStorageWriterFactory<T, D> writerFactory) {
        this.tempCreator = tempCreator;
        this.comparator = comparator;
        this.readerFactory = readerFactory;
        this.writerFactory = writerFactory;
    }

    @Override
    public T merge(T first, T second) throws StorageNotAccessibleException {
        T tempStorage = tempCreator.create();
        DataSourceReader<D> firstReader = readerFactory.createDataStorageReader(first);
        DataSourceReader<D> secondReader = readerFactory.createDataStorageReader(second);

        try (DataStorageWriter<D> storageAppender = writerFactory.createDataStorageWriter(tempStorage)) {
            D firstLine = firstReader.readLine();
            D secondLine = secondReader.readLine();
            while (firstLine != null && secondLine != null) {
                if (comparator.compare(firstLine, secondLine) < 0) {
                    storageAppender.writeLine(firstLine);
                    firstLine = firstReader.readLine();
                } else {
                    storageAppender.writeLine(secondLine);
                    secondLine = secondReader.readLine();
                }
            }

            // append the line that we have already read
            if (firstLine == null) {
                storageAppender.writeLine(secondLine);
            } else {
                storageAppender.writeLine(firstLine);
            }

            DataSourceReader<D> storageToAppend = firstReader.hasNext() ? firstReader : secondReader;
            while (storageToAppend.hasNext()) {
                storageAppender.writeLine(storageToAppend.next());    
            }
        }
        return tempStorage;
    }
}
