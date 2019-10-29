package com.kabanov.app.configuration;

import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.kabanov.app.comparator.AscendingIntegerComparator;
import com.kabanov.app.comparator.DataComparator;
import com.kabanov.app.copier.DataCopier;
import com.kabanov.app.copier.LineByLineCopier;
import com.kabanov.app.creator.CollectionTempDataStorageCreator;
import com.kabanov.app.creator.TempDataStorageCreator;
import com.kabanov.app.merger.PairDataMerger;
import com.kabanov.app.merger.PairDataMergerImpl;
import com.kabanov.app.merger.SortedDataMerger;
import com.kabanov.app.properties.AppProperties;
import com.kabanov.app.reader.CollectionDataStorageReaderFactory;
import com.kabanov.app.reader.DataStorageReaderFactory;
import com.kabanov.app.sorter.DataSorterFactory;
import com.kabanov.app.sorter.DataSorterFactoryImpl;
import com.kabanov.app.splitter.DataSplitter;
import com.kabanov.app.splitter.DataSplitterImpl;
import com.kabanov.app.validation.DataValidator;
import com.kabanov.app.validation.DataValidatorImpl;
import com.kabanov.app.writer.CollectionDataStorageWriterFactory;
import com.kabanov.app.writer.DataStorageWriterFactory;

/**
 * @author kabaale
 */
public class CollectionDataSourceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<DataSorterFactory<List<Integer>>>() {
        }).to(new TypeLiteral<DataSorterFactoryImpl<List<Integer>>>() {
        });

        bind(new TypeLiteral<DataSplitter<List<Integer>>>() {
        }).to((new TypeLiteral<DataSplitterImpl<List<Integer>, Integer>>() {
        }));

        bind(new TypeLiteral<TempDataStorageCreator<List<Integer>>>() {
        }).to(new TypeLiteral<CollectionTempDataStorageCreator<Integer>>() {});

        bind(new TypeLiteral<DataStorageReaderFactory<List<Integer>, Integer>>() {
        }).to(new TypeLiteral<CollectionDataStorageReaderFactory<Integer>>() {
        });

        bind(new TypeLiteral<DataStorageReaderFactory<List<Integer>, ?>>() {
        }).to(new TypeLiteral<CollectionDataStorageReaderFactory<Integer>>() {
        });

        bind(new TypeLiteral<DataStorageWriterFactory<List<Integer>, Integer>>() {
        }).to(new TypeLiteral<CollectionDataStorageWriterFactory<Integer>>() {
        });

        bind(new TypeLiteral<DataCopier<List<Integer>>>() {
        }).to(new TypeLiteral<LineByLineCopier<List<Integer>, Integer>>() {
        });

        bind(new TypeLiteral<PairDataMerger<List<Integer>>>() {
        }).to(new TypeLiteral<PairDataMergerImpl<List<Integer>, Integer>>() {
        });

        bind(new TypeLiteral<SortedDataMerger<List<Integer>>>() {
        });

        bind(new TypeLiteral<DataComparator<Integer>>() {
        }).to(AscendingIntegerComparator.class);

        bind(new TypeLiteral<DataValidator<List<Integer>>>() {
        }).to(new TypeLiteral<DataValidatorImpl<List<Integer>>>() {
        });
    }

    @Provides
    public AppProperties providesAppProperties() {
        return new AppProperties(2);
    }
}
