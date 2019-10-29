package com.kabanov.app.creator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kabaale
 */
public class CollectionTempDataStorageCreator<D> implements TempDataStorageCreator<List<D>> {
    
    @Override
    public List<D> create() {
        return new ArrayList<>();
    }
}
