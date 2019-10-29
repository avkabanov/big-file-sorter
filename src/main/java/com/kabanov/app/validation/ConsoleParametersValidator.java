package com.kabanov.app.validation;

/**
 * @author Kabanov Alexey
 */
public class ConsoleParametersValidator {
    
    public void validate(String[] args) throws ValidationException {
        if (args == null) {
            throw new ValidationException("args can not be null");
        }

        if (args.length != 2) {
            throw new ValidationException("args should contain two parameters: absolute src and dest path");
        }
    }
}
