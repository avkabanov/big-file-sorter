package com.kabanov.app.properties;

import com.google.inject.Inject;
import com.kabanov.app.validation.ValidationException;

/**
 * @author Kabanov Alexey
 */
public class AppPropertiesFactory {

    private MaxLinesToLoadResolver maxLinesToLoadResolver;

    @Inject
    public AppPropertiesFactory(MaxLinesToLoadResolver maxLinesToLoadResolver) {
        this.maxLinesToLoadResolver = maxLinesToLoadResolver;
    }

    public AppProperties createProperties() throws ValidationException {
        return new AppProperties(maxLinesToLoadResolver.getMaxNumberOfLinesToLoad());
    }
}
