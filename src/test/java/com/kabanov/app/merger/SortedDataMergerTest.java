package com.kabanov.app.merger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kabanov.app.comparator.AscendingIntegerComparator;
import com.kabanov.app.validation.StorageNotAccessibleException;

/**
 * @author kabaale
 */
@RunWith(MockitoJUnitRunner.class)
public class SortedDataMergerTest {

    @Mock private PairDataMerger<List<Integer>> pairDataMerger;
    private SortedDataMerger<List<Integer>> merger;
    private AscendingIntegerComparator comparator = new AscendingIntegerComparator();

    @Before
    public void setup() throws StorageNotAccessibleException {
        // emulate algorithm of merging to arrays and sorting it
        Mockito.when(pairDataMerger.merge(Mockito.anyList(), Mockito.anyList())).then(
                (Answer<List<Integer>>) invocation -> {
                    List<Integer> first = invocation.getArgument(0);
                    List<Integer> second = invocation.getArgument(1);

                    List<Integer> result = new ArrayList<>(first);
                    result.addAll(second);
                    result.sort(comparator);
                    return result;
                });

        merger = new SortedDataMerger<>(pairDataMerger);
    }

    @Test
    public void shouldNotFallWithSingleElementChunk() throws StorageNotAccessibleException {
        List<List<Integer>> chunks = new ArrayList<>();
        chunks.add(Collections.singletonList(1));
        List<Integer> expected = Collections.singletonList(1);

        List<Integer> result = merger.mergeAllChunks(chunks);
        Assert.assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenEmptyChunkListPassed() throws StorageNotAccessibleException {
        List<List<Integer>> chunks = new ArrayList<>();

        merger.mergeAllChunks(chunks);
    }

    @Test
    public void shouldMergeEvenNumberOfChunks() throws StorageNotAccessibleException {
        List<List<Integer>> chunks = new ArrayList<>();
        
        // add even number of chunks. Do not care about the content of the chunk
        chunks.add(Collections.singletonList(1));
        chunks.add(Collections.singletonList(2));
        chunks.add(Collections.singletonList(3));
        chunks.add(Collections.singletonList(4));
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);

        List<Integer> result = merger.mergeAllChunks(chunks);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldMergeOddNumberOfChunks() throws StorageNotAccessibleException {
        List<List<Integer>> chunks = new ArrayList<>();

        // add odd number of chunks. Do not care about the content of the chunk
        chunks.add(Collections.singletonList(1));
        chunks.add(Collections.singletonList(2));
        chunks.add(Collections.singletonList(3));
        List<Integer> expected = Arrays.asList(1, 2, 3);

        List<Integer> result = merger.mergeAllChunks(chunks);
        Assert.assertEquals(expected, result);
    }
    
    
}