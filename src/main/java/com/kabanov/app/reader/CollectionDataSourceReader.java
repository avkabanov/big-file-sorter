package com.kabanov.app.reader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kabaale
 */
public class CollectionDataSourceReader<D> implements DataSourceReader<D> {
    
    private final List<D> list;
    private int currentRead = 0;

    public CollectionDataSourceReader(List<D> list) {
        this.list = list;
    }

    @Override
    public List<D> readLines(int maxLinesToRead) {
        List<D> result = new ArrayList<>();
        int counter = 0;
        while (counter < maxLinesToRead && hasNext()) {
            result.add(readLine());
            counter++;
        }
            
        return result;
    }

    @Override
    public D readLine() {
        if (hasNext()) {
            return list.get(currentRead++);    
        } else {
            return null;
        }
    }

    @Override
    public void close() {
        // do nothing
    }

    @Override
    public boolean hasNext() {
        return currentRead < list.size();
    }

    @Override
    public D next() {
        return readLine();
    }
}
