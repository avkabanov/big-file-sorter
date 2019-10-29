package com.kabanov.app.reader;

import java.io.Closeable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

/**
 * @author kabaale
 */
public interface DataSourceReader<D> extends Closeable, Iterator<D> {

    List<D> readLines(int maxLinesToRead);

    /**
     * @return null if nothing left to read
     */
    @Nullable
    D readLine();

    @Override
    void close();
}
