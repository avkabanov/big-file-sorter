package com.kabanov.app.validation;


import com.google.inject.Inject;
import com.kabanov.app.reader.DataSourceReader;
import com.kabanov.app.reader.DataStorageReaderFactory;

/**
 * @author Kabanov Alexey
 */
public class DataValidatorImpl<T> implements DataValidator<T> {
    
    private DataStorageReaderFactory<T, ?> readerFactory;

    @Inject
    public DataValidatorImpl(DataStorageReaderFactory<T, ?> readerFactory) {
        this.readerFactory = readerFactory;
    }
    
    @Override
    public void validateInputDataSource(T src) throws ValidationException {
        try (DataSourceReader reader = readerFactory.createDataStorageReader(src)) {
            if (!reader.hasNext()) {
                throw new ValidationException("Datasource can not be empty");
            }
            
        } catch (StorageNotAccessibleException e) {
            throw new ValidationException("Can not read from datasource", e);
        } 
    }
}
