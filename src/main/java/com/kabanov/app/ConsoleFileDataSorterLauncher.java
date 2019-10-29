package com.kabanov.app;

import java.io.File;

import com.google.inject.Inject;
import com.kabanov.app.sorter.DataSorter;
import com.kabanov.app.validation.ConsoleParametersValidator;
import com.kabanov.app.validation.StorageNotAccessibleException;
import com.kabanov.app.validation.ValidationException;

/**
 * @author Kabanov Alexey
 */
public class ConsoleFileDataSorterLauncher {

    private final DataSorter<File> dataSorter;
    private final ConsoleParametersValidator parametersValidator;

    @Inject
    public ConsoleFileDataSorterLauncher(DataSorter<File> dataSorter, ConsoleParametersValidator parametersValidator) {
        this.dataSorter = dataSorter;
        this.parametersValidator = parametersValidator;
    }

    public void launch(String[] args) throws StorageNotAccessibleException, ValidationException {
        parametersValidator.validate(args);
        
        File src = new File(args[0]);
        File dest = new File(args[1]);

        dataSorter.sort(src, dest);
    }
}
