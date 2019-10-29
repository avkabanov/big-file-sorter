package com.kabanov.app.writer;

import java.io.File;
import java.io.IOException;

import com.kabanov.app.validation.StorageNotAccessibleException;

/**
 * @author kabaale
 */
public class FileDataStoreWriterFactory implements DataStorageWriterFactory<File, String> {
    
    @Override
    public DataStorageWriter<String> createDataStorageWriter(File dataSource) throws StorageNotAccessibleException {
        try {
            return new FileDataStoreWriter(dataSource);
        } catch (IOException e) {
            throw new StorageNotAccessibleException(e);
        }
    }
}
