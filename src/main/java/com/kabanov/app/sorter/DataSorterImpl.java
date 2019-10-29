package com.kabanov.app.sorter;

import java.util.List;

import com.google.inject.Inject;
import com.kabanov.app.copier.DataCopier;
import com.kabanov.app.merger.SortedDataMerger;
import com.kabanov.app.splitter.DataSplitter;
import com.kabanov.app.validation.DataValidator;
import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.validation.ValidationException;

/**
 * @author kabaale
 */
public class DataSorterImpl<T> implements DataSorter<T> {
    
    private DataSplitter<T> splitter;
    private SortedDataMerger<T> merger;
    private DataCopier<T> copier;
    private DataValidator<T> validator;

    @Inject
    public DataSorterImpl(DataSplitter<T> splitter, SortedDataMerger<T> merger, DataCopier<T> copier,
                          DataValidator<T> validator) {
        this.splitter = splitter;
        this.merger = merger;
        this.copier = copier;
        this.validator = validator;
    }

    @Override
    public void sort(T src, T dest) throws ValidationException, StorageNotAccessibleException {
        validator.validateInputDataSource(src);
        List<T> sortedChunks = splitter.splitIntoSortedChunks(src);
        T result = merger.mergeAllChunks(sortedChunks);
        copier.copy(result, dest);
    }
}
