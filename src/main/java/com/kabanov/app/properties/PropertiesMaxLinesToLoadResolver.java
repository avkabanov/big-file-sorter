package com.kabanov.app.properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.kabanov.app.validation.AppPropertiesValidator;
import com.kabanov.app.validation.ValidationException;

/**
 * @author Kabanov Alexey
 */
public class PropertiesMaxLinesToLoadResolver implements MaxLinesToLoadResolver {
    
    // It has been decided not to calculate the maximum number of simultaneously loaded lines in runtime.
    // The constant number is used to fit the requirement that required amount of memory
    // should not depend on filesize. 
    // In fact, the number returned by this method determines the amount of memory will be used
    private final String maxNumberOfLinesToLoad;

    private AppPropertiesValidator validator;

    @Inject
    public PropertiesMaxLinesToLoadResolver(@Named("max_number_of_lines_to_load") String maxNumberOfLinesToLoad,
                                            AppPropertiesValidator validator) {
        this.maxNumberOfLinesToLoad = maxNumberOfLinesToLoad;
        this.validator = validator;
    }

    @Override
    public int getMaxNumberOfLinesToLoad() throws ValidationException {
        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(maxNumberOfLinesToLoad);
        } catch (NumberFormatException e) {
            throw new ValidationException("Number of lines must be an integer. Can not parse: " + maxNumberOfLinesToLoad);
        }

        validator.validateMaxNumberOfLinesToLoad(parsedNumber);
        
        return parsedNumber;
    }
}
