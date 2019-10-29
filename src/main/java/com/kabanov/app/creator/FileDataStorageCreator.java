package com.kabanov.app.creator;

import java.io.File;
import java.io.IOException;

/**
 * @author kabaale
 */
public class FileDataStorageCreator implements TempDataStorageCreator<File> {
    // 100 is a random number with 3 digits because file prefix should be at least 3 symbols long
    private int currentCounter = 100;
    
    @Override
    public File create() {
        File newFile;
        try {
            newFile = File.createTempFile(String.valueOf(currentCounter), null);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        currentCounter++;

        return newFile;
    }
}
