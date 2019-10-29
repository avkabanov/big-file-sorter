package com.kabanov.app.comparator;

import java.io.File;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.kabanov.app.sorter.DataSorter;
import com.kabanov.app.sorter.DataSorterImpl;

/**
 * @author Kabanov Alexey
 */
public class FileDataSourceLauncherModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<DataSorter<File>>() {
        }).to(new TypeLiteral<DataSorterImpl<File>>() {
        });
    }
}
