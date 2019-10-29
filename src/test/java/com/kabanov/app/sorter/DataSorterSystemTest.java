package com.kabanov.app.sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.kabanov.app.configuration.CollectionDataSourceModule;
import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.validation.ValidationException;

/**
 * @author Kabanov Alexey
 */
public class DataSorterSystemTest {

    private DataSorter<List<Integer>> sorter;

    @Before
    public void setup() {
        Injector injector = Guice.createInjector(new CollectionDataSourceModule());
        sorter = injector.getInstance(Key.get(new TypeLiteral<DataSorterFactory<List<Integer>>>() {
        })).create();
    }

    @Test
    public void shouldSortManyElementsDataSet() throws StorageNotAccessibleException, ValidationException {
        List<Integer> data = Arrays.asList(6, 1, 4, 7, 2, 0, 3, 6);
        List<Integer> result = new ArrayList<>();
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 6, 6, 7);

        sorter.sort(data, result);
        Assert.assertEquals(expected, result);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenDatasourceIsEmpty() throws StorageNotAccessibleException, ValidationException {
        List<Integer> data = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        
        sorter.sort(data, result);
    }
}