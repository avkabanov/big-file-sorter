package com.kabanov.app.writer;

import java.io.Closeable;
import java.util.List;

/**
 * @author kabaale
 */
public interface DataStorageWriter<D> extends Closeable {

    void writeLine(D line);

    void writeLines(List<D> lines);

    @Override
    void close();
}
