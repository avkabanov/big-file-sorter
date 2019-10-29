package com.kabanov.app.validation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.kabanov.app.reader.CollectionDataSourceReader;
import com.kabanov.app.reader.DataStorageReaderFactory;

/**
 * @author Kabanov Alexey
 */
public class DataValidatorImplTest {

    private DataStorageReaderFactory<List<Integer>, Integer> readerFactory = CollectionDataSourceReader::new;
    private DataValidator<List<Integer>> validator = new DataValidatorImpl<>(readerFactory);

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionOnEmptyDatasource() throws ValidationException {
        validator.validateInputDataSource(new ArrayList<>());
    }
}