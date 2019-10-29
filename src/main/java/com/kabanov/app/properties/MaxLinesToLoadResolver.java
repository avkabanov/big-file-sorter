package com.kabanov.app.properties;

import com.kabanov.app.validation.ValidationException;

/**
 * @author kabaale
 */
public interface MaxLinesToLoadResolver {
    int getMaxNumberOfLinesToLoad() throws ValidationException;
}
