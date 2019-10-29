package com.kabanov.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kabanov.app.comparator.FileDataSourceLauncherModule;
import com.kabanov.app.configuration.FileDataSourceModule;
import com.kabanov.app.configuration.ResourcePropertiesModule;
import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.validation.ValidationException;

/**
 * @author Kabanov Alexey
 */
public class Application {

    public static void main(String[] args) throws StorageNotAccessibleException, ValidationException {
        Injector injector = Guice.createInjector(
                new FileDataSourceLauncherModule(),
                new FileDataSourceModule(), 
                new ResourcePropertiesModule());
        
        ConsoleFileDataSorterLauncher launcher = injector.getInstance(ConsoleFileDataSorterLauncher.class);
        
        launcher.launch(args);
    }
}
