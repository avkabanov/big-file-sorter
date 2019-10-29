package com.kabanov.app.merger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.kabanov.app.comparator.AscendingIntegerComparator;
import com.kabanov.app.creator.TempDataStorageCreator;
import com.kabanov.app.reader.CollectionDataSourceReader;
import com.kabanov.app.reader.DataStorageReaderFactory;
import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.writer.CollectionDataSourceWriter;
import com.kabanov.app.writer.DataStorageWriterFactory;

/**
 * @author kabaale
 */
public class PairDataMergerImplTest {

    private TempDataStorageCreator<List<Integer>> creator = ArrayList::new;
    private DataStorageReaderFactory<List<Integer>, Integer> readerFactory = CollectionDataSourceReader::new;
    private DataStorageWriterFactory<List<Integer>, Integer> writeFactory = CollectionDataSourceWriter::new;

    private PairDataMergerImpl<List<Integer>, Integer> merger = new PairDataMergerImpl<>(creator,
            new AscendingIntegerComparator(), readerFactory, writeFactory);

    @Test
    public void shouldSameSizeListBeMerged() throws StorageNotAccessibleException {
        List<Integer> first = Arrays.asList(1, 3, 5);
        List<Integer> second = Arrays.asList(2, 4, 6);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> result = merger.merge(first, second);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldMergeIfFirstListGreaterThanSecond() throws StorageNotAccessibleException {
        List<Integer> first = Arrays.asList(1, 3, 5, 6);
        List<Integer> second = Arrays.asList(2, 3);
        List<Integer> expected = Arrays.asList(1, 2, 3, 3, 5, 6);

        List<Integer> result = merger.merge(first, second);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldMergeIfSecondListGreaterThanFirst() throws StorageNotAccessibleException {
        List<Integer> first = Arrays.asList(2, 3);
        List<Integer> second = Arrays.asList(1, 3, 5, 6);
        List<Integer> expected = Arrays.asList(1, 2, 3, 3, 5, 6);

        List<Integer> result = merger.merge(first, second);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldMergeIfAllValuesTheSame() throws StorageNotAccessibleException {
        List<Integer> first = Arrays.asList(1, 1);
        List<Integer> second = Arrays.asList(1, 1, 1, 1);
        List<Integer> expected = Arrays.asList(1, 1, 1, 1, 1, 1);

        List<Integer> result = merger.merge(first, second);
        Assert.assertEquals(expected, result);
    }

}