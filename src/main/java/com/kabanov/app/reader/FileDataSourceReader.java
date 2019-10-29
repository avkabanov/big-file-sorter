package com.kabanov.app.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author kabaale
 */
public class FileDataSourceReader implements DataSourceReader<String> {

    private Scanner scanner;

    public FileDataSourceReader(File file) throws FileNotFoundException {
        scanner = new Scanner(file);
    }

    @Override
    @Nonnull
    public List<String> readLines(int numberOfLines) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (scanner.hasNext() && i < numberOfLines) {
            result.add(scanner.nextLine());
            i ++;
        }
        return result;
    }

    @Override
    @Nullable
    public String readLine() {
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }

    @Override
    public String next() {
        return readLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
