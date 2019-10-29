package com.kabanov.app.validation;

/**
 * @author Kabanov Alexey
 */
public interface AppPropertiesValidator {
    
    void validateMaxNumberOfLinesToLoad(int numberOfLines) throws ValidationException;

}
