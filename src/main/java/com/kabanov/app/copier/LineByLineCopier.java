package com.kabanov.app.copier;

import com.google.inject.Inject;
import com.kabanov.app.reader.DataSourceReader;
import com.kabanov.app.reader.DataStorageReaderFactory;
import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.writer.DataStorageWriter;
import com.kabanov.app.writer.DataStorageWriterFactory;

/**
 * @author Kabanov Alexey
 */
public class LineByLineCopier<T, D> implements DataCopier<T> {

    private final DataStorageReaderFactory<T, D> readerFactory;
    private final DataStorageWriterFactory<T, D> writerFactory;

    @Inject
    public LineByLineCopier(DataStorageReaderFactory<T, D> readerFactory,
                            DataStorageWriterFactory<T, D> writerFactory) {
        this.readerFactory = readerFactory;
        this.writerFactory = writerFactory;
    }

    @Override
    public void copy(T from, T to) throws StorageNotAccessibleException {
        try (DataSourceReader<D> reader = readerFactory.createDataStorageReader(from);
             DataStorageWriter<D> writer = writerFactory.createDataStorageWriter(to)) {

            while (reader.hasNext()) {
                writer.writeLine(reader.readLine());
            }
        }
    }
}
