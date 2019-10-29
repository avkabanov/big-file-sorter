package com.kabanov.app.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author kabaale
 */
public class FileDataStoreWriter implements DataStorageWriter<String> {

    private PrintWriter printWriter;

    public FileDataStoreWriter(File dataSource) throws IOException {
        printWriter = new PrintWriter(new FileWriter(dataSource));
    }

    @Override
    public void writeLine(String line) {
        printWriter.println(line);
    }

    @Override
    public void writeLines(List<String> lines) {
        for (String line : lines) {
            printWriter.println(line);    
        }
    }

    @Override
    public void close() {
        printWriter.close();
    }
}
