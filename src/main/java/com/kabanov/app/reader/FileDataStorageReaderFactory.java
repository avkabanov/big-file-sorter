package com.kabanov.app.reader;

import java.io.File;
import java.io.FileNotFoundException;

import com.kabanov.app.validation.StorageNotAccessibleException;

/**
 * @author kabaale
 */
public class FileDataStorageReaderFactory implements DataStorageReaderFactory<File, String> {
    
    @Override
    public DataSourceReader<String> createDataStorageReader(File dataSource) throws StorageNotAccessibleException {
        try {
            return new FileDataSourceReader(dataSource);
        } catch (FileNotFoundException e) {
            throw new StorageNotAccessibleException(e);
        }
    }
}
