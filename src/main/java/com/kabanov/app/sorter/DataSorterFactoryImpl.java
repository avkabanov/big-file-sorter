package com.kabanov.app.sorter;

import com.google.inject.Inject;
import com.kabanov.app.copier.DataCopier;
import com.kabanov.app.merger.SortedDataMerger;
import com.kabanov.app.splitter.DataSplitter;
import com.kabanov.app.validation.DataValidator;

/**
 * @author kabaale
 */
public class DataSorterFactoryImpl<T> implements DataSorterFactory<T> {
    
    private DataSplitter<T> dataSplitter;
    private SortedDataMerger<T> dataMerger;
    private DataCopier<T> dataCopier;
    private DataValidator<T> dataValidator;

    @Inject
    public DataSorterFactoryImpl(DataSplitter<T> dataSplitter,
                                 SortedDataMerger<T> dataMerger, DataCopier<T> dataCopier,
                                 DataValidator<T> dataValidator) {
        this.dataSplitter = dataSplitter;
        this.dataMerger = dataMerger;
        this.dataCopier = dataCopier;
        this.dataValidator = dataValidator;
    }

    @Override
    public com.kabanov.app.sorter.DataSorterImpl<T> create() {
        return new DataSorterImpl<T>(dataSplitter, dataMerger, dataCopier, dataValidator);
    }
}
