package com.kabanov.app.validation;

/**
 * @author Kabanov Alexey
 */
public class AppPropertiesValidatorImpl implements AppPropertiesValidator {
    
    @Override
    public void validateMaxNumberOfLinesToLoad(int numberOfLines) throws ValidationException {
        if (numberOfLines <= 0) {
            throw new ValidationException("Number of lines should be greater than 0");
        }
    }
}
