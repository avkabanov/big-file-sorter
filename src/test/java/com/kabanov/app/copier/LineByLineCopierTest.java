package com.kabanov.app.copier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.kabanov.app.reader.CollectionDataSourceReader;
import com.kabanov.app.reader.DataStorageReaderFactory;
import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.writer.CollectionDataSourceWriter;
import com.kabanov.app.writer.DataStorageWriterFactory;

/**
 * @author Kabanov Alexey
 */
public class LineByLineCopierTest {

    private DataStorageReaderFactory<List<Integer>, Integer> readerFactory = CollectionDataSourceReader::new;
    private DataStorageWriterFactory<List<Integer>, Integer> writeFactory = CollectionDataSourceWriter::new; 
    private LineByLineCopier<List<Integer>, Integer> copier = new LineByLineCopier<>(readerFactory, writeFactory);

    @Test
    public void shouldCopyDataWithLotsOfValues() throws StorageNotAccessibleException {
        List<Integer> src = Arrays.asList(1, 2, 3, 4);
        List<Integer> dest = new ArrayList<>();
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        
        copier.copy(src, dest);
        Assert.assertEquals(expected, dest);
    }

    @Test
    public void shouldCopyDataWithSingleLine() throws StorageNotAccessibleException {
        List<Integer> src = Collections.singletonList(1);
        List<Integer> dest = new ArrayList<>();
        List<Integer> expected = Collections.singletonList(1);

        copier.copy(src, dest);
        Assert.assertEquals(expected, dest);
    }

    @Test
    public void shouldCopyEmptyDataSet() throws StorageNotAccessibleException {
        List<Integer> src = new ArrayList<>();
        List<Integer> dest = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        copier.copy(src, dest);
        Assert.assertEquals(expected, dest);
    }
    
}