package com.kabanov.app.configuration;

import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.kabanov.app.properties.AppProperties;
import com.kabanov.app.properties.AppPropertiesFactory;
import com.kabanov.app.properties.MaxLinesToLoadResolver;
import com.kabanov.app.properties.PropertiesMaxLinesToLoadResolver;
import com.kabanov.app.validation.AppPropertiesValidator;
import com.kabanov.app.validation.AppPropertiesValidatorImpl;
import com.kabanov.app.validation.ValidationException;

/**
 * @author kabaale
 */
public class ResourcePropertiesModule extends AbstractModule {

    @Override
    protected void configure() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("app.properties"));
            Names.bindProperties(binder(), props);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config: ", e);
        }
        ;
        bind(AppPropertiesValidator.class).to(AppPropertiesValidatorImpl.class);
        bind(MaxLinesToLoadResolver.class).to(PropertiesMaxLinesToLoadResolver.class);
    }

    @Provides
    public AppProperties providesAppProperties(AppPropertiesFactory appPropertiesFactory) throws
            ValidationException {
        return appPropertiesFactory.createProperties();
    }
}
