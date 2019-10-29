package com.kabanov.app.splitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.kabanov.app.comparator.AscendingIntegerComparator;
import com.kabanov.app.comparator.DataComparator;
import com.kabanov.app.creator.TempDataStorageCreator;
import com.kabanov.app.properties.AppProperties;
import com.kabanov.app.reader.CollectionDataSourceReader;
import com.kabanov.app.reader.DataStorageReaderFactory;
import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.writer.CollectionDataSourceWriter;
import com.kabanov.app.writer.DataStorageWriterFactory;

/**
 * @author kabaale
 */
@RunWith(MockitoJUnitRunner.class)
public class DataSplitterImplTest {
    
    private final static int MAX_NUMBER_OF_LINES = 3;

    @Mock AppProperties appProperties;
    private DataSplitter<List<Integer>> splitter;
    private TempDataStorageCreator<List<Integer>> storageCreator = ArrayList::new;
    private DataComparator<Integer> comparator = new AscendingIntegerComparator();
    private DataStorageReaderFactory<List<Integer>, Integer> readerFactory = CollectionDataSourceReader::new;
    private DataStorageWriterFactory<List<Integer>, Integer> writeFactory = CollectionDataSourceWriter::new;
     
    
    @Before
    public void setup() {
        Mockito.when(appProperties.getMaxNumberOfLinesToLoad()).thenReturn(MAX_NUMBER_OF_LINES);
        splitter = new DataSplitterImpl<>(storageCreator, appProperties, comparator, writeFactory, readerFactory);
    }

    @Test
    public void shouldSplitIntoChunksGivenSize() throws StorageNotAccessibleException {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6) ;
        
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1,2, 3));
        expected.add(Arrays.asList(4,5, 6));

        List<List<Integer>> result = splitter.splitIntoSortedChunks(data);

        Assert.assertEquals(expected, result);
    }

}