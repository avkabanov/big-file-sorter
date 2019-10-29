package com.kabanov.app.configuration;

import java.io.File;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.kabanov.app.comparator.AscendingStringComparator;
import com.kabanov.app.comparator.DataComparator;
import com.kabanov.app.copier.DataCopier;
import com.kabanov.app.copier.LineByLineCopier;
import com.kabanov.app.creator.FileDataStorageCreator;
import com.kabanov.app.creator.TempDataStorageCreator;
import com.kabanov.app.merger.PairDataMerger;
import com.kabanov.app.merger.PairDataMergerImpl;
import com.kabanov.app.merger.SortedDataMerger;
import com.kabanov.app.reader.DataStorageReaderFactory;
import com.kabanov.app.reader.FileDataStorageReaderFactory;
import com.kabanov.app.sorter.DataSorterFactory;
import com.kabanov.app.sorter.DataSorterFactoryImpl;
import com.kabanov.app.splitter.DataSplitter;
import com.kabanov.app.splitter.DataSplitterImpl;
import com.kabanov.app.validation.DataValidator;
import com.kabanov.app.validation.DataValidatorImpl;
import com.kabanov.app.writer.DataStorageWriterFactory;
import com.kabanov.app.writer.FileDataStoreWriterFactory;

/**
 * @author kabaale
 */
public class FileDataSourceModule extends AbstractModule {
    @Override
    protected void configure() {
        
        bind(new TypeLiteral<DataSorterFactory<File>>() {
        }).to(new TypeLiteral<DataSorterFactoryImpl<File>>() {
        });

        bind(new TypeLiteral<DataSplitter<File>>() {
        }).to((new TypeLiteral<DataSplitterImpl<File, String>>() {
        }));

        bind(new TypeLiteral<TempDataStorageCreator<File>>() {
        }).to(new TypeLiteral<FileDataStorageCreator>() {
        });

        bind(new TypeLiteral<DataStorageReaderFactory<File, String>>() {
        }).to(new TypeLiteral<FileDataStorageReaderFactory>() {
        });

        bind(new TypeLiteral<DataStorageReaderFactory<File, ?>>() {
        }).to(new TypeLiteral<FileDataStorageReaderFactory>() {
        });

        bind(new TypeLiteral<DataStorageWriterFactory<File, String>>() {
        }).to(new TypeLiteral<FileDataStoreWriterFactory>() {
        });

        bind(new TypeLiteral<DataCopier<File>>() {
        }).to(new TypeLiteral<LineByLineCopier<File, String>>() {
        });

        bind(new TypeLiteral<PairDataMerger<File>>() {
        }).to(new TypeLiteral<PairDataMergerImpl<File, String>>() {
        });

        bind(new TypeLiteral<SortedDataMerger<File>>() {
        });

        bind(new TypeLiteral<DataComparator<String>>() {
        }).to(AscendingStringComparator.class);

        bind(new TypeLiteral<DataValidator<File>>() {
        }).to(new TypeLiteral<DataValidatorImpl<File>>() {
        });
    }
}
