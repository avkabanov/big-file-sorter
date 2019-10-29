package com.kabanov.app.copier;

import com.kabanov.app.validation.StorageNotAccessibleException;

/**
 * @author kabaale
 */
public interface DataCopier<T> {
    
    void copy(T from, T to) throws StorageNotAccessibleException;
}
