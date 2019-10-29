package com.kabanov.app.validation;

/**
 * @author Kabanov Alexey
 */
public interface DataValidator<T> {
    void validateInputDataSource(T src) throws ValidationException;
}
