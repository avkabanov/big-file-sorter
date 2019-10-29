package com.kabanov.app.writer;

import java.util.List;

/**
 * @author kabaale
 */
public class CollectionDataSourceWriter<D> implements DataStorageWriter<D> {
    
    private final List<D> list;

    public CollectionDataSourceWriter(List<D> list) {
        this.list = list;
    }

    @Override
    public void writeLine(D line) {
        list.add(line);
    }

    @Override
    public void writeLines(List<D> lines) {
        list.addAll(lines);
    }

    @Override
    public void close() {
        // do nothing
    }
}
